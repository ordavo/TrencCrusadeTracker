package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.faction.FactionProvider
import com.example.trenchcrusadetracker.domain.model.faction.Faction
import com.example.trenchcrusadetracker.domain.model.faction.FactionRule
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

/**
 * Provider for "The Cult of the Black Grail" faction.
 * Fichas verificadas contra "Warbands of Trench Crusade" (páginas 126-140).
 * Las campañas del Black Grail comienzan con 700 👑. Es una facción Fallen.
 */
class BlackGrailFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "black_grail",
        name = "Cult of the Black Grail",
        description = "Vessels of Beelzebub's plague, spreading corruption through No Man's Land.",
        specialRules = listOf(
            FactionRule(
                id = "rule_grail_infection",
                name = "Infection Markers",
                effectDescription = "Some Black Grail weapons place INFECTION MARKERS (instead of BLOOD MARKERS) next to the target."
            ),
            FactionRule(
                id = "rule_grail_morale",
                name = "Morale",
                effectDescription = "The opponent adds -1 DICE to all Morale Checks unless their Warband is of the Court or the Black Grail."
            )
        ),
        defaultStartingDucats = 700,
        variants = WarbandVariants.blackGrail
    )

    override val defaultStartingDucats: Int = 700
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> = BlackGrailRoster.base()
}
