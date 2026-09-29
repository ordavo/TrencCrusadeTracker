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
    val members: List<WarbandMember> = emptyList()
) {
    fun totalCostInDucats(): Int = members.sumOf { it.calculateTotalCostInDucats() }

    fun totalCostInGloryPoints(): Int = members.sumOf { it.calculateTotalCostInGloryPoints() }

    /**
     * Cumple RF-1 y RF-2: Bloquea edición si la campaña no está en Fase de Cuartel.
     */
    fun isEditableInPhase(phase: CampaignPhase): Boolean {
        return phase is CampaignPhase.Barracks
    }
}
