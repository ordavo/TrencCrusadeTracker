package com.example.trenchcrusadetracker.domain.faction

import com.example.trenchcrusadetracker.domain.faction.providers.GreatHungerFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.HereticLegionFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.HereticNavalRaidersFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.IronSultanateFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.NewAntiochFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.PrussianStosstruppenFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.RedBrigadeFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.SacredAfflictionFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.TrenchPilgrimsFactionProvider
import com.example.trenchcrusadetracker.domain.model.faction.Faction

/**
 * Implementación concreta del repositorio de facciones (Repository Pattern).
 * Sigue el principio SOLID (DIP y OCP): Permite registrar nuevas facciones sin alterar la lógica de negocio.
 */
class FactionRepositoryImpl(
    private val providers: List<FactionProvider> = listOf(
        NewAntiochFactionProvider(),
        HereticLegionFactionProvider(),
        TrenchPilgrimsFactionProvider(),
        IronSultanateFactionProvider(),
        SacredAfflictionFactionProvider(),
        PrussianStosstruppenFactionProvider(),
        HereticNavalRaidersFactionProvider(),
        RedBrigadeFactionProvider(),
        GreatHungerFactionProvider()
    )
) : FactionRepository {

    override fun getAllFactions(): List<Faction> {
        return providers.map { it.faction }
    }

    override fun getFactionById(id: String): Faction? {
        return providers.find { it.faction.id == id }?.faction
    }

    override fun getProviderByFactionId(id: String): FactionProvider? {
        return providers.find { it.faction.id == id }
    }
}
