package com.example.trenchcrusadetracker.domain.model.warband

import com.example.trenchcrusadetracker.domain.model.campaign.CampaignPhase
import com.example.trenchcrusadetracker.domain.model.faction.Faction

/**
 * Fireteam de la Warband (regla de New Antioch u otras facciones).
 * El ejemplo de Trench Companion muestra Fireteams formados por 2 modelos
 * que se Activan simultáneamente.
 */
data class Fireteam(
    val id: String,
    val memberIds: List<String> = emptyList(),
    val name: String? = null
)

data class Warband(
    val id: String,
    val name: String,
    val playerId: String,
    val faction: Faction,
    val ducats: Int,
    val gloryPoints: Int,
    val members: List<WarbandMember> = emptyList(),
    val fireteams: List<Fireteam> = emptyList(),
    val maxElitesAllowed: Int = 6,
    val maxFireteamsAllowed: Int = 2
) {
    fun totalCostInDucats(): Int = members.sumOf { it.calculateTotalCostInDucats() }

    fun totalCostInGloryPoints(): Int = members.sumOf { it.calculateTotalCostInGloryPoints() }

    /**
     * Copia de las monedas del warband (treasury).
     */
    fun availableDucats(): Int = ducats

    fun availableGloryPoints(): Int = gloryPoints

    /**
     * Retorna el número actual de miembros con rol Elite en la banda.
     */
    fun currentEliteCount(): Int = members.count { it.role == MemberRole.ELITE }

    /**
     * Valida si la banda puede agregar o ascender un integrante a categoría Elite (Límite máximo: 6 por banda).
     */
    fun canAddElite(): Boolean = currentEliteCount() < maxElitesAllowed

    /**
     * Número de miembros en un rol concreto (ej. "Fielded: Elite: 6, Troop: 5, Mercenary: 2").
     */
    fun memberCountFor(role: MemberRole): Int = members.count { it.role == role }

    /**
     * Desglose de miembros por rol (incluye única vez cada rol presente).
     */
    fun roleCounts(): Map<MemberRole, Int> = MemberRole.values().associateWith { role ->
        memberCountFor(role)
    }

    /**
     * Número actual de Fireteams formados.
     */
    fun fireteamCount(): Int = fireteams.size

    /**
     * Valida si la banda puede agregar otro Fireteam (ej. New Antioch permite hasta 2).
     */
    fun canAddFireteam(): Boolean = fireteams.size < maxFireteamsAllowed

    /**
     * Valida si un miembro ya pertenece a algún Fireteam.
     */
    fun isInFireteam(memberId: String): Boolean = fireteams.any { it.memberIds.contains(memberId) }

    /**
     * Rating del warband (costo total en Ducados) para la hoja de campaña.
     */
    fun warbandRating(): Int = totalCostInDucats()

    /**
     * Cumple RF-1 y RF-2: Bloquea edición si la campaña no está en Fase de Cuartel.
     */
    fun isEditableInPhase(phase: CampaignPhase): Boolean {
        return phase is CampaignPhase.Barracks
    }
}