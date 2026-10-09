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
 * Provider for "Stosstruppen of the Free State of Prussia" faction.
 * Variante de New Antioch; fichas verificadas contra el libro
 * "Stosstruppen-Warband" (páginas 20-31).
 */
class PrussianStosstruppenFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "prussian_stosstruppen",
        name = "Prussian Stosstruppen",
        description = "Prussian trench shock troops specializing in rapid breach tactics and aggressive assault.",
        specialRules = listOf(
            FactionRule(
                id = "rule_prussian_fireteams",
                name = "Expert Fireteams",
                effectDescription = "A Stosstruppen Warband can include up to 3 Fireteams instead of 2."
            ),
            FactionRule(
                id = "rule_prussian_grenade",
                name = "Masters of the Grenade",
                effectDescription = "Add 4\" to the Range of all Grenades used by the Warband."
            ),
            FactionRule(
                id = "rule_prussian_troop_selection",
                name = "Stosstruppen Core",
                effectDescription = "The Warband must include 2-8 Stosstruppen and cannot include Trench Moles."
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
                customName = "Hauptmann",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 70,
                keywords = listOf("NEW ANTIOCH", "ELITE", "LEADER", "TOUGH"),
                commandAbilities = listOf("Hold Your Fire!", "Rapid Assault")
            ),
            EliteMember(
                id = "prussian_sniper_priest",
                customName = "Sniper Priest",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 50,
                keywords = listOf("NEW ANTIOCH", "ELITE"),
                specialSkills = listOf("Absolute Faith", "Aim")
            ),
            EliteMember(
                id = "prussian_feldkaplan",
                customName = "Feldkaplan",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 60,
                keywords = listOf("NEW ANTIOCH", "ELITE", "NEGATE FEAR"),
                specialSkills = listOf("God is With Us!", "Onward Christian Soldiers!")
            ),
            TrooperMember(
                id = "prussian_yeoman",
                customName = "Yeoman",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 30,
                keywords = listOf("NEW ANTIOCH")
            ),
            TrooperMember(
                id = "prussian_stosstrupp",
                customName = "Stosstrupp",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 45,
                keywords = listOf("NEW ANTIOCH"),
                specialRules = listOf("Shock Charge", "Rapid Assault")
            ),
            TrooperMember(
                id = "prussian_combat_engineer",
                customName = "Combat Engineer",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = -2, maxWounds = 1),
                baseCostInDucats = 80,
                keywords = listOf("NEW ANTIOCH", "NEGATE MINED"),
                specialRules = listOf("Battlefield Demolition", "Set Mine", "Defuse Mine", "Fortify")
            ),
            TrooperMember(
                id = "prussian_mechanized_heavy_infantry",
                customName = "Mechanized Heavy Infantry",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 1, armor = -2, maxWounds = 1),
                baseCostInDucats = 85,
                keywords = listOf("NEW ANTIOCH", "STRONG")
            ),
            TrooperMember(
                id = "prussian_combat_medic",
                customName = "Combat Medic",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = -1, maxWounds = 1),
                baseCostInDucats = 65,
                keywords = listOf("NEW ANTIOCH", "NEGATE FEAR"),
                specialRules = listOf("Expert Medic", "Finish the Fallen")
            )
        )
    }
}