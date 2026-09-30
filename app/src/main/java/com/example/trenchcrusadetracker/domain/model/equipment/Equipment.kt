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
 * Tipos oficiales de Battlekit según el reglamento de Trench Crusade.
 */
enum class BattlekitType(val displayName: String) {
    ONE_HANDED("1-Handed"),
    TWO_HANDED("2-Handed"),
    GRENADE("Grenade"),
    ARMOUR("Armour"),
    SHIELD("Shield"),
    EQUIPMENT("Equipment"),
    SPECIAL("Special")
}

/**
 * Alcance oficial de una pieza de Battlekit.
 */
sealed class WeaponRange {
    data class Ranged(val inches: Int) : WeaponRange() {
        override fun toString(): String = "${inches}\""
    }
    object Melee : WeaponRange() {
        override fun toString(): String = "Melee"
    }
    data class DualPurpose(val inches: Int) : WeaponRange() {
        override fun toString(): String = "${inches}\"/Melee"
    }
    object None : WeaponRange() {
        override fun toString(): String = "-"
    }
}

/**
 * Jerarquía sellada (Sealed Class) para todo el equipamiento de Trench Crusade.
 * Sigue estrictamente la especificación oficial de Profile, Type, Range, Keywords y Special Rules.
 */
sealed class Equipment {
    abstract val id: String
    abstract val name: String
    abstract val cost: EquipmentCost
    abstract val source: EquipmentSource
    abstract val category: BattlekitCategory
    abstract val description: String
    abstract val type: BattlekitType
    abstract val range: WeaponRange
    abstract val keywords: List<String>
    abstract val specialRules: List<String>

    val isOneHanded: Boolean get() = type == BattlekitType.ONE_HANDED || type == BattlekitType.GRENADE || type == BattlekitType.SHIELD
    val isTwoHanded: Boolean get() = type == BattlekitType.TWO_HANDED
    val handsRequired: Int get() = when (type) {
        BattlekitType.TWO_HANDED -> 2
        BattlekitType.ONE_HANDED, BattlekitType.SHIELD -> 1
        else -> 0
    }

    data class RangedWeapon(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        override val type: BattlekitType = BattlekitType.TWO_HANDED,
        override val range: WeaponRange,
        override val keywords: List<String> = emptyList(),
        override val specialRules: List<String> = emptyList()
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.RANGED_WEAPONS
    }

    data class MeleeWeapon(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        override val type: BattlekitType = BattlekitType.ONE_HANDED,
        override val range: WeaponRange = WeaponRange.Melee,
        override val keywords: List<String> = emptyList(),
        override val specialRules: List<String> = emptyList()
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.MELEE_WEAPONS
    }

    data class DualPurposeWeapon(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        override val type: BattlekitType = BattlekitType.ONE_HANDED,
        override val range: WeaponRange,
        override val keywords: List<String> = emptyList(),
        override val specialRules: List<String> = emptyList()
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.RANGED_WEAPONS
    }

    data class Grenade(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        override val range: WeaponRange = WeaponRange.Ranged(8),
        override val keywords: List<String> = emptyList(),
        override val specialRules: List<String> = emptyList()
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.GRENADES
        override val type: BattlekitType = BattlekitType.GRENADE
    }

    data class Armor(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        val armorBonus: Int = 1,
        val movementPenalty: Int = 0,
        override val keywords: List<String> = emptyList(),
        override val specialRules: List<String> = emptyList()
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.ARMOUR
        override val type: BattlekitType = BattlekitType.ARMOUR
        override val range: WeaponRange = WeaponRange.None
    }

    data class Shield(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        val meleeDefenseBonus: Int = 1,
        val rangedDefenseBonus: Int = 1,
        override val keywords: List<String> = emptyList(),
        override val specialRules: List<String> = emptyList()
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.SHIELDS
        override val type: BattlekitType = BattlekitType.SHIELD
        override val range: WeaponRange = WeaponRange.None
    }

    data class Gear(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        override val keywords: List<String> = emptyList(),
        override val specialRules: List<String> = emptyList()
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.EQUIPMENT
        override val type: BattlekitType = BattlekitType.EQUIPMENT
        override val range: WeaponRange = WeaponRange.None
    }

    data class SpecialItem(
        override val id: String,
        override val name: String,
        override val cost: EquipmentCost,
        override val source: EquipmentSource = EquipmentSource.STANDARD,
        override val description: String,
        override val keywords: List<String> = emptyList(),
        override val specialRules: List<String> = emptyList()
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.SPECIAL
        override val type: BattlekitType = BattlekitType.SPECIAL
        override val range: WeaponRange = WeaponRange.None
    }
}
