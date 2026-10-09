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
 * Provider for "Heretic Naval Raiders" faction.
 * Variante de Heretic Legions; fichas verificadas contra el libro
 * "Heretic-Naval-Raiders-Warband" (páginas 21-34).
 */
class HereticNavalRaidersFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "heretic_naval_raiders",
        name = "Heretic Naval Raiders",
        description = "Corsairs and naval raiders dedicated to soul-harvesting and maritime plunder.",
        specialRules = listOf(
            FactionRule(
                id = "rule_naval_captain",
                name = "Heretic Captain",
                effectDescription = "A Heretic Naval Raiders Warband must include 1 Heretic Captain."
            ),
            FactionRule(
                id = "rule_naval_fast",
                name = "Fast as Lightning",
                effectDescription = "Add +1 DICE to Dash ACTION rolls for models in the Warband."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "naval_heretic_captain",
                customName = "Heretic Captain",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 80,
                keywords = listOf("HERETIC", "ELITE", "LEADER", "TOUGH"),
                commandAbilities = listOf("Puppet Master")
            ),
            EliteMember(
                id = "naval_abyssal_commando",
                customName = "Abyssal Commando",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 90,
                keywords = listOf("HERETIC", "ELITE", "INFILTRATOR"),
                specialSkills = listOf("Abyssal Armour", "Fade", "Mist-Shrouded")
            ),
            EliteMember(
                id = "naval_drowned_chorister",
                customName = "Drowned Chorister",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = -2, armor = 0, maxWounds = 1),
                baseCostInDucats = 65,
                keywords = listOf("HERETIC", "ELITE", "FEAR"),
                specialSkills = listOf("Leviathan Bell", "Unholy Hymns")
            ),
            TrooperMember(
                id = "naval_heretic_raider",
                customName = "Heretic Raider",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 30,
                keywords = listOf("HERETIC"),
                specialRules = listOf("Heretic Raider Legionnaires")
            ),
            TrooperMember(
                id = "naval_wretched",
                customName = "Wretched",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = -1, armor = 0, maxWounds = 1),
                baseCostInDucats = 25,
                keywords = listOf("HERETIC"),
                specialRules = listOf("Chattel", "Dark Blessing", "Law of Hell")
            ),
            TrooperMember(
                id = "naval_anointed_raider",
                customName = "Anointed Heretic Raider",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = -2, maxWounds = 1),
                baseCostInDucats = 95,
                keywords = listOf("HERETIC", "STRONG"),
                specialRules = listOf("Reinforced Armour", "Infernal Brand")
            ),
            TrooperMember(
                id = "naval_sea_hag",
                customName = "Sea Hag",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 100,
                keywords = listOf("HERETIC", "ARTIFICIAL", "NEGATE FEAR", "NEGATE GAS"),
                specialRules = listOf("Abiotic Life", "Levitate", "Infernal Bombs")
            )
        )
    }
}