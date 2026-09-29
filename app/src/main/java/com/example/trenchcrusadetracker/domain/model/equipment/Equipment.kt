package com.example.trenchcrusadetracker.domain.model.equipment

/**
 * Tipo de moneda utilizada para adquirir equipamiento.
 */
enum class CurrencyType {
    DUCATS,
    GLORY_POINTS
}

/**
 * Representa el costo de un objeto en Ducados o Puntos de Gloria.
 */
data class EquipmentCost(
    val amount: Int,
    val currency: CurrencyType = CurrencyType.DUCATS
) {
    fun ducatsAmount(): Int = if (currency == CurrencyType.DUCATS) amount else 0
    fun gloryAmount(): Int = if (currency == CurrencyType.GLORY_POINTS) amount else 0
}

/**
 * Origen de procedencia del equipamiento según las reglas del libro.
 */
enum class EquipmentSource {
    STANDARD,      // Armería estándar
    BLACK_MARKET,  // Mercado Negro
    EXPLORATION    // Tabla de Exploración del libro general
}

/**
 * Categorías oficiales en las que se divide el Battlekit de un modelo.
 */
enum class BattlekitCategory {
    RANGED_WEAPONS, // Armas a distancia
    GRENADES,       // Granadas
    MELEE_WEAPONS,  // Armas cuerpo a cuerpo
    ARMOUR,         // Armaduras
    SHIELDS,        // Escudos
    EQUIPMENT,      // Equipo general
    SPECIAL         // Objetos especiales y reliquias
}

/**
 * Jerarquía sellada (Sealed Class) para todo el equipamiento de Trench Crusade.
 */
sealed class Equipment {
    abstract val id: String
    abstract val name: String
    abstract val cost: EquipmentCost
    abstract val source: EquipmentSource
    abstract val category: BattlekitCategory
    abstract val description: String

    data class Weapon(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val category: BattlekitCategory,
        override val description: String,
        val rangeInInches: Int? = null,
        val diceCount: Int = 1,
        val armorPiercing: Int = 0,
        val specialRules: List<String> = emptyList()
    ) : Equipment()

    data class Grenade(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        val blastRadiusInInches: Int? = null,
        val specialEffect: String
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.GRENADES
    }

    data class Armor(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        val armorBonus: Int,
        val movementPenalty: Int = 0
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.ARMOUR
    }

    data class Shield(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        val meleeDefenseBonus: Int = 1,
        val rangedDefenseBonus: Int = 0
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.SHIELDS
    }

    data class Gear(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        val specialEffect: String
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.EQUIPMENT
    }

    data class SpecialItem(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        val relicRule: String
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.SPECIAL
    }
}
