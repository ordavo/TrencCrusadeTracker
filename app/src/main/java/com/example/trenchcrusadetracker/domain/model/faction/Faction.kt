package com.example.trenchcrusadetracker.domain.model.faction

import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

data class FactionRule(
    val id: String,
    val name: String,
    val effectDescription: String
)

/**
 * Variante (subfacción) de una facción base, tal y como aparecen en el libro de
 * "Warbands of Trench Crusade" (p. ej. Papal States Intervention Force).
 *
 * Por defecto reutiliza el roster y la armería de su facción base, pero una variante
 * puede declarar su propio [units] y resolver su propia [armoryId] cuando el libro le
 * da entradas de banda o Battlekit únicos (p. ej. The Great Hunger, Dirge of the Great
 * Hegemon o los Naval Raiders).
 */
data class FactionVariant(
    val id: String,
    val name: String,
    val description: String,
    val specialRules: List<FactionRule> = emptyList(),
    val defaultStartingDucats: Int = 700,
    val defaultStartingGloryPoints: Int = 0,
    val units: List<WarbandMember> = emptyList(),
    val armoryId: String? = null
)

data class Faction(
    val id: String,
    val name: String,
    val description: String,
    val specialRules: List<FactionRule> = emptyList(),
    val defaultStartingDucats: Int = 700,
    val defaultStartingGloryPoints: Int = 0,
    val variants: List<FactionVariant> = emptyList()
)
