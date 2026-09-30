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
 * Provider for "The Great Hunger" faction.
 */
class GreatHungerFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "great_hunger",
        name = "The Great Hunger",
        description = "Cultists and ravenous devourers driven by insatiable abyssal hunger.",
        specialRules = listOf(
            FactionRule(
                id = "rule_insatiable_hunger",
                name = "Insatiable Devouring",
                effectDescription = "Regain wounds when slaying enemies in close combat."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "hunger_abyssal_herald",
                customName = "Abyssal Herald",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 90,
                battlekit = Battlekit(),
                commandAbilities = listOf("Feast Command", "Famine Aura")
            ),
            EliteMember(
                id = "hunger_devourer",
                customName = "Ravenous Devourer",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 55,
                battlekit = Battlekit(),
                specialSkills = listOf("Flesh Gorge")
            ),
            TrooperMember(
                id = "hunger_starving_cultist",
                customName = "Starving Cultist",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, courage = 0, maxWounds = 1),
                baseCostInDucats = 25,
                battlekit = Battlekit()
            )
        )
    }
}
