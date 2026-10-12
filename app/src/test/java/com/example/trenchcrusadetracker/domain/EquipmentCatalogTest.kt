package com.example.trenchcrusadetracker.domain

import com.example.trenchcrusadetracker.domain.equipment.EquipmentRepositoryImpl
import com.example.trenchcrusadetracker.domain.equipment.providers.FactionArmories
import com.example.trenchcrusadetracker.domain.equipment.providers.FactionArmoryRegistry
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.warband.MemberRole
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class EquipmentCatalogTest {

    private val repository = EquipmentRepositoryImpl()

    private val baseFactions = listOf(
        "new_antioch", "trench_pilgrims", "iron_sultanate",
        "heretic_legions", "black_grail", "the_court"
    )

    @Test
    fun `master catalog has no duplicate ids and resolves standard plus unique items`() {
        val all = repository.getAllEquipment()
        val ids = all.map { it.id }
        assertEquals(ids.size, ids.toSet().size)

        // Perfiles estándar
        assertNotNull(repository.getEquipmentById("w_bolt_action_rifle"))
        assertNotNull(repository.getEquipmentById("m_trench_club"))
        assertNotNull(repository.getEquipmentById("a_reinforced_armour"))
        assertNotNull(repository.getEquipmentById("s_trench_shield"))

        // Perfiles únicos
        assertNotNull(repository.getEquipmentById("na_machine_armour"))
        assertNotNull(repository.getEquipmentById("tp_punt_gun"))
        assertNotNull(repository.getEquipmentById("is_jezzail"))
        assertNotNull(repository.getEquipmentById("hl_blasphemous_staff"))
        assertNotNull(repository.getEquipmentById("bg_corruption_belcher"))
        assertNotNull(repository.getEquipmentById("court_serpent_assault_gun"))
    }

    @Test
    fun `every faction armory resolves to valid master profiles`() {
        baseFactions.forEach { factionId ->
            val provider = FactionArmoryRegistry.forFaction(factionId, repository)
            val items = provider.getAvailableEquipment()
            assertTrue("$factionId should have equipment", items.isNotEmpty())
            items.forEach { item ->
                assertNotNull("Missing master profile ${item.id}", repository.getEquipmentById(item.id))
            }
        }
    }

    @Test
    fun `new antioch applies its specific costs and stipulations`() {
        val provider = FactionArmoryRegistry.forFaction("new_antioch", repository)

        val pistol = provider.getAvailableEquipment().first { it.id == "w_automatic_pistol" }
        assertEquals(20, pistol.cost.amount)
        assertTrue(pistol.stipulations.eliteOnly)
        assertEquals(3, pistol.stipulations.limit)

        val submachine = provider.getAvailableEquipment().first { it.id == "w_submachine_gun" }
        assertTrue(submachine.stipulations.bayonetLug)
        assertTrue(submachine.stipulations.shieldCombo)
        assertEquals(2, submachine.stipulations.limit)
    }

    @Test
    fun `troopers cannot take glory or elite-only equipment but elites can`() {
        val provider = FactionArmoryRegistry.forFaction("new_antioch", repository)

        val leaderItems = provider.getAvailableEquipmentForRole(MemberRole.ELITE)
        val trooperItems = provider.getAvailableEquipmentForRole(MemberRole.TROOPER)

        assertTrue(leaderItems.any { it.id == "w_automatic_pistol" })
        assertTrue(trooperItems.none { it.id == "w_automatic_pistol" })
        assertTrue(trooperItems.all { it.cost.gloryAmount() == 0 })
    }

    @Test
    fun `faction armories exist for the six base factions plus variant armories`() {
        assertTrue(FactionArmories.byFactionId.keys.containsAll(baseFactions))
        assertTrue(FactionArmories.byFactionId.containsKey("black_grail_great_hunger"))
        assertTrue(FactionArmories.byFactionId.containsKey("black_grail_dirge"))
        assertTrue(FactionArmories.byFactionId.containsKey("heretic_trench_ghosts"))
        assertTrue(FactionArmories.byFactionId.containsKey("heretic_knights_of_avarice"))
        assertTrue(FactionArmories.byFactionId.containsKey("heretic_naval_raiders"))
    }

    @Test
    fun `variant armories resolve to valid master profiles`() {
        val variantArmories = listOf(
            "black_grail_great_hunger", "black_grail_dirge",
            "heretic_trench_ghosts", "heretic_knights_of_avarice", "heretic_naval_raiders"
        )
        variantArmories.forEach { armoryId ->
            val provider = FactionArmoryRegistry.forFaction(armoryId, repository)
            val items = provider.getAvailableEquipment()
            assertTrue("$armoryId should have equipment", items.isNotEmpty())
            items.forEach { item ->
                assertNotNull("Missing master profile ${item.id}", repository.getEquipmentById(item.id))
            }
        }
    }

    @Test
    fun `the court applies october review corrections`() {
        val provider = FactionArmoryRegistry.forFaction("the_court", repository)
        val items = provider.getAvailableEquipment()

        assertEquals(45, items.first { it.id == "court_serpent_assault_gun" }.cost.amount)
        assertEquals(35, items.first { it.id == "court_malebranche_sword" }.cost.amount)
        assertEquals(10, items.first { it.id == "court_torture_instrument" }.cost.amount)
        assertTrue(items.first { it.id == "court_torture_instrument" }.stipulations.restrictedTo.contains("DEMONIC"))

        // Goetic Powers listadas como especiales
        assertTrue(items.any { it.id == "court_goetic_black_heart" })
        assertTrue(items.any { it.id == "court_goetic_slavemaster" })
    }

    @Test
    fun `unknown faction falls back to the default armory`() {
        val provider = FactionArmoryRegistry.forFaction("unknown_faction", repository)
        assertEquals(repository.getAllEquipment().size, provider.getAvailableEquipment().size)
    }

    @Test
    fun `profiles keep their standard characteristics`() {
        val sniper = repository.getEquipmentById("w_sniper_rifle")
        assertNotNull(sniper)
        assertEquals("48\"", sniper!!.range.toString())
        assertTrue(sniper.keywords.contains("CRITICAL"))

        assertNull(repository.getEquipmentById("does_not_exist"))
    }
}
