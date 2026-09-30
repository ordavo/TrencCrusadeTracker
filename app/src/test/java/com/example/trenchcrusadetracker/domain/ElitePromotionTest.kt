package com.example.trenchcrusadetracker.domain

import com.example.trenchcrusadetracker.domain.model.faction.Faction
import com.example.trenchcrusadetracker.domain.model.warband.EliteMember
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.MemberRole
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember
import com.example.trenchcrusadetracker.domain.model.warband.Warband
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ElitePromotionTest {

    private val testFaction = Faction(
        id = "f1",
        name = "Test Faction",
        description = "Test description"
    )

    @Test
    fun `test promotion of trooper to elite member`() {
        val trooper = TrooperMember(
            id = "t1",
            customName = "Private Miller",
            baseStats = ModelStats(movement = 6, courage = 0),
            baseCostInDucats = 30
        )

        val promotedElite = trooper.promoteToElite("Trench Veteran")

        assertEquals(MemberRole.ELITE, promotedElite.role)
        assertEquals("Private Miller", promotedElite.customName)
        assertEquals(1, promotedElite.baseStats.courage)
        assertTrue(promotedElite.isPromotedFromTrooper)
        assertEquals(listOf("Trench Veteran"), promotedElite.specialSkills)
    }

    @Test
    fun `test max 6 elites per warband restriction`() {
        val leader = LeaderMember(
            id = "l1",
            customName = "Captain Vane",
            baseCostInDucats = 90
        )

        val elitesList = (1..6).map { index ->
            EliteMember(
                id = "e$index",
                customName = "Elite $index",
                baseStats = ModelStats(movement = 6),
                baseCostInDucats = 50
            )
        }

        val warbandWith6Elites = Warband(
            id = "w1",
            name = "Iron Guard",
            playerId = "p1",
            faction = testFaction,
            ducats = 500,
            gloryPoints = 0,
            members = listOf(leader) + elitesList
        )

        assertEquals(6, warbandWith6Elites.currentEliteCount())
        assertFalse("Warband should not be able to add a 7th elite", warbandWith6Elites.canAddElite())
    }
}
