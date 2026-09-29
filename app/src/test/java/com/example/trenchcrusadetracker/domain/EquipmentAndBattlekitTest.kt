package com.example.trenchcrusadetracker.domain

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitCategory
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentSource
import com.example.trenchcrusadetracker.domain.model.warband.Battlekit
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import org.junit.Assert.assertEquals
import org.junit.Test

class EquipmentAndBattlekitTest {

    @Test
    fun `test battlekit categories and costs calculations`() {
        val rifle = Equipment.Weapon(
            id = "w1",
            name = "Trench Rifle",
            cost = EquipmentCost(amount = 20, currency = CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            category = BattlekitCategory.RANGED_WEAPONS,
            description = "Standard service rifle",
            rangeInInches = 24
        )

        val blackMarketGrenade = Equipment.Grenade(
            id = "g1",
            name = "Mustard Gas Shell",
            cost = EquipmentCost(amount = 2, currency = CurrencyType.GLORY_POINTS),
            source = EquipmentSource.BLACK_MARKET,
            description = "Corrosive chemical grenade",
            specialEffect = "Inflicts 1 automatic wound on contact"
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
        val sword = Equipment.Weapon(
            id = "w2",
            name = "Trench Sword",
            cost = EquipmentCost(amount = 10, currency = CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            category = BattlekitCategory.MELEE_WEAPONS,
            description = "Razor sharp melee blade"
        )

        val relic = Equipment.SpecialItem(
            id = "s1",
            name = "Holy Relic",
            cost = EquipmentCost(amount = 3, currency = CurrencyType.GLORY_POINTS),
            source = EquipmentSource.EXPLORATION,
            description = "Ancient holy artifact",
            relicRule = "+1 Courage bonus"
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
