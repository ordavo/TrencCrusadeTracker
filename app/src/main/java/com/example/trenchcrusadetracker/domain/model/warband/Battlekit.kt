package com.example.trenchcrusadetracker.domain.model.warband

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitCategory
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
     * Agrega un elemento de equipamiento automáticamente en su categoría correspondiente.
     */
    fun addEquipment(item: Equipment): Battlekit {
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
     * Calcula el costo total acumulado en Ducados.
     */
    fun totalCostInDucats(): Int = allEquipment().sumOf { it.cost.ducatsAmount() }

    /**
     * Calcula el costo total acumulado en Puntos de Gloria.
     */
    fun totalCostInGloryPoints(): Int = allEquipment().sumOf { it.cost.gloryAmount() }
}
