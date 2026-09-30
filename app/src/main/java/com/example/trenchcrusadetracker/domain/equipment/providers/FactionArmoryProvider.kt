package com.example.trenchcrusadetracker.domain.equipment.providers

import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.warband.MemberRole

/**
 * Interface Segregation Principle (ISP):
 * Provee el arsenal específico y permitido para cada facción inyectando el repositorio maestro.
 */
interface FactionArmoryProvider {
    val factionId: String
    fun getAvailableEquipment(): List<Equipment>
    fun getAvailableEquipmentForRole(role: MemberRole): List<Equipment>
}
