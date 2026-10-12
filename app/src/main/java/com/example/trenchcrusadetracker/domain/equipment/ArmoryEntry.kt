package com.example.trenchcrusadetracker.domain.equipment

import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentStipulations

/**
 * Entrada de una Armería de facción ("Armoury Table").
 *
 * En "Warbands of Trench Crusade" el perfil de cada pieza de Battlekit es común
 * (Battlekit estándar), pero el coste y las estipulaciones (Limit, ELITE only,
 * Shield Combo, Consumable, etc.) dependen de la facción. Esta clase modela esa
 * relación sin duplicar el perfil.
 */
data class ArmoryEntry(
    val equipmentId: String,
    val cost: EquipmentCost,
    val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
) {
    companion object {
        /** Constructor de conveniencia con coste en Ducados. */
        fun ducats(
            equipmentId: String,
            amount: Int,
            stipulations: EquipmentStipulations = EquipmentStipulations.NONE
        ): ArmoryEntry = ArmoryEntry(equipmentId, EquipmentCost(amount, CurrencyType.DUCATS), stipulations)

        /** Constructor de conveniencia con coste en Puntos de Gloria. */
        fun glory(
            equipmentId: String,
            amount: Int,
            stipulations: EquipmentStipulations = EquipmentStipulations.NONE
        ): ArmoryEntry = ArmoryEntry(equipmentId, EquipmentCost(amount, CurrencyType.GLORY_POINTS), stipulations)
    }
}
