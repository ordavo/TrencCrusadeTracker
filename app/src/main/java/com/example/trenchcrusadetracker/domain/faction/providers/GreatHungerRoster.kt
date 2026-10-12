package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.model.warband.EliteMember
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import com.example.trenchcrusadetracker.domain.model.warband.MovementType
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

/**
 * Roster propio de la variante "The Great Hunger" (Cult of the Black Grail).
 * Fuente: The-Great-Hunger-1.0.2.pdf (Great Hunger Warband Entries).
 *
 * Sustituye por completo al roster base de Black Grail: la banda NO puede incluir
 * Lord of Tumours, Corpse Guards, Grail Thralls, Heralds of Beelzebub ni Amalgam.
 */
internal object GreatHungerRoster {

    fun units(): List<WarbandMember> = listOf(
        LeaderMember(
            id = "gh_matagot_hag",
            customName = "Matagot Hag",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 135,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "LEADER", "NEGATE GAS", "TOUGH"),
            specialRules = listOf("Cadre of Flesh", "Frenzied Followers", "Mother's Call", "Pestilent", "Undead Fortitude"),
            commandAbilities = listOf("Mother's Call")
        ),
        EliteMember(
            id = "gh_butcher_knight",
            customName = "Butcher Knight",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 60,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "STRONG"),
            specialSkills = listOf("Ravenous Infection", "Undead Fortitude"),
            specialRules = listOf("Butcher Knights: puede recibir uno de los rangos propios del Great Hunger (Butcher King, Knight Companion of the Feast o Knight of Twin Cleavers).")
        ),
        // Plague Knight Ranks propios del Great Hunger (pp.6 del suplemento).
        EliteMember(
            id = "gh_butcher_king",
            customName = "Butcher King",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 70,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "STRONG"),
            specialSkills = listOf("Ravenous Infection", "Butcher King", "Undead Fortitude"),
            specialRules = listOf("Butcher King: al final de la batalla, si no está Out of Action y ha eliminado al menos un enemigo en Melee, gana 1 ☼.")
        ),
        EliteMember(
            id = "gh_knight_companion_of_the_feast",
            customName = "Knight Companion of the Feast",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 65,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "STRONG"),
            specialSkills = listOf("Ravenous Infection", "Knight Companion of the Feast", "Undead Fortitude"),
            specialRules = listOf("Knight Companion of the Feast: +1 DICE al Risky Success Roll de una Ravenous Infection ACTION de un aliado a 3\".")
        ),
        EliteMember(
            id = "gh_knight_of_twin_cleavers",
            customName = "Knight of Twin Cleavers",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 65,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "STRONG"),
            specialSkills = listOf("Ravenous Infection", "Knight of Twin Cleavers", "Undead Fortitude"),
            specialRules = listOf("Knight of Twin Cleavers: tiene IGNORE OFF-HAND y sus Melee Attacks tienen el keyword SHRAPNEL.")
        ),
        EliteMember(
            id = "gh_desiccated_husk",
            customName = "Desiccated Husk",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 1, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 55,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS"),
            specialSkills = listOf("More Worm Than Man", "Parasitic Tick", "Undead Fortitude"),
            specialRules = listOf("Desiccated Husk: usa la entrada Corpse Guard; sus Melee Attacks tienen el keyword CRITICAL y sustituye Bodyguard por More Worm Than Man.")
        ),
        EliteMember(
            id = "gh_great_maw",
            customName = "Great Maw",
            baseStats = ModelStats(movement = 6, movementType = MovementType.INFANTRY, melee = 4, ranged = 1, armor = 0, maxWounds = 1),
            baseCostInDucats = 130,
            keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "STRONG", "TOUGH"),
            specialSkills = listOf("Crushing Blows", "Undead Fortitude")
        ),
        TrooperMember(
            id = "gh_ravenous",
            customName = "Ravenous",
            baseStats = ModelStats(movement = 5, movementType = MovementType.INFANTRY, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 35,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS"),
            specialRules = listOf("Gluttonous Horde", "Ravenous Infection", "Undead Fortitude")
        ),
        TrooperMember(
            id = "gh_cradle_thrall",
            customName = "Cradle Thrall",
            baseStats = ModelStats(movement = 5, movementType = MovementType.INFANTRY, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 0,
            baseCostInGloryPoints = 2,
            keywords = listOf("BLACK GRAIL", "FEAR", "NEGATE GAS", "INFILTRATOR"),
            specialRules = listOf("Gluttonous Horde", "Ravenous Infection", "Undead Fortitude")
        ),
        TrooperMember(
            id = "gh_gregori_gula",
            customName = "Gregori Gula",
            baseStats = ModelStats(movement = 9, movementType = MovementType.FLYING, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
            baseCostInDucats = 140,
            keywords = listOf("BLACK GRAIL", "FEAR", "INFILTRATOR", "NEGATE GAS"),
            specialRules = listOf("Dormant Hunger", "Gnashing and Tearing", "Plague-Ridden Flesh", "Unholy Gut (Vomitus: 40 Ducados)")
        )
    )
}
