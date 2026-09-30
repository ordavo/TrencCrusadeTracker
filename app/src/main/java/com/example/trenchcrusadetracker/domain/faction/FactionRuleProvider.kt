package com.example.trenchcrusadetracker.domain.faction

import com.example.trenchcrusadetracker.domain.model.faction.Faction
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember

/**
 * Interface Segregation Principle (ISP) & Single Responsibility Principle (SRP):
 * Define la responsabilidad de proveer información de las reglas y recursos iniciales de una facción.
 */
interface FactionRuleProvider {
    val faction: Faction
    val defaultStartingDucats: Int
    val defaultStartingGloryPoints: Int
}

/**
 * Interface Segregation Principle (ISP):
 * Define la lista de tropas y miniaturas disponibles para reclutar en una facción.
 */
interface UnitRosterProvider {
    fun getAvailableUnits(): List<WarbandMember>
}

/**
 * Interface completa que combina las reglas y el catálogo de unidades de una facción.
 */
interface FactionProvider : FactionRuleProvider, UnitRosterProvider
