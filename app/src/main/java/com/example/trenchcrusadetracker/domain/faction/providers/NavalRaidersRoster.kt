package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.model.warband.EliteMember
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import com.example.trenchcrusadetracker.domain.model.warband.MovementType
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

/**
 * Roster propio de la variante "Heretic Naval Raiders" (Heretic Legions, Fallen).
 * Fuente: "Heretic-Naval-Raiders-War"-suplemento (Warband Entries).
 *
 * Sustituye por completo al roster base de los Heretic Legions.
 */
internal object NavalRaidersRoster {

    fun units(): List<WarbandMember> = listOf(
        LeaderMember(
            id = "naval_heretic_captain",
            customName = "Heretic Captain",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
            baseCostInDucats = 80,
            keywords = listOf("HERETIC", "ELITE", "LEADER", "TOUGH"),
            commandAbilities = listOf("Puppet Master")
        ),
        EliteMember(
            id = "naval_abyssal_commando",
            customName = "Abyssal Commando",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 2, ranged = 1, armor = 0, maxWounds = 1),
            baseCostInDucats = 90,
            keywords = listOf("HERETIC", "ELITE", "INFILTRATOR"),
            specialSkills = listOf("Abyssal Armour (+5 Ducados sobre Reinforced Armour)", "Fade ACTION", "Mist-Shrouded")
        ),
        EliteMember(
            id = "naval_drowned_chorister",
            customName = "Drowned Chorister",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 2, ranged = -2, armor = 0, maxWounds = 1),
            baseCostInDucats = 65,
            keywords = listOf("HERETIC", "ELITE", "FEAR"),
            specialSkills = listOf("Leviathan Bell (10 Ducados)", "Unholy Hymns")
        ),
        EliteMember(
            id = "naval_sea_hag",
            customName = "Sea Hag",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 100,
            keywords = listOf("HERETIC", "ARTIFICIAL", "NEGATE FEAR", "NEGATE GAS"),
            specialSkills = listOf("Abiotic Life", "Levitate", "Infernal Bombs (Infernal Mines +10 Ducados)")
        ),
        TrooperMember(
            id = "naval_heretic_raider",
            customName = "Heretic Raider",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 30,
            keywords = listOf("HERETIC"),
            specialRules = listOf("Heretic Raider Legionnaires (+10 Ducados: +1 DICE a Ranged o Melee)")
        ),
        TrooperMember(
            id = "naval_wretched",
            customName = "Wretched",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = -1, ranged = -1, armor = 0, maxWounds = 1),
            baseCostInDucats = 25,
            keywords = listOf("HERETIC"),
            specialRules = listOf("Chattel", "Dark Blessing", "Law of Hell")
        ),
        TrooperMember(
            id = "naval_anointed_raider",
            customName = "Anointed Heretic Raider",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 1, ranged = 1, armor = -2, maxWounds = 1),
            baseCostInDucats = 95,
            keywords = listOf("HERETIC", "STRONG"),
            specialRules = listOf("Reinforced Armour + Infernal Brand incluidos (no removibles)")
        )
    )
}
