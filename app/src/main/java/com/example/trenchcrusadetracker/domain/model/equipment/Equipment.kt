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
 * Estipulaciones ("Armoury Stipulations") que una Armería de facción puede imponer
 * a una pieza de Battlekit sobre el perfil estándar (ver "Warbands of Trench Crusade").
 *
 * - Consumable: se retira del roster al finalizar la partida.
 * - Headgear: un modelo no puede portar más de una pieza de Headgear.
 * - Shield Combo: permite combinar la pieza (arma 2 manos) con un Escudo.
 * - Bayonet Lug: requisito para fijar una Bayoneta a un arma a distancia.
 * - Limit: número máximo de esa pieza que puede tener una banda en campaña.
 * - LimitExcluding: excepción textual al límite (p. ej. "Mechanized Heavy Infantry").
 * - RestrictedTo: palabras clave / tipos de modelo que pueden portarla ("ELITE", etc.).
 */
data class EquipmentStipulations(
    val consumable: Boolean = false,
    val headgear: Boolean = false,
    val shieldCombo: Boolean = false,
    val bayonetLug: Boolean = false,
    val limit: Int? = null,
    val limitExcluding: String? = null,
    val restrictedTo: List<String> = emptyList()
) {
    /** Indica si la pieza está restringida a modelos con el keyword ELITE. */
    val eliteOnly: Boolean get() = restrictedTo.any { it.equals("ELITE", ignoreCase = true) }

    /** Indica si la pieza impone alguna estipulación. */
    val isEmpty: Boolean
        get() = !consumable && !headgear && !shieldCombo && !bayonetLug &&
            limit == null && limitExcluding == null && restrictedTo.isEmpty()

    /** Texto legible para la interfaz (en español). */
    fun display(): String {
        val parts = mutableListOf<String>()
        if (restrictedTo.isNotEmpty()) parts += "${restrictedTo.joinToString(" & ")} only"
        if (limit != null) {
            val extra = limitExcluding?.let { " (excl. $it)" } ?: ""
            parts += "Limit: $limit$extra"
        }
        if (consumable) parts += "Consumable"
        if (headgear) parts += "Headgear"
        if (shieldCombo) parts += "Shield Combo"
        if (bayonetLug) parts += "Bayonet Lug"
        return parts.joinToString(", ")
    }

    companion object {
        val NONE = EquipmentStipulations()
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
    /** Estipulaciones impuestas por la Armería de la facción (vacío = perfil estándar). */
    abstract val stipulations: EquipmentStipulations
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
        override val specialRules: List<String> = emptyList(),
        override val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
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
        override val specialRules: List<String> = emptyList(),
        override val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
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
        override val specialRules: List<String> = emptyList(),
        override val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
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
        override val specialRules: List<String> = emptyList(),
        override val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
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
        override val specialRules: List<String> = emptyList(),
        override val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
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
        override val specialRules: List<String> = emptyList(),
        override val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
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
        override val specialRules: List<String> = emptyList(),
        override val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
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
        override val specialRules: List<String> = emptyList(),
        override val stipulations: EquipmentStipulations = EquipmentStipulations.NONE
    ) : Equipment() {
        override val category: BattlekitCategory = BattlekitCategory.SPECIAL
        override val type: BattlekitType = BattlekitType.SPECIAL
        override val range: WeaponRange = WeaponRange.None
    }
}

/**
 * Aplica el costo y las estipulaciones de una Armería de facción a un perfil estándar,
 * devolviendo una copia tipada del mismo [Equipment].
 */
fun Equipment.withArmory(
    cost: EquipmentCost,
    stipulations: EquipmentStipulations
): Equipment = when (this) {
    is Equipment.RangedWeapon -> copy(cost = cost, stipulations = stipulations)
    is Equipment.MeleeWeapon -> copy(cost = cost, stipulations = stipulations)
    is Equipment.DualPurposeWeapon -> copy(cost = cost, stipulations = stipulations)
    is Equipment.Grenade -> copy(cost = cost, stipulations = stipulations)
    is Equipment.Armor -> copy(cost = cost, stipulations = stipulations)
    is Equipment.Shield -> copy(cost = cost, stipulations = stipulations)
    is Equipment.Gear -> copy(cost = cost, stipulations = stipulations)
    is Equipment.SpecialItem -> copy(cost = cost, stipulations = stipulations)
}
