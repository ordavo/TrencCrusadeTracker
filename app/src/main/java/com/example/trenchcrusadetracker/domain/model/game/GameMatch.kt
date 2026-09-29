package com.example.trenchcrusadetracker.domain.model.game

enum class MatchStatus {
    WAITING_CONFIRMATION,
    DEPLOYMENT,
    IN_PROGRESS,
    FINISHED
}

data class GameMatch(
    val id: String,
    val campaignId: String?,
    val player1Id: String,
    val player2Id: String,
    val scenario: Scenario,
    val status: MatchStatus = MatchStatus.WAITING_CONFIRMATION,
    val currentTurn: Int = 1,
    val turnLogs: List<TurnLog> = emptyList()
)
