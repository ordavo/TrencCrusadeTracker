package com.example.trenchcrusadetracker.domain.model.campaign

import com.example.trenchcrusadetracker.domain.model.warband.Warband

data class Player(
    val id: String,
    val name: String,
    val warband: Warband? = null
)
