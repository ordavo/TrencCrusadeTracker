package com.example.trenchcrusadetracker.domain.model.campaign

/**
 * Campaña de Trench Crusade.
 * Refleja los campos vistos en la hoja de Trench Companion (ej. "La guardia del Tzar"):
 * Victory Points, ronda actual/total, notas, localizaciones de exploración y promociones fallidas.
 */
data class Campaign(
    val id: String,
    val name: String,
    val creatorPlayerId: String,
    val currentPhase: CampaignPhase = CampaignPhase.Barracks,
    val currentRoundNumber: Int = 1,
    val totalRounds: Int = 12,
    val players: List<Player> = emptyList(),
    val startingDucats: Int = 500,
    val startingGloryPoints: Int = 0,
    val notes: String = "",
    val explorationLocations: List<String> = emptyList(),
    val failedPromotions: List<String> = emptyList()
)