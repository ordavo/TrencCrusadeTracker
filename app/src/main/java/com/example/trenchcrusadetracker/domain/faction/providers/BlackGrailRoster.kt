package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.model.warband.EliteMember
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import com.example.trenchcrusadetracker.domain.model.warband.MovementType
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

/**
 * Roster base de "The Cult of the Black Grail".
 * Fuente: "Warbands of Trench Crusade" (páginas 126-139).
 *
 * Se comparte entre el provider base y la variante "Dirge of the Great Hegemon"
 * (que reemplaza algunas entradas por las suyas: Executor y Bereaved).
 */
internal object BlackGrailRoster {

    fun base(): List<WarbandMember> = listOf(
        LeaderMember(
            id = "grail_lord_of_tumours",
            customName = "Lord of Tumours",
            baseStats = ModelStats(movement = 6, melee = 4, ranged = 1, armor = 0, maxWounds = 1),
            baseCostInDucats = 130,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "LEADER", "NEGATE GAS", "STRONG", "TOUGH"),
            commandAbilities = listOf("Beelzebub's Touch", "Crushing Blows", "Undead Fortitude")
        ),
        EliteMember(
            id = "grail_plague_knight",
            customName = "Plague Knight",
            baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 60,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "STRONG"),
            specialSkills = listOf("Undead Fortitude", "Plague Knight Ranks")
        ),
        EliteMember(
            id = "grail_corpse_guard",
            customName = "Corpse Guard",
            baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 55,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS"),
            specialSkills = listOf("Bodyguard", "Parasitic Tick", "Undead Fortitude")
        ),
        TrooperMember(
            id = "grail_grail_thrall",
            customName = "Grail Thrall",
            baseStats = ModelStats(movement = 5, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 25,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS"),
            specialRules = listOf("Overwhelming Horde", "Undead Fortitude")
        ),
        TrooperMember(
            id = "grail_fly_thrall",
            customName = "Fly Thrall",
            baseStats = ModelStats(movement = 6, movementType = MovementType.FLYING, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 25,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS"),
            specialRules = listOf("Overwhelming Horde")
        ),
        TrooperMember(
            id = "grail_hound",
            customName = "Hound of the Black Grail",
            baseStats = ModelStats(movement = 8, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 55,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS"),
            specialRules = listOf("Disease Carrier", "Frightening Speed", "Infested Carcasses (+5 Ducados)", "Teeth & Claws", "Undead Fortitude")
        ),
        TrooperMember(
            id = "grail_herald_of_beelzebub",
            customName = "Herald of Beelzebub",
            baseStats = ModelStats(movement = 10, movementType = MovementType.FLYING, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 50,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS", "SKIRMISHER"),
            specialRules = listOf("Infected Proboscis", "Maddening Buzzing (+10 Ducados)")
        ),
        TrooperMember(
            id = "grail_amalgam",
            customName = "Amalgam",
            baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 140,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS", "STRONG", "TOUGH"),
            specialRules = listOf("Six-armed Monstrosity", "Corpulent", "Trample", "Unstoppable", "Undead Fortitude")
        )
    )

    /**
     * Roster de la variante "Dirge of the Great Hegemon".
     * No permite Lord of Tumours ni Amalgam; añade el Executor y renombra los
     * Thralls a "Bereaved" (Ranged +0 y 30 Ducados).
     */
    fun dirge(): List<WarbandMember> = listOf(
        EliteMember(
            id = "dirge_executor",
            customName = "Executor",
            baseStats = ModelStats(movement = 6, melee = 2, ranged = 1, armor = 0, maxWounds = 1),
            baseCostInDucats = 80,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "STRONG", "TOUGH"),
            specialSkills = listOf("Undead Fortitude", "Command Bereaved", "Plague Knight Ranks")
        ),
        EliteMember(
            id = "grail_plague_knight",
            customName = "Plague Knight",
            baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 60,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "STRONG"),
            specialSkills = listOf("Undead Fortitude", "Plague Knight Ranks", "Command Bereaved")
        ),
        EliteMember(
            id = "grail_corpse_guard",
            customName = "Corpse Guard",
            baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 55,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS"),
            specialSkills = listOf("Bodyguard", "Parasitic Tick", "Undead Fortitude")
        ),
        TrooperMember(
            id = "dirge_bereaved_grail",
            customName = "Bereaved (Grail Thrall)",
            baseStats = ModelStats(movement = 5, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 30,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS"),
            specialRules = listOf("Overwhelming Horde", "Undead Fortitude")
        ),
        TrooperMember(
            id = "dirge_bereaved_fly",
            customName = "Bereaved (Fly Thrall)",
            baseStats = ModelStats(movement = 6, movementType = MovementType.FLYING, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 30,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS"),
            specialRules = listOf("Overwhelming Horde")
        ),
        TrooperMember(
            id = "grail_hound",
            customName = "Hound of the Black Grail",
            baseStats = ModelStats(movement = 8, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 55,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS"),
            specialRules = listOf("Disease Carrier", "Frightening Speed", "Infested Carcasses (+5 Ducados)", "Teeth & Claws", "Undead Fortitude")
        ),
        TrooperMember(
            id = "grail_herald_of_beelzebub",
            customName = "Herald of Beelzebub",
            baseStats = ModelStats(movement = 10, movementType = MovementType.FLYING, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 50,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS", "SKIRMISHER"),
            specialRules = listOf("Infected Proboscis", "Maddening Buzzing (+10 Ducados)")
        )
    )
}
