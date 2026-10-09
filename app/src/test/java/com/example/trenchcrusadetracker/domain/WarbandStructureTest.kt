package com.example.trenchcrusadetracker.domain

import com.example.trenchcrusadetracker.domain.model.faction.Faction
import com.example.trenchcrusadetracker.domain.model.warband.Fireteam
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.MemberRole
import com.example.trenchcrusadetracker.domain.model.warband.MercenaryMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import com.example.trenchcrusadetracker.domain.model.warband.MovementType
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember
import com.example.trenchcrusadetracker.domain.model.warband.Warband
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Verifica la estructura de una Warband/personaje segÃºn las fichas de Trench Companion:
 * coste dual (Ducados/Gloria), keywords, habilidades (âœ§/â–³/â–½), Fireteams y desglose por rol.
 */
class WarbandStructureTest {

    private val testFaction = Faction(
        id = "new_antioch",
        name = "Principality of New Antioch",
        description = "Test"
    )

    @Test
    fun `test mercenary base cost in glory points works alongside ducats`() {
        val mercenary = MercenaryMember(
            id = "m1",
            customName = "Mamluk Faris",
            baseStats = ModelStats(
                movement = 6,
                melee = 1,
                ranged = 1,
                armor = 0
            ),
            baseCostInDucats = 0,
            baseCostInGloryPoints = 4
        )

        assertEquals(0, mercenary.calculateTotalCostInDucats())
        assertEquals(4, mercenary.calculateTotalCostInGloryPoints())
    }

    @Test
    fun `test member carries keywords movement type and abilities`() {
        val leader = LeaderMember(
            id = "l1",
            customName = "Lieutenant Vladimir",
            baseStats = ModelStats(
                movement = 10,
                movementType = MovementType.FLYING,
                melee = 3,
                ranged = 2,
                armor = 3
            ),
            baseCostInDucats = 90,
            keywords = listOf("NEW ANTIOCH", "LEADER", "TOUGH", "FLYING"),
            specialRules = listOf("Hold Your Fire!"),
            skills = listOf("Melee Proficiency", "Parry"),
            flaws = listOf("Paranoid")
        )

        assertEquals("10\" / Flying", leader.baseStats.movementDisplay())
        assertEquals(MovementType.FLYING, leader.baseStats.movementType)
        assertEquals(listOf("NEW ANTIOCH", "LEADER", "TOUGH", "FLYING"), leader.keywords)
        assertEquals(listOf("Hold Your Fire!"), leader.specialRules)
        assertEquals(listOf("Melee Proficiency", "Parry"), leader.skills)
        assertEquals(listOf("Paranoid"), leader.flaws)
    }

    @Test
    fun `test warband counts members per role`() {
        val members = listOf(
            TrooperMember(id = "t1", customName = "Troop", baseStats = ModelStats(), baseCostInDucats = 35),
            TrooperMember(id = "t2", customName = "Troop 2", baseStats = ModelStats(), baseCostInDucats = 35),
            MercenaryMember(id = "m1", customName = "Merc", baseStats = ModelStats(), baseCostInGloryPoints = 4)
        )

        val warband = Warband(
            id = "w1",
            name = "La guardia del Tzar",
            playerId = "p1",
            faction = testFaction,
            ducats = 200,
            gloryPoints = 0,
            members = members
        )

        assertEquals(2, warband.memberCountFor(MemberRole.TROOPER))
        assertEquals(1, warband.memberCountFor(MemberRole.MERCENARY))
        val counts = warband.roleCounts()
        assertEquals(1, counts[MemberRole.MERCENARY])
        assertEquals(0, counts[MemberRole.LEADER])
        assertEquals(70, warband.totalCostInDucats())
        assertEquals(4, warband.totalCostInGloryPoints())
    }

    @Test
    fun `test new antioch allows up to 2 fireteams`() {
        val warband = Warband(
            id = "w1",
            name = "Test",
            playerId = "p1",
            faction = testFaction,
            ducats = 200,
            gloryPoints = 0,
            fireteams = listOf(
                Fireteam(id = "ft1", memberIds = listOf("a", "b")),
                Fireteam(id = "ft2", memberIds = listOf("c", "d"))
            )
        )

        assertEquals(2, warband.fireteamCount())
        assertFalse("Cannot add a 3rd Fireteam", warband.canAddFireteam())
        assertTrue("Member b belongs to a Fireteam", warband.isInFireteam("b"))
        assertFalse("Member z is not in a Fireteam", warband.isInFireteam("z"))
    }

    @Test
    fun `test promoteToElite preserves keywords and new ability fields`() {
        val trooper = TrooperMember(
            id = "t1",
            customName = "Private Miller",
            baseStats = ModelStats(),
            baseCostInDucats = 30,
            keywords = listOf("NEW ANTIOCH", "TROOPER"),
            specialRules = listOf("Assault Drill")
        )

        val promoted = trooper.promoteToElite("Strenght of Samson")

        assertEquals(MemberRole.ELITE, promoted.role)
        assertEquals(listOf("NEW ANTIOCH", "TROOPER"), promoted.keywords)
        assertEquals(listOf("Assault Drill"), promoted.specialRules)
        assertTrue(promoted.skills.contains("Strenght of Samson"))
        assertTrue(promoted.isPromotedFromTrooper)
    }
}