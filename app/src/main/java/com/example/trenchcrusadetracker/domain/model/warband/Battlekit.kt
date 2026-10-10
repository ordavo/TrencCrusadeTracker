package com.example.trenchcrusadetracker.domain.model.warband

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitCategory
import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitType
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment

/**
 * Estructura del Battlekit de un modelo, dividido en sus 7 categorías oficiales:
 * 1. Ranged Weapons
 * 2. Grenades
 * 3. Melee Weapons
 * 4. Armour
 * 5. Shields
 * 6. Equipment
 * 7. Special
 * Cumple con los límites de Battlekit y restricciones de Escudos del reglamento oficial.
 */
data class Battlekit(
    val rangedWeapons: List<Equipment> = emptyList(),
    val grenades: List<Equipment> = emptyList(),
    val meleeWeapons: List<Equipment> = emptyList(),
    val armour: List<Equipment> = emptyList(),
    val shields: List<Equipment> = emptyList(),
    val equipment: List<Equipment> = emptyList(),
    val special: List<Equipment> = emptyList()
) {
    /**
     * Retorna la lista consolidada de todos los objetos en el Battlekit.
     */
    fun allEquipment(): List<Equipment> {
        return rangedWeapons + grenades + meleeWeapons + armour + shields + equipment + special
    }

    /**
     * Valida las restricciones oficiales del reglamento antes de agregar una pieza de Battlekit.
     */
    fun canAddEquipment(item: Equipment): Boolean {
        val all = allEquipment()

        // Regla: No se permiten piezas de equipamiento con el mismo nombre duplicado
        if (item.type == BattlekitType.EQUIPMENT && all.any { it.name == item.name }) {
            return false
        }

        // Regla: Máximo un traje de Armadura
        if (item.type == BattlekitType.ARMOUR && armour.isNotEmpty()) {
            return false
        }

        // Regla: Máximo un tipo de Granada
        if (item.type == BattlekitType.GRENADE && grenades.isNotEmpty()) {
            return false
        }

        // Regla: Máximo un Escudo
        if (item.type == BattlekitType.SHIELD && shields.isNotEmpty()) {
            return false
        }

        val carriesShield = shields.isNotEmpty() || item.type == BattlekitType.SHIELD

        // Restricciones con Escudo
        if (carriesShield) {
            // No se pueden llevar armas de 2 Manos salvo que tengan la estipulación Shield Combo
            if (item.isTwoHanded && !item.stipulations.shieldCombo && !item.keywords.contains("Shield Combo")) {
                return false
            }
            // Máximo una arma de 1 Mano Melee y una de 1 Mano a Distancia
            if (item.category == BattlekitCategory.MELEE_WEAPONS && meleeWeapons.isNotEmpty()) {
                return false
            }
            if (item.category == BattlekitCategory.RANGED_WEAPONS && rangedWeapons.isNotEmpty()) {
                return false
            }
        } else {
            // Límites de Armas sin Escudo
            if (item.category == BattlekitCategory.RANGED_WEAPONS) {
                val count2H = rangedWeapons.count { it.isTwoHanded }
                val count1H = rangedWeapons.count { it.isOneHanded }
                if (item.isTwoHanded && (count2H >= 1 || count1H > 0)) return false
                if (item.isOneHanded && (count2H >= 1 || count1H >= 2)) return false
            }

            if (item.category == BattlekitCategory.MELEE_WEAPONS) {
                val count2H = meleeWeapons.count { it.isTwoHanded }
                val count1H = meleeWeapons.count { it.isOneHanded }
                if (item.isTwoHanded && (count2H >= 1 || count1H > 0)) return false
                if (item.isOneHanded && (count2H >= 1 || count1H >= 2)) return false
            }
        }

        // Límite total de manos ocupadas <= 2
        val currentHands = all.sumOf { it.handsRequired }
        if (currentHands + item.handsRequired > 2) {
            return false
        }

        return true
    }

    /**
     * Agrega un elemento de equipamiento automáticamente en su categoría correspondiente si cumple las restricciones.
     */
    fun addEquipment(item: Equipment): Battlekit {
        if (!canAddEquipment(item)) return this

        return when (item.category) {
            BattlekitCategory.RANGED_WEAPONS -> copy(rangedWeapons = rangedWeapons + item)
            BattlekitCategory.GRENADES -> copy(grenades = grenades + item)
            BattlekitCategory.MELEE_WEAPONS -> copy(meleeWeapons = meleeWeapons + item)
            BattlekitCategory.ARMOUR -> copy(armour = armour + item)
            BattlekitCategory.SHIELDS -> copy(shields = shields + item)
            BattlekitCategory.EQUIPMENT -> copy(equipment = equipment + item)
            BattlekitCategory.SPECIAL -> copy(special = special + item)
        }
    }

    /**
     * Devuelve las piezas de equipamiento del Battlekit pertenecientes a una categoría.
     * Útil para mostrar el equipamiento agrupado por secciones en la interfaz.
     */
    fun itemsInCategory(category: BattlekitCategory): List<Equipment> {
        return when (category) {
            BattlekitCategory.RANGED_WEAPONS -> rangedWeapons
            BattlekitCategory.GRENADES -> grenades
            BattlekitCategory.MELEE_WEAPONS -> meleeWeapons
            BattlekitCategory.ARMOUR -> armour
            BattlekitCategory.SHIELDS -> shields
            BattlekitCategory.EQUIPMENT -> equipment
            BattlekitCategory.SPECIAL -> special
        }
    }

    /**
     * Calcula el costo total acumulado en Ducados.
     */
    fun totalCostInDucats(): Int = allEquipment().sumOf { it.cost.ducatsAmount() }

    /**
     * Calcula el costo total acumulado en Puntos de Gloria.
     */
    fun totalCostInGloryPoints(): Int = allEquipment().sumOf { it.cost.gloryAmount() }
}
