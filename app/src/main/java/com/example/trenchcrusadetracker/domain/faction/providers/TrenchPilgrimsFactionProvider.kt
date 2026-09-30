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
 * Provider for "Trench Pilgrims" faction.
 */
class TrenchPilgrimsFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "trench_pilgrims",
        name = "Trench Pilgrims",
        description = "Religious zealots, iconoclasts, and flagellants marching blindly into the gunfire.",
        specialRules = listOf(
            FactionRule(
                id = "rule_zealotry",
                name = "Martyrdom",
                effectDescription = "When a friendly model is slain, nearby allies gain attack modifiers."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "pilgrim_war_prophet",
                customName = "War Prophet",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 95,
                battlekit = Battlekit(),
                commandAbilities = listOf("Fervent Sermon", "Divine Protection")
            ),
            EliteMember(
                id = "pilgrim_communicant",
                customName = "Communicant Giant",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = 0, courage = 2, maxWounds = 1),
                baseCostInDucats = 85,
                battlekit = Battlekit(),
                specialSkills = listOf("Giant Might")
            ),
            TrooperMember(
                id = "pilgrim_flagellant",
                customName = "Trench Pilgrim",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 25,
                battlekit = Battlekit()
            )
        )
    }
}
