package com.example.trenchcrusadetracker.domain.model.campaign

data class Campaign(
    val id: String,
    val name: String,
    val creatorPlayerId: String,
    val currentPhase: CampaignPhase = CampaignPhase.Barracks,
    val currentRoundNumber: Int = 1,
    val players: List<Player> = emptyList(),
    val startingDucats: Int = 500,
    val startingGloryPoints: Int = 0
)
