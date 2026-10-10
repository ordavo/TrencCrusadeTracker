package com.example.trenchcrusadetracker.domain.model.faction

data class FactionRule(
    val id: String,
    val name: String,
    val effectDescription: String
)

/**
 * Variante (subfacción) de una facción base, tal y como aparecen en el libro de
 * "Warbands of Trench Crusade" (p. ej. Papal States Intervention Force).
 * Reutiliza el roster base de su facción, pero define sus propias reglas especiales
 * y recursos iniciales (Ducados y Puntos de Gloria).
 */
data class FactionVariant(
    val id: String,
    val name: String,
    val description: String,
    val specialRules: List<FactionRule> = emptyList(),
    val defaultStartingDucats: Int = 700,
    val defaultStartingGloryPoints: Int = 0
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
