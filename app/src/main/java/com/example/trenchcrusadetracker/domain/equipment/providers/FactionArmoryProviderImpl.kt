package com.example.trenchcrusadetracker.domain.equipment.providers

import com.example.trenchcrusadetracker.domain.equipment.ArmoryEntry
import com.example.trenchcrusadetracker.domain.equipment.EquipmentRepository
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.withArmory
import com.example.trenchcrusadetracker.domain.model.warband.MemberRole

/**
 * Proveedor de armería genérico (OCP): resuelve una lista de [ArmoryEntry] contra el
 * catálogo maestro, aplicando a cada perfil el coste y las estipulaciones de la facción.
 *
 * No conoce datos concretos de ninguna facción; éstos viven en [FactionArmories].
 */
class FactionArmoryProviderImpl(
    override val factionId: String,
    private val entries: List<ArmoryEntry>,
    private val masterRepository: EquipmentRepository
) : FactionArmoryProvider {

    /** Índice id -> perfil estándar/único, construido una sola vez. */
    private val catalogById: Map<String, Equipment> by lazy {
        masterRepository.getAllEquipment().associateBy { it.id }
    }

    /** Armería completa de la facción (perfil + coste + estipulaciones). */
    private val available: List<Equipment> by lazy {
        entries.mapNotNull { entry ->
            catalogById[entry.equipmentId]?.withArmory(entry.cost, entry.stipulations)
        }
    }

    override fun getAvailableEquipment(): List<Equipment> = available

    override fun getAvailableEquipmentForRole(role: MemberRole): List<Equipment> {
        return when (role) {
            MemberRole.LEADER, MemberRole.ELITE, MemberRole.MERCENARY -> available
            // Los Troopers no pueden portar objetos de Gloria ni equipo restringido a ELITE.
            MemberRole.TROOPER -> available.filter {
                it.cost.gloryAmount() == 0 && !it.stipulations.eliteOnly
            }
        }
    }
}

/**
 * Registro de armerías (Factory). Devuelve un [FactionArmoryProvider] para cada facción
 * registrada en [FactionArmories] y un proveedor de respaldo para facciones sin armería.
 */
object FactionArmoryRegistry {

    fun forFaction(factionId: String, masterRepository: EquipmentRepository): FactionArmoryProvider {
        val entries = FactionArmories.byFactionId[factionId]
        return if (entries != null) {
            FactionArmoryProviderImpl(factionId, entries, masterRepository)
        } else {
            DefaultFactionArmoryProvider(factionId, masterRepository)
        }
    }
}
