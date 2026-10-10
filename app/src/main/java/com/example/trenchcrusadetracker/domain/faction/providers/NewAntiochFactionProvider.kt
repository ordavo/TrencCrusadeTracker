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
 * Fichas verificadas contra "Warbands of Trench Crusade" (páginas 26-33).
 */
class NewAntiochFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "new_antioch",
        name = "Principality of New Antioch",
        description = "The bastion of faith and military technology standing firm against the Forces of Hell.",
        specialRules = listOf(
            FactionRule(
                id = "rule_antioch_officer",
                name = "Officers Selection",
                effectDescription = "A New Antioch Warband must include 1 Lieutenant."
            ),
            FactionRule(
                id = "rule_antioch_fireteams",
                name = "Expert Fireteams",
                effectDescription = "A New Antioch Warband can include up to 2 Fireteams, each consisting of a pair of aligned models."
            )
        ),
        defaultStartingDucats = 700,
        variants = WarbandVariants.newAntioch
    )

    override val defaultStartingDucats: Int = 700
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "antioch_lieutenant",
                customName = "Lieutenant",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 70,
                keywords = listOf("NEW ANTIOCH", "ELITE", "LEADER", "TOUGH"),
                commandAbilities = listOf("Hold Your Fire!")
            ),
            EliteMember(
                id = "antioch_sniper_priest",
                customName = "Sniper Priest",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 50,
                keywords = listOf("NEW ANTIOCH", "ELITE"),
                specialSkills = listOf("Absolute Faith", "Aim")
            ),
            EliteMember(
                id = "antioch_trench_cleric",
                customName = "Trench Cleric",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 60,
                keywords = listOf("NEW ANTIOCH", "ELITE", "NEGATE FEAR"),
                specialSkills = listOf("God is With Us!", "Onward Christian Soldiers!")
            ),
            TrooperMember(
                id = "antioch_yeoman",
                customName = "Yeoman",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 30,
                keywords = listOf("NEW ANTIOCH")
            ),
            TrooperMember(
                id = "antioch_shock_trooper",
                customName = "Shock Trooper",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 45,
                keywords = listOf("NEW ANTIOCH"),
                specialRules = listOf("Assault Drill", "Shock Charge")
            ),
            TrooperMember(
                id = "antioch_combat_engineer",
                customName = "Combat Engineer",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = -2, maxWounds = 1),
                baseCostInDucats = 80,
                keywords = listOf("NEW ANTIOCH"),
                specialRules = listOf("Battlefield Demolition", "Defuse Mine", "Fortify")
            ),
            TrooperMember(
                id = "antioch_mechanized_heavy_infantry",
                customName = "Mechanized Heavy Infantry",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 1, armor = -2, maxWounds = 1),
                baseCostInDucats = 85,
                keywords = listOf("NEW ANTIOCH", "STRONG")
            ),
            TrooperMember(
                id = "antioch_combat_medic",
                customName = "Combat Medic",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = -1, maxWounds = 1),
                baseCostInDucats = 65,
                keywords = listOf("NEW ANTIOCH", "NEGATE FEAR"),
                specialRules = listOf("Expert Medic", "Finish the Fallen")
            )
        )
    }
}