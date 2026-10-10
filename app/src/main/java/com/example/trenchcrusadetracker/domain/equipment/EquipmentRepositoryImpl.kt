package com.example.trenchcrusadetracker.domain.equipment

import com.example.trenchcrusadetracker.domain.equipment.providers.FactionBattlekit
import com.example.trenchcrusadetracker.domain.equipment.providers.StandardBattlekit
import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitCategory
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentSource

/**
 * Catálogo Maestro del Arsenal de Trench Crusade.
 *
 * Combina el Battlekit ESTÁNDAR (Trench Crusade Digital Rulebook) con el Battlekit
 * ÚNICO de cada facción (Warbands of Trench Crusade). Los costes y estipulaciones
 * definitivos los aplica cada Armería de facción (ver FactionArmories).
 */
class EquipmentRepositoryImpl : EquipmentRepository {

    private val masterCatalog: List<Equipment> =
        StandardBattlekit.items + FactionBattlekit.items

    override fun getAllEquipment(): List<Equipment> = masterCatalog

    override fun getEquipmentById(id: String): Equipment? {
        return masterCatalog.find { it.id == id }
    }

    override fun getEquipmentByCategory(category: BattlekitCategory): List<Equipment> {
        return masterCatalog.filter { it.category == category }
    }

    override fun getEquipmentBySource(source: EquipmentSource): List<Equipment> {
        return masterCatalog.filter { it.source == source }
    }
}
