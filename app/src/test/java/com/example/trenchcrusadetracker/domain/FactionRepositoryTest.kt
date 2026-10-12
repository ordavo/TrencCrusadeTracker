package com.example.trenchcrusadetracker.domain

import com.example.trenchcrusadetracker.domain.faction.FactionRepositoryImpl
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class FactionRepositoryTest {

    @Test
    fun `test repository loads the 6 base factions`() {
        val repository = FactionRepositoryImpl()
        val factions = repository.getAllFactions()

        assertEquals(6, factions.size)

        val newAntioch = repository.getFactionById("new_antioch")
        assertNotNull(newAntioch)
        assertEquals("Principality of New Antioch", newAntioch?.name)

        val provider = repository.getProviderByFactionId("new_antioch")
        assertNotNull(provider)
        assertEquals(8, provider?.getAvailableUnits()?.size)
    }

    @Test
    fun `test base factions start with 700 ducats except variants`() {
        val repository = FactionRepositoryImpl()
        val factions = repository.getAllFactions()

        for (faction in factions) {
            assertEquals("${faction.name} should start with 700 ducats", 700, faction.defaultStartingDucats)
        }
    }

    @Test
    fun `test new antioch exposes its warband variants`() {
        val repository = FactionRepositoryImpl()
        val newAntioch = repository.getFactionById("new_antioch")
        assertNotNull(newAntioch)

        val variants = newAntioch?.variants ?: emptyList()
        assertEquals(6, variants.size)

        val papalStates = variants.find { it.id == "antioch_papal_states" }
        assertNotNull(papalStates)
        assertEquals(500, papalStates?.defaultStartingDucats)
        assertEquals(11, papalStates?.defaultStartingGloryPoints)
    }

    @Test
    fun `test black grail base faction is registered with variants`() {
        val repository = FactionRepositoryImpl()
        val blackGrail = repository.getFactionById("black_grail")
        assertNotNull(blackGrail)
        assertEquals("Cult of the Black Grail", blackGrail?.name)
        assertEquals(2, blackGrail?.variants?.size ?: 0)

        val provider = repository.getProviderByFactionId("black_grail")
        assertNotNull(provider)
        assertTrue((provider?.getAvailableUnits()?.size ?: 0) >= 6)
    }

    @Test
    fun `test captains and leaders have leader role and keyword`() {
        val repository = FactionRepositoryImpl()
        val factions = repository.getAllFactions()

        for (faction in factions) {
            val provider = repository.getProviderByFactionId(faction.id)
            val leaders = provider?.getAvailableUnits()?.filterIsInstance<LeaderMember>() ?: emptyList()

            for (leader in leaders) {
                assertTrue("Leader ${leader.customName} in ${faction.name} must have LEADER keyword", leader.keywords.contains("LEADER"))
            }
        }
    }

    @Test
    fun `test all characters start with no equipment`() {
        val repository = FactionRepositoryImpl()
        val factions = repository.getAllFactions()

        for (faction in factions) {
            val provider = repository.getProviderByFactionId(faction.id)
            assertNotNull(provider)
            val units = provider?.getAvailableUnits() ?: emptyList()

            for (unit in units) {
                assertTrue("Unit ${unit.customName} in ${faction.name} must start with no equipment", unit.battlekit.allEquipment().isEmpty())
            }
        }
    }

    @Test
    fun `test black grail exposes plague knight ranks`() {
        val repository = FactionRepositoryImpl()
        val provider = repository.getProviderByFactionId("black_grail")
        assertNotNull(provider)

        val units = provider?.getAvailableUnits() ?: emptyList()
        val ids = units.map { it.id }
        assertEquals(ids.size, ids.toSet().size)

        assertEquals(70, units.firstOrNull { it.id == "grail_plague_almoner" }?.baseCostInDucats)
        assertEquals(65, units.firstOrNull { it.id == "grail_knight_companion_of_the_fly_ranged" }?.baseCostInDucats)
        assertEquals(65, units.firstOrNull { it.id == "grail_knight_companion_of_the_fly_melee" }?.baseCostInDucats)
        assertEquals(65, units.firstOrNull { it.id == "grail_knight_of_the_rotten_cross" }?.baseCostInDucats)
        assertEquals(1, units.firstOrNull { it.id == "grail_knight_companion_of_the_fly_ranged" }?.baseStats?.ranged)
        assertEquals(3, units.firstOrNull { it.id == "grail_knight_companion_of_the_fly_melee" }?.baseStats?.melee)
    }

    @Test
    fun `test great hunger variant exposes its roster and butcher ranks`() {
        val repository = FactionRepositoryImpl()
        val blackGrail = repository.getFactionById("black_grail")
        assertNotNull(blackGrail)

        val greatHunger = blackGrail?.variants?.find { it.id == "black_grail_great_hunger" }
        assertNotNull(greatHunger)
        val units = greatHunger?.units ?: emptyList()

        assertTrue(units.any { it.id == "gh_matagot_hag" })
        assertTrue(units.any { it.id == "gh_ravenous" })
        assertEquals(70, units.firstOrNull { it.id == "gh_butcher_king" }?.baseCostInDucats)
        assertEquals(65, units.firstOrNull { it.id == "gh_knight_companion_of_the_feast" }?.baseCostInDucats)
        assertEquals(65, units.firstOrNull { it.id == "gh_knight_of_twin_cleavers" }?.baseCostInDucats)
        assertEquals(2, units.firstOrNull { it.id == "gh_matagot_hag" }?.baseStats?.melee)

        val ids = units.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun `test heretic legionnaires upgrade the trooper profile`() {
        val repository = FactionRepositoryImpl()
        val provider = repository.getProviderByFactionId("heretic_legion")
        assertNotNull(provider)

        val units = provider?.getAvailableUnits() ?: emptyList()
        val trooper = units.firstOrNull { it.id == "heretic_trooper" }
        val ranged = units.firstOrNull { it.id == "heretic_legionnaire_ranged" }
        val melee = units.firstOrNull { it.id == "heretic_legionnaire_melee" }

        assertNotNull(trooper)
        assertNotNull(ranged)
        assertNotNull(melee)
        assertEquals(40, ranged?.baseCostInDucats)
        assertEquals(40, melee?.baseCostInDucats)
        assertEquals(1, ranged?.baseStats?.ranged)
        assertEquals(0, ranged?.baseStats?.melee)
        assertEquals(1, melee?.baseStats?.melee)
        assertEquals(0, melee?.baseStats?.ranged)
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
