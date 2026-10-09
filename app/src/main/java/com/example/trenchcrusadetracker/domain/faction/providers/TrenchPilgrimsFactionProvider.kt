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
 * Fichas verificadas contra "Warbands of Trench Crusade" (páginas 52-58).
 */
class TrenchPilgrimsFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "trench_pilgrims",
        name = "Trench Pilgrims",
        description = "Religious zealots, iconoclasts, and flagellants marching blindly into the gunfire.",
        specialRules = listOf(
            FactionRule(
                id = "rule_pilgrim_war_prophet",
                name = "War Prophet",
                effectDescription = "A Trench Pilgrim Warband must include 1 War Prophet."
            ),
            FactionRule(
                id = "rule_pilgrim_martyrdom",
                name = "Martyrdom",
                effectDescription = "Fallen pilgrims are honoured through Resurrection and Martyrdom Devices."
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
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 80,
                keywords = listOf("PILGRIM", "ELITE", "LEADER"),
                commandAbilities = listOf("Loudspeakers", "Laying on of Hands", "Memento Mori")
            ),
            EliteMember(
                id = "pilgrim_communicant",
                customName = "Communicant",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = -3, armor = 0, maxWounds = 1),
                baseCostInDucats = 100,
                keywords = listOf("PILGRIM", "ELITE", "STRONG", "TOUGH"),
                specialSkills = listOf("Bodyguard", "Miracle of Regeneration", "The Communicant Cross")
            ),
            EliteMember(
                id = "pilgrim_castigator",
                customName = "Castigator",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 50,
                keywords = listOf("PILGRIM", "ELITE"),
                specialSkills = listOf("Enforced Orthodoxy", "Whip of God", "Zealot Strength")
            ),
            TrooperMember(
                id = "pilgrim_trench_pilgrim",
                customName = "Trench Pilgrim",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 30,
                keywords = listOf("PILGRIM"),
                specialRules = listOf("Resurrection")
            ),
            TrooperMember(
                id = "pilgrim_stigmatic_nun",
                customName = "Stigmatic Nun",
                baseStats = ModelStats(movement = 8, melee = 1, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 50,
                keywords = listOf("PILGRIM"),
                specialRules = listOf("Agile", "Blessed Stigmata")
            ),
            TrooperMember(
                id = "pilgrim_ecclesiastic_prisoner",
                customName = "Ecclesiastic Prisoner",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 20,
                keywords = listOf("PILGRIM"),
                specialRules = listOf("Awaited", "Feeble Flailing", "Mad Dash", "Martyrdom Device")
            ),
            TrooperMember(
                id = "pilgrim_anchorite_shrine",
                customName = "Anchorite Shrine",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = -3, maxWounds = 1),
                baseCostInDucats = 140,
                keywords = listOf("PILGRIM", "FEAR", "NEGATE SHRAPNEL", "STRONG", "TOUGH"),
                specialRules = listOf("Broken on the Wheel", "Symphony of Slaughter")
            )
        )
    }
}