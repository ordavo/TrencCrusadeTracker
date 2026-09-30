package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.faction.FactionProvider
import com.example.trenchcrusadetracker.domain.model.faction.Faction
import com.example.trenchcrusadetracker.domain.model.faction.FactionRule
import com.example.trenchcrusadetracker.domain.model.warband.Battlekit
import com.example.trenchcrusadetracker.domain.model.warband.EliteMember
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

/**
 * Provider for "The Red Brigade" faction.
 */
class RedBrigadeFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "red_brigade",
        name = "The Red Brigade",
        description = "Elite shock brigade characterized by iron discipline and distinctive red attire.",
        specialRules = listOf(
            FactionRule(
                id = "rule_red_banner",
                name = "Unyielding Red Line",
                effectDescription = "Immunity to panic checks while the brigade banner is active."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "red_brigade_colonel",
                customName = "Brigade Colonel",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 100,
                battlekit = Battlekit(),
                commandAbilities = listOf("Unyielding Line", "Synchronized Volley")
            ),
            EliteMember(
                id = "red_brigade_veteran_elite",
                customName = "Red Line Veteran",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 60,
                battlekit = Battlekit(),
                specialSkills = listOf("Red Line Discipline")
            ),
            TrooperMember(
                id = "red_brigade_guard",
                customName = "Red Line Guard",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 1, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 45,
                battlekit = Battlekit()
            )
        )
    }
}
