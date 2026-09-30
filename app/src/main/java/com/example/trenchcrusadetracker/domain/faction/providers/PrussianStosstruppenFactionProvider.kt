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
 * Provider for "Prussian Stosstruppen" faction.
 */
class PrussianStosstruppenFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "prussian_stosstruppen",
        name = "Prussian Stosstruppen",
        description = "Prussian trench shock troops specializing in rapid breach tactics and aggressive assault.",
        specialRules = listOf(
            FactionRule(
                id = "rule_trench_assault",
                name = "Shock Assault",
                effectDescription = "Bonus when charging and throwing grenades in direct trench breaches."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "prussian_hauptmann",
                customName = "Stoss-Hauptmann",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 95,
                battlekit = Battlekit(),
                commandAbilities = listOf("Blitz Charge", "Grenade Volley")
            ),
            EliteMember(
                id = "prussian_storm_veteran",
                customName = "Storm Trooper Veteran",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 60,
                battlekit = Battlekit(),
                specialSkills = listOf("Breach Specialist")
            ),
            TrooperMember(
                id = "prussian_stosstrupp",
                customName = "Stosstrupp Trooper",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 40,
                battlekit = Battlekit()
            )
        )
    }
}
