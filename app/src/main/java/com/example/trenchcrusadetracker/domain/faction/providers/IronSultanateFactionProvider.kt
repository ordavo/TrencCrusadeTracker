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
 * Provider for "Sultanate of the Iron Wall" (Iron Sultanate) faction.
 * Fichas verificadas contra "Warbands of Trench Crusade" (páginas 80-87).
 */
class IronSultanateFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "iron_sultanate",
        name = "Iron Sultanate",
        description = "Defenders of the Iron Wall, masters of alchemy, naphtha fire, and heavy artillery.",
        specialRules = listOf(
            FactionRule(
                id = "rule_sultanate_yuzbasi",
                name = "Yüzbaşı",
                effectDescription = "An Iron Sultanate Warband must include 1 Yüzbaşı."
            ),
            FactionRule(
                id = "rule_sultanate_alchemy",
                name = "Alchemical Precision",
                effectDescription = "Masters of alchemy forge peerless war machines and alchemical weapons."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "sultanate_yuzbasi",
                customName = "Yüzbaşı",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 70,
                keywords = listOf("SULTANATE", "ELITE", "LEADER", "NEGATE FEAR", "TOUGH"),
                commandAbilities = listOf("Janissary Veteran", "Counter-Charge", "Mubarizun")
            ),
            EliteMember(
                id = "sultanate_jabirean_alchemist",
                customName = "Jabirean Alchemist",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 55,
                keywords = listOf("SULTANATE", "ELITE"),
                specialSkills = listOf("Mastery of the Elements", "Elemental Change")
            ),
            EliteMember(
                id = "sultanate_assassin",
                customName = "Sultanate Assassin",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 85,
                keywords = listOf("SULTANATE", "ELITE", "INFILTRATOR"),
                specialSkills = listOf("Temporal Assassin", "Time Slip")
            ),
            TrooperMember(
                id = "sultanate_azeb",
                customName = "Azeb",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 25,
                keywords = listOf("SULTANATE"),
                specialRules = listOf("Light Skirmishers")
            ),
            TrooperMember(
                id = "sultanate_sapper",
                customName = "Sultanate Sapper",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 50,
                keywords = listOf("SULTANATE"),
                specialRules = listOf("Set Mine", "Defuse Mine", "Fortify", "Forward Positions")
            ),
            TrooperMember(
                id = "sultanate_lion_of_jabir",
                customName = "Lion of Jabir",
                baseStats = ModelStats(movement = 8, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 60,
                keywords = listOf("SULTANATE", "ARTIFICIAL"),
                specialRules = listOf("Agile", "Artificial Life", "Teeth and Claws", "Pin")
            ),
            TrooperMember(
                id = "sultanate_janissary",
                customName = "Janissary",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 55,
                keywords = listOf("SULTANATE", "STRONG"),
                specialRules = listOf("Counter-Charge")
            ),
            TrooperMember(
                id = "sultanate_brazen_bull",
                customName = "Brazen Bull",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 100,
                keywords = listOf("SULTANATE", "ARTIFICIAL", "FEAR", "STRONG", "TOUGH"),
                specialRules = listOf("Artificial Life", "Trample")
            )
        )
    }
}