package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.faction.FactionProvider
import com.example.trenchcrusadetracker.domain.model.faction.Faction
import com.example.trenchcrusadetracker.domain.model.faction.FactionRule
import com.example.trenchcrusadetracker.domain.model.warband.Battlekit
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

/**
 * Provider for "The Court of the Seven-Headed Serpent" faction.
 * Fallen pueden portar SIN MARKERS y lanzar Goetic Spells.
 * Fichas verificadas contra "October-Rules-Review-2026.pdf" (reglas actualizadas) y
 * "Warbands of Trench Crusade" (páginas 145-167). Las campañas del Court comienzan con 700 👑.
 */
class TheCourtFactionProvider : FactionProvider {
    override val faction: Faction = Faction(
        id = "the_court",
        name = "The Court of the Seven-Headed Serpent",
        description = "Demonic nobles of Dis and the legions of the Infernal War Council of Hell.",
        specialRules = listOf(
            FactionRule(
                id = "rule_court_seven_sins",
                name = "Seven Deadly Sins",
                effectDescription = "Pledge the Warband to Wrath, Envy, Lust, Pride, Sloth, Gluttony or Greed before recruiting."
            ),
            FactionRule(
                id = "rule_court_sin_markers",
                name = "SIN MARKERS",
                effectDescription = "Fallen models gain SIN MARKERS to fuel Goetic Spells (max 6 per model)."
            ),
            FactionRule(
                id = "rule_court_mortal_suffering",
                name = "Mortal Suffering",
                effectDescription = "DEMONIC models can place a SIN MARKER instead of a BLOOD MARKER."
            )
        ),
        defaultStartingDucats = 700,
        variants = WarbandVariants.court
    )

    override val defaultStartingDucats: Int = 700
    override val defaultStartingGloryPoints: Int = 0

    override fun getAvailableUnits(): List<WarbandMember> {
        return listOf(
            TheCourtRoster.praetor(),
            TheCourtRoster.sorcerer(),
            TheCourtRoster.hellKnight(),
            TheCourtRoster.hunter(),
            TheCourtRoster.wretched(),
            TheCourtRoster.pitLocust(),
            TheCourtRoster.yokeFiend(),
            TheCourtRoster.desecratedSaint()
        )
    }
}