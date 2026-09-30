package com.example.trenchcrusadetracker.domain.model.warband

import com.example.trenchcrusadetracker.domain.model.campaign.CampaignPhase
import com.example.trenchcrusadetracker.domain.model.faction.Faction

data class Warband(
    val id: String,
    val name: String,
    val playerId: String,
    val faction: Faction,
    val ducats: Int,
    val gloryPoints: Int,
    val members: List<WarbandMember> = emptyList(),
    val maxElitesAllowed: Int = 6
) {
    fun totalCostInDucats(): Int = members.sumOf { it.calculateTotalCostInDucats() }

    fun totalCostInGloryPoints(): Int = members.sumOf { it.calculateTotalCostInGloryPoints() }

    /**
     * Retorna el número actual de miembros con rol Elite en la banda.
     */
    fun currentEliteCount(): Int = members.count { it.role == MemberRole.ELITE }

    /**
     * Valida si la banda puede agregar o ascender un integrante a categoría Elite (Límite máximo: 6 por banda).
     */
    fun canAddElite(): Boolean = currentEliteCount() < maxElitesAllowed

    /**
     * Cumple RF-1 y RF-2: Bloquea edición si la campaña no está en Fase de Cuartel.
     */
    fun isEditableInPhase(phase: CampaignPhase): Boolean {
        return phase is CampaignPhase.Barracks
    }
}
