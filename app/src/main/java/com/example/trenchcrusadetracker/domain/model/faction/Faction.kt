package com.example.trenchcrusadetracker.domain.model.faction

data class FactionRule(
    val id: String,
    val name: String,
    val effectDescription: String
)

data class Faction(
    val id: String,
    val name: String,
    val description: String,
    val specialRules: List<FactionRule> = emptyList(),
    val defaultStartingDucats: Int = 500
)
