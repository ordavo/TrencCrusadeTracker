package com.example.trenchcrusadetracker.domain

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitType
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentSource
import com.example.trenchcrusadetracker.domain.model.equipment.WeaponRange
import com.example.trenchcrusadetracker.domain.model.warband.Battlekit
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EquipmentAndBattlekitTest {

    @Test
    fun `test official battlekit limits and shield restrictions`() {
        val  rifle2H = Equipment.RangedWeapon(
            id = "w1",
            name = "Trench Rifle",
            cost = EquipmentCost(20),
            source = EquipmentSource.STANDARD,
            description = "2-Handed rifle",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(24),
            keywords = listOf("2-Handed")
        )

        val pistol1H = Equipment.DualPurposeWeapon(
            id = "w2",
            name = "Pistol",
            cost = EquipmentCost(10),
            source = EquipmentSource.STANDARD,
            description = "1-Handed pistol",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.DualPurpose(12),
            keywords = listOf("1-Handed")
        )

        val shield = Equipment.Shield(
            id = "s1",
            name = "Trench Shield",
            cost = EquipmentCost(15),
            source = EquipmentSource.STANDARD,
            description = "Ballistic shield",
            keywords = listOf("Shield", "1-Handed")
        )

        var kit = Battlekit()
        assertTrue(kit.canAddEquipment(shield))
        kit = kit.addEquipment(shield)

        // Shield equipped: cannot add 2-Handed weapon
        assertFalse(kit.canAddEquipment(rifle2H))

        // Shield equipped: can add 1-Handed weapon
        assertTrue(kit.canAddEquipment(pistol1H))
        kit = kit.addEquipment(pistol1H)

        // Shield + 1-Handed weapon = 2 hands occupied. Cannot add another weapon.
        assertFalse(kit.canAddEquipment(rifle2H))
    }

    @Test
    fun `test battlekit categories and costs calculations`() {
        val rifle = Equipment.RangedWeapon(
            id = "w1",
            name = "Trench Rifle",
            cost = EquipmentCost(amount = 20, currency = CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Standard service rifle",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(24)
        )

        val blackMarketGrenade = Equipment.Grenade(
            id = "g1",
            name = "Mustard Gas Shell",
            cost = EquipmentCost(amount = 2, currency = CurrencyType.GLORY_POINTS),
            source = EquipmentSource.BLACK_MARKET,
            description = "Corrosive chemical grenade",
            range = WeaponRange.Ranged(8),
            keywords = listOf("BLAST (3\")")
        )

        var kit = Battlekit()
        kit = kit.addEquipment(rifle)
        kit = kit.addEquipment(blackMarketGrenade)

        assertEquals(1, kit.rangedWeapons.size)
        assertEquals(1, kit.grenades.size)
        assertEquals(20, kit.totalCostInDucats())
        assertEquals(2, kit.totalCostInGloryPoints())
    }

    @Test
    fun `test member total cost includes base cost and battlekit equipment`() {
        val sword = Equipment.MeleeWeapon(
            id = "w2",
            name = "Trench Sword",
            cost = EquipmentCost(amount = 10, currency = CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Razor sharp melee blade",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.Melee,
            keywords = listOf("+1 DICE")
        )

        val relic = Equipment.SpecialItem(
            id = "s1",
            name = "Holy Relic",
            cost = EquipmentCost(amount = 3, currency = CurrencyType.GLORY_POINTS),
            source = EquipmentSource.EXPLORATION,
            description = "Ancient holy artifact",
            keywords = listOf("+1 COURAGE")
        )

        val battlekit = Battlekit()
            .addEquipment(sword)
            .addEquipment(relic)

        val leader = LeaderMember(
            id = "m1",
            customName = "Lieutenant Vane",
            baseStats = ModelStats(),
            baseCostInDucats = 80,
            battlekit = battlekit
        )

        assertEquals(90, leader.calculateTotalCostInDucats())
        assertEquals(3, leader.calculateTotalCostInGloryPoints())
    }
}
