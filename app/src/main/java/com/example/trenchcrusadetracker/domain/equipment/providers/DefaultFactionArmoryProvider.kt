package com.example.trenchcrusadetracker.domain.equipment.providers

import com.example.trenchcrusadetracker.domain.equipment.EquipmentRepository
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.warband.MemberRole

/**
 * Proveedor de arsenal genérico (fallback) para facciones que aún no cuentan con
 * una armería específica. Expone el arsenal maestro aplicando las restricciones
 * básicas por rol: los Troopers no pueden portar objetos adquiridos con Gloria.
 */
class DefaultFactionArmoryProvider(
    override val factionId: String,
    private val masterRepository: EquipmentRepository
) : FactionArmoryProvider {

    override fun getAvailableEquipment(): List<Equipment> = masterRepository.getAllEquipment()

    override fun getAvailableEquipmentForRole(role: MemberRole): List<Equipment> {
        return when (role) {
            MemberRole.LEADER, MemberRole.ELITE -> masterRepository.getAllEquipment()
            MemberRole.TROOPER -> masterRepository.getAllEquipment().filter { it.cost.gloryAmount() == 0 }
            MemberRole.MERCENARY -> masterRepository.getAllEquipment()
        }
    }
}
