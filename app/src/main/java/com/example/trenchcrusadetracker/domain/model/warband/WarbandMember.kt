package com.example.trenchcrusadetracker.domain.model.warband

import com.example.trenchcrusadetracker.domain.model.equipment.Equipment

enum class MemberRole {
    LEADER,
    ELITE,
    TROOPER,
    MERCENARY
}

data class ModelStats(
    val movement: Int = 5,
    val melee: Int = 0,
    val ranged: Int = 0,
    val armor: Int = 0,
    val courage: Int = 0,
    val maxWounds: Int = 1
)

/**
 * Superclase abstracta para los integrantes de una Warband.
 */
abstract class WarbandMember {
    abstract val id: String
    abstract val customName: String
    abstract val role: MemberRole
    abstract val baseStats: ModelStats
    abstract val baseCostInDucats: Int
    abstract val battlekit: Battlekit
    abstract val experiencePoints: Int
    abstract val injuries: List<String>
    abstract val isDead: Boolean

    /**
     * Calcula el costo total del personaje en Ducados incluyendo su equipamiento.
     */
    fun calculateTotalCostInDucats(): Int {
        return baseCostInDucats + battlekit.totalCostInDucats()
    }

    /**
     * Calcula el costo total del personaje en Puntos de Gloria de su equipamiento.
     */
    fun calculateTotalCostInGloryPoints(): Int {
        return battlekit.totalCostInGloryPoints()
    }

    /**
     * Regla de validación para determinar si la unidad puede llevar cierto equipo.
     */
    abstract fun canEquip(equipment: Equipment): Boolean
}

data class LeaderMember(
    override val id: String,
    override val customName: String,
    override val baseStats: ModelStats,
    override val baseCostInDucats: Int,
    override val battlekit: Battlekit = Battlekit(),
    override val experiencePoints: Int = 0,
    override val injuries: List<String> = emptyList(),
    override val isDead: Boolean = false,
    val commandAbilities: List<String> = emptyList()
) : WarbandMember() {
    override val role: MemberRole = MemberRole.LEADER
    override fun canEquip(equipment: Equipment): Boolean = true
}

data class TrooperMember(
    override val id: String,
    override val customName: String,
    override val baseStats: ModelStats,
    override val baseCostInDucats: Int,
    override val battlekit: Battlekit = Battlekit(),
    override val experiencePoints: Int = 0,
    override val injuries: List<String> = emptyList(),
    override val isDead: Boolean = false,
    val maxEquipmentAllowed: Int = 3
) : WarbandMember() {
    override val role: MemberRole = MemberRole.TROOPER
    override fun canEquip(equipment: Equipment): Boolean = battlekit.allEquipment().size < maxEquipmentAllowed
}
