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
 * Provider for "Heretic Naval Raiders" faction.
 */
class HereticNavalRaidersFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "heretic_naval_raiders",
        name = "Heretic Naval Raiders",
        description = "Corsairs and naval raiders dedicated to soul-harvesting and maritime plunder.",
        specialRules = listOf(
            FactionRule(
                id = "rule_naval_boarding",
                name = "Boarding Party",
                effectDescription = "Movement ignores deep mud, water, and difficult aquatic terrain."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "heretic_corsair_captain",
                customName = "Corsair Captain",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 90,
                battlekit = Battlekit(),
                commandAbilities = listOf("Full Broadside", "Plunder Command")
            ),
            EliteMember(
                id = "heretic_boarding_master",
                customName = "Naval Boarding Master",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 58,
                battlekit = Battlekit(),
                specialSkills = listOf("Harpoon Tactics")
            ),
            TrooperMember(
                id = "heretic_deck_raider",
                customName = "Deck Raider",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, courage = 0, maxWounds = 1),
                baseCostInDucats = 32,
                battlekit = Battlekit()
            )
        )
    }
}
