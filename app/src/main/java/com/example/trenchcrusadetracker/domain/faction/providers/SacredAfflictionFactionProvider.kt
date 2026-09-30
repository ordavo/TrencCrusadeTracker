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
 */
class SacredAfflictionFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "sacred_affliction",
        name = "Procession of the Sacred Affliction",
        description = "Holy penitents and plague-bearers who channel pain and divine affliction into martial fervor.",
        specialRules = listOf(
            FactionRule(
                id = "rule_sacred_pain",
                name = "Sacred Affliction",
                effectDescription = "Wounds suffered increase melee damage dealt."
            )
        ),
        defaultStartingDucats = 500
    )

    override val defaultStartingDucats: Int = 500
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            LeaderMember(
                id = "affliction_penitent_master",
                customName = "Penitent Master",
                baseStats = ModelStats.CaptainBase,
                baseCostInDucats = 85,
                battlekit = Battlekit(),
                commandAbilities = listOf("Chant of Sufferance", "Holy Plague")
            ),
            EliteMember(
                id = "affliction_zealot",
                customName = "Afflicted Zealot",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, courage = 2, maxWounds = 1),
                baseCostInDucats = 55,
                battlekit = Battlekit(),
                specialSkills = listOf("Fervent Pain")
            ),
            TrooperMember(
                id = "affliction_flagellant",
                customName = "Afflicted Penitent",
                baseStats = ModelStats(movement = 6, melee = 1, ranged = 0, armor = 0, courage = 1, maxWounds = 1),
                baseCostInDucats = 28,
                battlekit = Battlekit()
            )
        )
    }
}
