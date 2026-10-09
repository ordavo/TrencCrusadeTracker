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
 * Provider for "The Red Brigade" faction.
 * Variante de New Antioch; fichas verificadas contra el libro "The-Red-Brigade" (v1.4).
 */
class RedBrigadeFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "red_brigade",
        name = "The Red Brigade",
        description = "Elite shock brigade characterized by iron discipline and distinctive red attire.",
        specialRules = listOf(
            FactionRule(
                id = "rule_red_no_retreat",
                name = "No Retreat",
                effectDescription = "A model in a Red Brigade Warband cannot take a Retreat ACTION."
            ),
            FactionRule(
                id = "rule_red_blood_pledge",
                name = "Wear and Tear",
                effectDescription = "The Warband starts each game with 1 BLOOD MARKER per 200 ducats of its total Cost."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "red_lieutenant",
                customName = "Lieutenant",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 70,
                keywords = listOf("NEW ANTIOCH", "ELITE", "LEADER", "TOUGH"),
                commandAbilities = listOf("Hold Your Fire!", "Gunslinger")
            ),
            EliteMember(
                id = "red_sniper_priest",
                customName = "Sniper Priest",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 50,
                keywords = listOf("NEW ANTIOCH", "ELITE"),
                specialSkills = listOf("Absolute Faith", "Aim")
            ),
            EliteMember(
                id = "red_trench_cleric",
                customName = "Trench Cleric",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 60,
                keywords = listOf("NEW ANTIOCH", "ELITE", "NEGATE FEAR"),
                specialSkills = listOf("God is With Us!", "Onward Christian Soldiers!")
            ),
            EliteMember(
                id = "red_crimson_communicant",
                customName = "Crimson Communicant",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = -1, armor = 0, maxWounds = 1),
                baseCostInDucats = 75,
                keywords = listOf("NEW ANTIOCH", "ELITE", "STRONG", "TOUGH"),
                specialSkills = listOf("Strength through Pain", "Atonement Bell")
            ),
            TrooperMember(
                id = "red_yeoman",
                customName = "Yeoman",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 30,
                keywords = listOf("NEW ANTIOCH")
            ),
            TrooperMember(
                id = "red_shock_trooper",
                customName = "Shock Trooper",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 45,
                keywords = listOf("NEW ANTIOCH"),
                specialRules = listOf("Assault Drill", "Shock Charge")
            ),
            TrooperMember(
                id = "red_combat_medic",
                customName = "Combat Medic",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = -1, maxWounds = 1),
                baseCostInDucats = 65,
                keywords = listOf("NEW ANTIOCH", "NEGATE FEAR"),
                specialRules = listOf("Expert Medic", "Finish the Fallen")
            ),
            TrooperMember(
                id = "red_trench_dog",
                customName = "Trench Dog of the Red Brigade",
                baseStats = ModelStats(movement = 8, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 35,
                keywords = listOf("NEW ANTIOCH"),
                specialRules = listOf("Dogs of War", "Four Paws", "Grenade Harness", "Teeth and Claws")
            )
        )
    }
}