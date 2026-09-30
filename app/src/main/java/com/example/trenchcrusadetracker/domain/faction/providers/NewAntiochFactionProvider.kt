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
 * Provider for "Principality of New Antioch" faction.
 */
class NewAntiochFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "new_antioch",
        name = "Principality of New Antioch",
        description = "The bastion of faith and military technology standing firm against the Forces of Hell.",
        specialRules = listOf(
            FactionRule(
                id = "rule_antioch_discipline",
                name = "Strict Discipline",
                effectDescription = "+1 bonus to Courage rolls when a Leader is within 8 inches."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "antioch_captain",
                customName = "Lieutenant Commander",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 90,
                battlekit = Battlekit(),
                commandAbilities = listOf("Tactical Advance", "Hold the Line")
            ),
            EliteMember(
                id = "antioch_sniper_priest",
                customName = "Sniper Priest",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 1, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 65,
                battlekit = Battlekit(),
                specialSkills = listOf("Marksmanship", "Divine Sight")
            ),
            TrooperMember(
                id = "antioch_trench_infantry",
                customName = "Trench Trooper",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, courage = 0, maxWounds = 1),
                baseCostInDucats = 35,
                battlekit = Battlekit()
            ),
            TrooperMember(
                id = "antioch_cavalry",
                customName = "Mechanized Cavalry Scout",
                baseStats = ModelStats(movement = 8, melee = 1, ranged = 0, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 60,
                battlekit = Battlekit()
            )
        )
    }
}
