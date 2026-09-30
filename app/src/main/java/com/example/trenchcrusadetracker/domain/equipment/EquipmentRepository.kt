package com.example.trenchcrusadetracker.domain.equipment

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitCategory
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentSource

/**
 * Dependency Inversion Principle (DIP):
 * Repositorio maestro de todo el arsenal del juego.
 * Sirve como fuente primaria inyectable para los arsenales de cada facción.
 */
interface EquipmentRepository {
    fun getAllEquipment(): List<Equipment>
    fun getEquipmentById(id: String): Equipment?
    fun getEquipmentByCategory(category: BattlekitCategory): List<Equipment>
    fun getEquipmentBySource(source: EquipmentSource): List<Equipment>
}
