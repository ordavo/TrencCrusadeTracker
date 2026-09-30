package com.example.trenchcrusadetracker.domain

import com.example.trenchcrusadetracker.domain.faction.FactionRepositoryImpl
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class FactionRepositoryTest {

    @Test
    fun `test repository loads all 9 warbands and factions`() {
        val repository = FactionRepositoryImpl()
        val factions = repository.getAllFactions()

        assertEquals(9, factions.size)

        val newAntioch = repository.getFactionById("new_antioch")
        assertNotNull(newAntioch)
        assertEquals("Principality of New Antioch", newAntioch?.name)

        val provider = repository.getProviderByFactionId("new_antioch")
        assertNotNull(provider)
        assertEquals(4, provider?.getAvailableUnits()?.size)
    }

    @Test
    fun `test captains and leaders default to CaptainBase stats`() {
        val repository = FactionRepositoryImpl()
        val factions = repository.getAllFactions()

        for (faction in factions) {
            val provider = repository.getProviderByFactionId(faction.id)
            val leaders = provider?.getAvailableUnits()?.filterIsInstance<LeaderMember>() ?: emptyList()

            for (leader in leaders) {
                assertEquals("Leader ${leader.customName} in ${faction.name} must have movement 6", 6, leader.baseStats.movement)
                assertEquals("Leader ${leader.customName} in ${faction.name} must have melee 0", 0, leader.baseStats.melee)
                assertEquals("Leader ${leader.customName} in ${faction.name} must have ranged 0", 0, leader.baseStats.ranged)
                assertEquals("Leader ${leader.customName} in ${faction.name} must have armor 0", 0, leader.baseStats.armor)
                assertEquals("Leader ${leader.customName} in ${faction.name} must have courage 2", 2, leader.baseStats.courage)
                assertEquals("Leader ${leader.customName} in ${faction.name} must have maxWounds 1", 1, leader.baseStats.maxWounds)
            }
        }
    }

    @Test
    fun `test all characters start with 0 base armor and no equipment`() {
        val repository = FactionRepositoryImpl()
        val factions = repository.getAllFactions()

        for (faction in factions) {
            val provider = repository.getProviderByFactionId(faction.id)
            assertNotNull(provider)
            val units = provider?.getAvailableUnits() ?: emptyList()

            for (unit in units) {
                assertEquals("Unit ${unit.customName} in ${faction.name} must have 0 base armor", 0, unit.baseStats.armor)
                assertTrue("Unit ${unit.customName} in ${faction.name} must start with no equipment", unit.battlekit.allEquipment().isEmpty())
            }
        }
    }

    @Test
    fun `test unit movement speeds vary across models`() {
        val repository = FactionRepositoryImpl()
        val factions = repository.getAllFactions()

        val movementsFound = mutableSetOf<Int>()

        for (faction in factions) {
            val provider = repository.getProviderByFactionId(faction.id)
            val units = provider?.getAvailableUnits() ?: emptyList()
            for (unit in units) {
                movementsFound.add(unit.baseStats.movement)
            }
        }

        assertTrue("Should contain multiple movement speeds", movementsFound.size > 1)
        assertTrue("Should contain 8 inch movement models", movementsFound.contains(8))
    }
}
