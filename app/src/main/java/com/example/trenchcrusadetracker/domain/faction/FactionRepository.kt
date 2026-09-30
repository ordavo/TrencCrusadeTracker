package com.example.trenchcrusadetracker.domain.faction

import com.example.trenchcrusadetracker.domain.model.faction.Faction

/**
 * Dependency Inversion Principle (DIP):
 * Interfaz de repositorio para consultar facciones sin depender de sus clases concretas.
 */
interface FactionRepository {
    fun getAllFactions(): List<Faction>
    fun getFactionById(id: String): Faction?
    fun getProviderByFactionId(id: String): FactionProvider?
}
