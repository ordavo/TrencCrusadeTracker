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
 * Provider for "Heretic Legion" faction.
 */
class HereticLegionFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "heretic_legion",
        name = "Heretic Legion",
        description = "Forces of Hell, Chaos, and damnable corruption waging eternal war in the trenches.",
        specialRules = listOf(
            FactionRule(
                id = "rule_heretic_blood",
                name = "Blood Sacrifices",
                effectDescription = "Perform dark rites to earn Blood Markers upon defeating enemies."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "heretic_priest",
                customName = "Heretic Priest",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 85,
                battlekit = Battlekit(),
                commandAbilities = listOf("Dark Chant", "Unholy Blessing")
            ),
            EliteMember(
                id = "heretic_chorister",
                customName = "Chorister of Hell",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 60,
                battlekit = Battlekit(),
                specialSkills = listOf("Infernal Chant")
            ),
            EliteMember(
                id = "heretic_hellknight",
                customName = "Hellknight",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = 0, courage = 2, maxWounds = 1),
                baseCostInDucats = 80,
                battlekit = Battlekit(),
                specialSkills = listOf("Unholy Might")
            ),
            TrooperMember(
                id = "heretic_trooper",
                customName = "Heretic Trooper",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, courage = 0, maxWounds = 1),
                baseCostInDucats = 30,
                battlekit = Battlekit()
            )
        )
    }
}
