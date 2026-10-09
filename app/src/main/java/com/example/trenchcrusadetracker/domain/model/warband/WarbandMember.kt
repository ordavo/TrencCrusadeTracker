package com.example.trenchcrusadetracker.domain.model.warband

import com.example.trenchcrusadetracker.domain.model.equipment.Equipment

/**
 * Rol de un integrante dentro de la Warband.
 * Según las fichas de Trench Companion: Leader/Elite/Troop/Mercenary.
 */
enum class MemberRole {
    LEADER,
    ELITE,
    TROOPER,
    MERCENARY
}

/**
 * Tipo de movimiento de un modelo (ej. "6"/Infantry", "10"/Flying").
 */
enum class MovementType(val displayName: String) {
    INFANTRY("Infantry"),
    FLYING("Flying"),
    MOUNTED("Mounted"),
    WALKER("Walker")
}

/**
 * Perfil base de características de un modelo.
 * Armour es el valor de armadura del perfil; Melee/Ranged son dados (D6).
 */
data class ModelStats(
    val movement: Int = 6,
    val movementType: MovementType = MovementType.INFANTRY,
    val melee: Int = 0,
    val ranged: Int = 0,
    val armor: Int = 0,
    val courage: Int = 0,
    val maxWounds: Int = 1
) {
    /**
     * Formato de movilidad oficial del juego, ej. "6"/Infantry".
     */
    fun movementDisplay(): String = "${movement}\" / ${movementType.displayName}"

    companion object {
        /**
         * Perfil base para capitanes y líderes:
         * Movimiento = 6", Melee = 0, Ranged = 0, Armadura = 0, Courage = 2, Max Wounds = 1.
         */
        val CaptainBase = ModelStats(
            movement = 6,
            melee = 0,
            ranged = 0,
            armor = 0,
            maxWounds = 1
        )
    }
}

/**
 * Superclase abstracta para los integrantes de una Warband.
 * Modela la estructura de las fichas de Trench Companion:
 * - Coste base en dos monedas (Ducados y Puntos de Gloria).
 * - Keywords del modelo (NEW ANTIOCH, ELITE, LEADER, TOUGH, FLYING, etc.).
 * - Reglas especiales (✧), habilidades de progresión (△) y penalizaciones (▽).
 */
abstract class WarbandMember {
    abstract val id: String
    abstract val customName: String
    abstract val role: MemberRole
    abstract val baseStats: ModelStats
    abstract val baseCostInDucats: Int
    abstract val baseCostInGloryPoints: Int
    abstract val keywords: List<String>
    abstract val specialRules: List<String>
    abstract val skills: List<String>
    abstract val flaws: List<String>
    abstract val battlekit: Battlekit
    abstract val experiencePoints: Int
    abstract val injuries: List<String>
    abstract val isDead: Boolean

    /**
     * Coste total del personaje en Ducados (base + battlekit).
     */
    fun calculateTotalCostInDucats(): Int {
        return baseCostInDucats + battlekit.totalCostInDucats()
    }

    /**
     * Coste total del personaje en Puntos de Gloria (base + battlekit).
     */
    fun calculateTotalCostInGloryPoints(): Int {
        return baseCostInGloryPoints + battlekit.totalCostInGloryPoints()
    }

    /**
     * Regla de validación para determinar si la unidad puede llevar cierto equipo.
     */
    abstract fun canEquip(equipment: Equipment): Boolean
}

data class LeaderMember(
    override val id: String,
    override val customName: String,
    override val baseStats: ModelStats = ModelStats.CaptainBase,
    override val baseCostInDucats: Int,
    override val baseCostInGloryPoints: Int = 0,
    override val keywords: List<String> = listOf("LEADER"),
    override val specialRules: List<String> = emptyList(),
    override val skills: List<String> = emptyList(),
    override val flaws: List<String> = emptyList(),
    override val battlekit: Battlekit = Battlekit(),
    override val experiencePoints: Int = 0,
    override val injuries: List<String> = emptyList(),
    override val isDead: Boolean = false,
    val commandAbilities: List<String> = emptyList()
) : WarbandMember() {
    override val role: MemberRole = MemberRole.LEADER
    override fun canEquip(equipment: Equipment): Boolean = true
}

data class EliteMember(
    override val id: String,
    override val customName: String,
    override val baseStats: ModelStats,
    override val baseCostInDucats: Int,
    override val baseCostInGloryPoints: Int = 0,
    override val keywords: List<String> = listOf("ELITE"),
    override val specialRules: List<String> = emptyList(),
    override val skills: List<String> = emptyList(),
    override val flaws: List<String> = emptyList(),
    override val battlekit: Battlekit = Battlekit(),
    override val experiencePoints: Int = 0,
    override val injuries: List<String> = emptyList(),
    override val isDead: Boolean = false,
    val specialSkills: List<String> = emptyList(),
    val isPromotedFromTrooper: Boolean = false
) : WarbandMember() {
    override val role: MemberRole = MemberRole.ELITE
    override fun canEquip(equipment: Equipment): Boolean = true
}

data class TrooperMember(
    override val id: String,
    override val customName: String,
    override val baseStats: ModelStats,
    override val baseCostInDucats: Int,
    override val baseCostInGloryPoints: Int = 0,
    override val keywords: List<String> = listOf("TROOPER"),
    override val specialRules: List<String> = emptyList(),
    override val skills: List<String> = emptyList(),
    override val flaws: List<String> = emptyList(),
    override val battlekit: Battlekit = Battlekit(),
    override val experiencePoints: Int = 0,
    override val injuries: List<String> = emptyList(),
    override val isDead: Boolean = false,
    val maxEquipmentAllowed: Int = 3
) : WarbandMember() {
    override val role: MemberRole = MemberRole.TROOPER
    override fun canEquip(equipment: Equipment): Boolean = battlekit.allEquipment().size < maxEquipmentAllowed

    /**
     * Asciende a una tropa estándar a categoría Elite tras ganar experiencia o méritos en campaña.
     */
    fun promoteToElite(specialSkillName: String = "Promoted Veteran"): EliteMember {
        return EliteMember(
            id = id,
            customName = customName,
            baseStats = baseStats,
            baseCostInDucats = baseCostInDucats,
            baseCostInGloryPoints = baseCostInGloryPoints,
            keywords = keywords,
            specialRules = specialRules,
            skills = skills + specialSkillName,
            flaws = flaws,
            battlekit = battlekit,
            experiencePoints = experiencePoints,
            injuries = injuries,
            isDead = isDead,
            specialSkills = listOf(specialSkillName),
            isPromotedFromTrooper = true
        )
    }
}

data class MercenaryMember(
    override val id: String,
    override val customName: String,
    override val baseStats: ModelStats,
    override val baseCostInDucats: Int = 0,
    override val baseCostInGloryPoints: Int = 0,
    override val keywords: List<String> = listOf("MERCENARY"),
    override val specialRules: List<String> = emptyList(),
    override val skills: List<String> = emptyList(),
    override val flaws: List<String> = emptyList(),
    override val battlekit: Battlekit = Battlekit(),
    override val experiencePoints: Int = 0,
    override val injuries: List<String> = emptyList(),
    override val isDead: Boolean = false
) : WarbandMember() {
    override val role: MemberRole = MemberRole.MERCENARY
    override fun canEquip(equipment: Equipment): Boolean = true
}