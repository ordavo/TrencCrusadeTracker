package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.faction.FactionProvider
import com.example.trenchcrusadetracker.domain.model.faction.Faction
import com.example.trenchcrusadetracker.domain.model.faction.FactionRule
import com.example.trenchcrusadetracker.domain.model.warband.Battlekit
import com.example.trenchcrusadetracker.domain.model.warband.EliteMember
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import com.example.trenchcrusadetracker.domain.model.warband.MovementType
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

/**
 * Provider for "The Great Hunger" faction.
 * Variante de Black Grail; fichas verificadas contra el libro
 * "The-Great-Hunger-1.0.2" (páginas 20-31). Las campañas de Black Grail
 * comienzan con 700 Ducats.
 */
class GreatHungerFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "great_hunger",
        name = "The Great Hunger",
        description = "The rot-gorged plague-legion of Chemosh, agents of a hungry death.",
        specialRules = listOf(
            FactionRule(
                id = "rule_hunger_herald",
                name = "Heralds of the Great Hunger",
                effectDescription = "The Warband must include a Matagot Hag and follows the Black Grail warband rules."
            ),
            FactionRule(
                id = "rule_hunger_plague",
                name = "Blood-Drenched Harvest",
                effectDescription = "Infect GLORIOUS DEATHS to gain INFECTION MARKERS."
            )
        ),
        defaultStartingDucats = 700
    )

    override val defaultStartingDucats: Int = 700
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "hunger_matagot_hag",
                customName = "Matagot Hag",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 135,
                keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "LEADER", "NEGATE GAS", "TOUGH"),
                commandAbilities = listOf("Gift of the Great Hunger", "Loudspeakers")
            ),
            EliteMember(
                id = "hunger_ravenous",
                customName = "Ravenous",
                baseStats = ModelStats(movement = 5, melee = 0, ranged = -1, armor = 0, maxWounds = 1),
                baseCostInDucats = 35,
                keywords = listOf("BLACK GRAIL", "ELITE", "FEAR", "NEGATE GAS", "NEGATE FEAR"),
                specialSkills = listOf("Abiotic Existence", "Doom is Nigh", "Flesh is Weak", "Feed on the Dead", "Suppurating Wounds")
            ),
            TrooperMember(
                id = "hunger_gregori_gula",
                customName = "Gregori Gula",
                baseStats = ModelStats(movement = 9, melee = 2, ranged = 0, armor = 0, maxWounds = 1, movementType = MovementType.FLYING),
                baseCostInDucats = 140,
                keywords = listOf("BLACK GRAIL", "FEAR", "INFILTRATOR", "NEGATE GAS"),
                specialRules = listOf("Gut Lord", "Infernal Flight")
            )
        )
    }
}