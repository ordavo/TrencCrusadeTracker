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
 * Provider for "Iron Sultanate" faction.
 */
class IronSultanateFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "iron_sultanate",
        name = "Iron Sultanate",
        description = "Defenders of the Iron Wall, masters of alchemy, naphtha fire, and heavy artillery.",
        specialRules = listOf(
            FactionRule(
                id = "rule_alchemy",
                name = "Alchemical Precision",
                effectDescription = "+1 modifier to hit when using alchemical weapons or special munitions."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "sultanate_emir",
                customName = "Janissary Officer",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 90,
                battlekit = Battlekit(),
                commandAbilities = listOf("Alchemical Volley", "Iron Defense")
            ),
            EliteMember(
                id = "sultanate_janissary_veteran",
                customName = "Janissary Veteran",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 60,
                battlekit = Battlekit(),
                specialSkills = listOf("Janissary Discipline")
            ),
            TrooperMember(
                id = "sultanate_azab",
                customName = "Azab Infantry",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, courage = 0, maxWounds = 1),
                baseCostInDucats = 30,
                battlekit = Battlekit()
            )
        )
    }
}
