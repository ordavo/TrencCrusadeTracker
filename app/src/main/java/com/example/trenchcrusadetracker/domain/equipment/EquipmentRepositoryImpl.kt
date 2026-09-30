package com.example.trenchcrusadetracker.domain.equipment

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitCategory
import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitType
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentSource
import com.example.trenchcrusadetracker.domain.model.equipment.WeaponRange

/**
 * Implementación maestra del catálogo completo de equipamiento de Trench Crusade.
 * Sigue estrictamente la reestructuración del Profile (Type, Range, Keywords y Special Rules).
 */
class EquipmentRepositoryImpl : EquipmentRepository {

    private val masterCatalog: List<Equipment> = listOf(
        // Ranged Weapons
        Equipment.RangedWeapon(
            id = "w_trench_rifle",
            name = "Trench Rifle",
            cost = EquipmentCost(20, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Standard bolt-action infantry rifle.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(24),
            keywords = listOf("2-Handed", "+0 DICE")
        ),
        Equipment.RangedWeapon(
            id = "w_submachine_gun",
            name = "Submachine Gun",
            cost = EquipmentCost(30, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Rapid fire automatic weapon for close trench breaches.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(18),
            keywords = listOf("2-Handed", "+1 DICE", "Rapid Fire")
        ),
        Equipment.RangedWeapon(
            id = "w_sniper_rifle",
            name = "Sniper Rifle with Scope",
            cost = EquipmentCost(35, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "High precision long-range rifle with telescopic scope.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(36),
            keywords = listOf("2-Handed", "+1 DICE", "Scoped")
        ),
        Equipment.RangedWeapon(
            id = "w_shotgun",
            name = "Trench Shotgun",
            cost = EquipmentCost(15, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Devastating close-range scatter gun.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(8),
            keywords = listOf("2-Handed", "+1 DICE", "SCATTER")
        ),

        // Dual Purpose Weapons
        Equipment.DualPurposeWeapon(
            id = "w_pistol",
            name = "Service Pistol",
            cost = EquipmentCost(10, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Standard sidearm usable in ranged and melee combat.",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.DualPurpose(12),
            keywords = listOf("1-Handed", "Dual-Purpose")
        ),

        // Grenades
        Equipment.Grenade(
            id = "g_frag",
            name = "Frag Grenade",
            cost = EquipmentCost(10, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "High explosive shrapnel grenade.",
            range = WeaponRange.Ranged(8),
            keywords = listOf("Grenade", "BLAST (2\")")
        ),
        Equipment.Grenade(
            id = "g_mustard_gas",
            name = "Mustard Gas Canister",
            cost = EquipmentCost(2, CurrencyType.GLORY_POINTS),
            source = EquipmentSource.BLACK_MARKET,
            description = "Corrosive chemical cloud weapon.",
            range = WeaponRange.Ranged(8),
            keywords = listOf("Grenade", "BLAST (3\")", "Gas")
        ),

        // Melee Weapons
        Equipment.MeleeWeapon(
            id = "m_trench_club",
            name = "Trench Club",
            cost = EquipmentCost(5, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Weighted melee club for brutal hand-to-hand combat.",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.Melee,
            keywords = listOf("1-Handed", "+1 INJURY DICE")
        ),
        Equipment.MeleeWeapon(
            id = "m_trench_sword",
            name = "Trench Sword",
            cost = EquipmentCost(10, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Razor sharp melee blade.",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.Melee,
            keywords = listOf("1-Handed", "+1 DICE")
        ),
        Equipment.MeleeWeapon(
            id = "m_bayonet",
            name = "Trench Bayonet",
            cost = EquipmentCost(5, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Rifle mounted spike.",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.Melee,
            keywords = listOf("1-Handed")
        ),

        // Armour
        Equipment.Armor(
            id = "a_trench_armor",
            name = "Trench Armor",
            cost = EquipmentCost(25, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Steel breastplate and helmet (+1 Armor).",
            armorBonus = 1,
            movementPenalty = 0,
            keywords = listOf("Armour", "+1 ARMOUR")
        ),
        Equipment.Armor(
            id = "a_heavy_plate",
            name = "Reinforced Heavy Plate",
            cost = EquipmentCost(50, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Heavy full body plate armor (+2 Armor, -1 Movement).",
            armorBonus = 2,
            movementPenalty = 1,
            keywords = listOf("Armour", "+2 ARMOUR", "-1 MOVEMENT")
        ),

        // Shields
        Equipment.Shield(
            id = "s_trench_shield",
            name = "Trench Shield",
            cost = EquipmentCost(15, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Steel ballistic shield requiring one hand.",
            meleeDefenseBonus = 1,
            rangedDefenseBonus = 1,
            keywords = listOf("Shield", "1-Handed", "Shield Restriction")
        ),

        // Equipment / Gear
        Equipment.Gear(
            id = "eq_gas_mask",
            name = "Gas Mask",
            cost = EquipmentCost(10, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Protects against toxic gas cloud effects.",
            keywords = listOf("Equipment", "Gas Immunity")
        ),
        Equipment.Gear(
            id = "eq_medkit",
            name = "First Aid Field Kit",
            cost = EquipmentCost(15, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Field bandages and stimulants.",
            keywords = listOf("Equipment", "Heal")
        ),

        // Special Items & Relics
        Equipment.SpecialItem(
            id = "sp_holy_relic",
            name = "Saint's Relic",
            cost = EquipmentCost(3, CurrencyType.GLORY_POINTS),
            source = EquipmentSource.EXPLORATION,
            description = "Ancient sacred relic.",
            keywords = listOf("Special", "Relic", "+1 COURAGE")
        )
    )

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
