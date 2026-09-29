package com.example.trenchcrusadetracker.domain.model.game

data class TurnLog(
    val turnNumber: Int,
    val player1VictoryPoints: Int,
    val player2VictoryPoints: Int,
    val player1GloriousDeaths: Int,
    val player2GloriousDeaths: Int
)
