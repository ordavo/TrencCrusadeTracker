package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.equipment.EquipmentRepository
import com.example.trenchcrusadetracker.domain.equipment.providers.FactionArmoryProvider
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.warband.MemberRole

/**
 * Proveedor del arsenal específico de "Principality of New Antioch".
 */
class NewAntiochArmoryProvider(
    private val masterRepository: EquipmentRepository
) : FactionArmoryProvider {

    override val factionId: String = "new_antioch"

    override fun getAvailableEquipment(): List<Equipment> {
        return masterRepository.getAllEquipment()
    }

    override fun getAvailableEquipmentForRole(role: MemberRole): List<Equipment> {
        return when (role) {
            MemberRole.LEADER, MemberRole.ELITE -> masterRepository.getAllEquipment()
            MemberRole.TROOPER -> masterRepository.getAllEquipment().filter { it.cost.gloryAmount() == 0 }
            MemberRole.MERCENARY -> masterRepository.getAllEquipment()
        }
    }
}
