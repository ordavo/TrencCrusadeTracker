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
 * Provider for "Procession of the Sacred Affliction" faction.
 * Variante de Trench Pilgrims; fichas verificadas contra el libro
 * "Procession-of-the-Sacred-Affliction-Warband" (páginas 22-35).
 */
class SacredAfflictionFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "sacred_affliction",
        name = "Procession of the Sacred Affliction",
        description = "Holy penitents and plague-bearers who channel pain and divine affliction into martial fervor.",
        specialRules = listOf(
            FactionRule(
                id = "rule_affliction_healing",
                name = "Gift of the Sacrament",
                effectDescription = "The Lazarist Prophet removes BLOOD or INFECTION MARKERS from allies."
            ),
            FactionRule(
                id = "rule_affliction_regeneration",
                name = "Blessed Affliction",
                effectDescription = "The afflicted regenerate, replacing BLOOD MARKERS with BLESSING MARKERS."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "affliction_lazarist_prophet",
                customName = "Lazarist Prophet",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 2, armor = 0, maxWounds = 1),
                baseCostInDucats = 90,
                keywords = listOf("PILGRIM", "ELITE", "LEADER"),
                commandAbilities = listOf("Gift of the Sacrament", "Loudspeakers", "Memento Mori")
            ),
            EliteMember(
                id = "affliction_lazarist_communicant",
                customName = "Lazarist Communicant",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = -3, armor = 0, maxWounds = 1),
                baseCostInDucats = 100,
                keywords = listOf("PILGRIM", "ELITE", "REGENERATE 1", "STRONG", "TOUGH"),
                specialSkills = listOf("Bodyguard", "The Communicant Cross or Helm")
            ),
            EliteMember(
                id = "affliction_lazarist_castigator",
                customName = "Lazarist Castigator",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 50,
                keywords = listOf("PILGRIM", "ELITE"),
                specialSkills = listOf("Enforced Orthodoxy", "Whip of God", "Zealot Strength")
            ),
            TrooperMember(
                id = "affliction_leper_pilgrim",
                customName = "Leper-Pilgrim",
                baseStats = ModelStats(movement = 6, melee = 0, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 30,
                keywords = listOf("PILGRIM"),
                specialRules = listOf("Resurrection", "Zealot Strength")
            ),
            TrooperMember(
                id = "affliction_stigmatic_nun",
                customName = "Stigmatic Nun",
                baseStats = ModelStats(movement = 8, melee = 1, ranged = 1, armor = 0, maxWounds = 1),
                baseCostInDucats = 60,
                keywords = listOf("PILGRIM", "REGENERATE 1"),
                specialRules = listOf("Agile", "Blessed Stigmata")
            ),
            TrooperMember(
                id = "affliction_ecclesiastic_prisoner",
                customName = "Ecclesiastic Prisoner",
                baseStats = ModelStats(movement = 6, melee = -1, ranged = 0, armor = 0, maxWounds = 1),
                baseCostInDucats = 20,
                keywords = listOf("PILGRIM"),
                specialRules = listOf("Awaited", "Feeble Flailing", "Mad Dash", "Martyrdom Device")
            ),
            TrooperMember(
                id = "affliction_anchorite_shrine",
                customName = "Anchorite Shrine",
                baseStats = ModelStats(movement = 6, melee = 2, ranged = 0, armor = -3, maxWounds = 1),
                baseCostInDucats = 140,
                keywords = listOf("PILGRIM", "FEAR", "NEGATE SHRAPNEL", "STRONG", "TOUGH"),
                specialRules = listOf("Broken on the Wheel", "Symphony of Slaughter")
            )
        )
    }
}