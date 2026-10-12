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
 * Provider for "Heretic Legions" faction.
 * Fichas verificadas contra "Warbands of Trench Crusade" (páginas 108-115).
 */
class HereticLegionFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "heretic_legion",
        name = "Heretic Legion",
        description = "Forces of Hell, Chaos, and damnable corruption waging eternal war in the trenches.",
        specialRules = listOf(
            FactionRule(
                id = "rule_heretic_priest",
                name = "Heretic Priest",
                effectDescription = "A Heretic Legions Warband must include 1 Heretic Priest."
            ),
            FactionRule(
                id = "rule_heretic_blood",
                name = "Blood Sacrifices",
                effectDescription = "Perform dark rites to earn Blood Markers upon defeating enemies."
            )
        ),
        defaultStartingDucats = 700,
        variants = WarbandVariants.hereticLegions
    )

    override val defaultStartingDucats: Int = 700
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "heretic_priest",
                customName = "Heretic Priest",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 80,
                keywords = listOf("HERETIC", "ELITE", "LEADER", "TOUGH"),
                commandAbilities = listOf("Puppet Master")
            ),
            EliteMember(
                id = "heretic_death_commando",
                customName = "Death Commando",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 90,
                keywords = listOf("HERETIC", "ELITE", "INFILTRATOR"),
                specialSkills = listOf("Stealth Generator", "Hide")
            ),
            EliteMember(
                id = "heretic_chorister",
                customName = "Chorister",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = -2, armor = 0, maxWounds = 1),
                baseCostInDucats = 65,
                keywords = listOf("HERETIC", "ELITE", "FEAR"),
                specialSkills = listOf("Unholy Hymns")
            ),
            TrooperMember(
                id = "heretic_trooper",
                customName = "Heretic Trooper",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 30,
                keywords = listOf("HERETIC")
            ),
            // Legionnaires of Hell (p.111): un Heretic Trooper puede ascender a Heretic
            // Legionnaire por +10 Ducados, subiendo Ranged O Melee de 0 a +1. Se modela
            // como dos entradas equivalentes para poder expresar la elección de perfil.
            TrooperMember(
                id = "heretic_legionnaire_ranged",
                customName = "Heretic Legionnaire (Ranged)",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 40,
                keywords = listOf("HERETIC"),
                specialRules = listOf("Legionnaires of Hell: no puede haber más Heretic Legionnaires que Heretic Troopers")
            ),
            TrooperMember(
                id = "heretic_legionnaire_melee",
                customName = "Heretic Legionnaire (Melee)",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 40,
                keywords = listOf("HERETIC"),
                specialRules = listOf("Legionnaires of Hell: no puede haber más Heretic Legionnaires que Heretic Troopers")
            ),
            TrooperMember(
                id = "heretic_war_wolf",
                customName = "War Wolf Assault Beast",
                baseStats = ModelStats(movement = 8, melee = 2, ranged = 0, armor = -3, maxWounds = 1),
                baseCostInDucats = 145,
                keywords = listOf("HERETIC", "ARTIFICIAL", "FEAR", "TOUGH"),
                specialRules = listOf("Assault Beast", "Loping Dash", "Chainsaw Mouth", "Shredding Claws")
            ),
            TrooperMember(
                id = "heretic_wretched",
                customName = "Wretched",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = -1, armor = 0, maxWounds = 1),
                baseCostInDucats = 25,
                keywords = listOf("HERETIC"),
                specialRules = listOf("Chattel", "Dark Blessing", "Law of Hell")
            ),
            TrooperMember(
                id = "heretic_anointed_heavy_infantry",
                customName = "Anointed Heavy Infantry",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = -2, maxWounds = 1),
                baseCostInDucats = 95,
                keywords = listOf("HERETIC", "STRONG"),
                specialRules = listOf("Reinforced Armour", "Infernal Brand")
            ),
            TrooperMember(
                id = "heretic_artillery_witch",
                customName = "Artillery Witch",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 100,
                keywords = listOf("HERETIC", "ARTIFICIAL", "NEGATE FEAR", "NEGATE GAS"),
                specialRules = listOf("Abiotic Life", "Artillery Witch Battery", "Levitate", "Infernal Bomb")
            )
        )
    }
}