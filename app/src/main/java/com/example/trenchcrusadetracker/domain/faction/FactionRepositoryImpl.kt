package com.example.trenchcrusadetracker.domain.faction

import com.example.trenchcrusadetracker.domain.faction.providers.BlackGrailFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.HereticLegionFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.IronSultanateFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.NewAntiochFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.TheCourtFactionProvider
import com.example.trenchcrusadetracker.domain.faction.providers.TrenchPilgrimsFactionProvider
import com.example.trenchcrusadetracker.domain.model.faction.Faction

/**
 * Implementación concreta del repositorio de facciones (Repository Pattern).
 * Sigue el principio SOLID (DIP y OCP): Permite registrar nuevas facciones sin alterar la lógica de negocio.
 *
 * Solo se registran las 6 facciones base. Sus subfacciones (variantes) viajan dentro
 * de cada [Faction.variants], evitando providers sueltos por cada variante.
 */
class FactionRepositoryImpl(
    private val providers: List<FactionProvider> = listOf(
        NewAntiochFactionProvider(),
        TrenchPilgrimsFactionProvider(),
        IronSultanateFactionProvider(),
        HereticLegionFactionProvider(),
        BlackGrailFactionProvider(),
        TheCourtFactionProvider()
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
