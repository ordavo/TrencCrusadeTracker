package com.example.trenchcrusadetracker.domain.equipment

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitCategory
import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitType
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentSource
import com.example.trenchcrusadetracker.domain.model.equipment.WeaponRange

/**
 * Catálogo Maestro del Arsenal General de Trench Crusade (Extraído del libro Warbands of Trench Crusade).
 * Permite que cada facción consulte e herede su equipamiento aplicando sus propias restricciones y costos.
 */
class EquipmentRepositoryImpl : EquipmentRepository {

    private val masterCatalog: List<Equipment> = listOf(
        // ==================== RANGED WEAPONS ====================
        Equipment.RangedWeapon(
            id = "w_bolt_action_rifle",
            name = "Bolt-Action Rifle / Trench Rifle",
            cost = EquipmentCost(20, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Standard service rifle for infantry.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(24),
            keywords = listOf("2-Handed", "+0 DICE")
        ),
        Equipment.RangedWeapon(
            id = "w_submachine_gun",
            name = "Submachine Gun",
            cost = EquipmentCost(30, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Automatic close-quarters breach weapon.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(18),
            keywords = listOf("2-Handed", "+1 DICE", "Rapid Fire")
        ),
        Equipment.RangedWeapon(
            id = "w_sniper_rifle",
            name = "Sniper Rifle with Scope",
            cost = EquipmentCost(35, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "High precision long-range rifle with telescopic sights.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(36),
            keywords = listOf("2-Handed", "+1 DICE", "Scoped")
        ),
        Equipment.RangedWeapon(
            id = "w_shotgun",
            name = "Trench Shotgun",
            cost = EquipmentCost(15, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Scatter firearm for close-range trench clears.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(8),
            keywords = listOf("2-Handed", "+1 DICE", "SCATTER")
        ),
        Equipment.RangedWeapon(
            id = "w_heavy_machine_gun",
            name = "Heavy Machine Gun",
            cost = EquipmentCost(60, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Heavy rapid-fire suppression weapon.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(36),
            keywords = listOf("2-Handed", "+2 DICE", "Heavy", "Suppression")
        ),
        Equipment.RangedWeapon(
            id = "w_flamethrower",
            name = "Trench Flamethrower",
            cost = EquipmentCost(45, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Incendiary projector firing torrents of liquid fire.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(12),
            keywords = listOf("2-Handed", "BLAST (3\")", "Incendiary")
        ),
        Equipment.RangedWeapon(
            id = "w_anti_tank_rifle",
            name = "Anti-Tank Rifle",
            cost = EquipmentCost(50, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "High-caliber anti-armor rifle designed to breach plate.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Ranged(30),
            keywords = listOf("2-Handed", "+1 INJURY DICE", "Heavy")
        ),

        // ==================== DUAL PURPOSE WEAPONS ====================
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
        Equipment.DualPurposeWeapon(
            id = "w_heavy_pistol",
            name = "Heavy Service Pistol",
            cost = EquipmentCost(15, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Large-caliber sidearm for close trench fighting.",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.DualPurpose(12),
            keywords = listOf("1-Handed", "+1 INJURY DICE", "Dual-Purpose")
        ),

        // ==================== GRENADES ====================
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
            id = "g_smoke",
            name = "Smoke Grenade",
            cost = EquipmentCost(8, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Canister providing dense tactical smoke cover.",
            range = WeaponRange.Ranged(8),
            keywords = listOf("Grenade", "BLAST (3\")", "Smoke")
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

        // ==================== MELEE WEAPONS ====================
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
            description = "Rifle mounted spike or combat knife.",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.Melee,
            keywords = listOf("1-Handed")
        ),
        Equipment.MeleeWeapon(
            id = "m_greatsword",
            name = "Greatsword / Two-Handed Sword",
            cost = EquipmentCost(25, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Massive blade requiring two hands to cleave armor.",
            type = BattlekitType.TWO_HANDED,
            range = WeaponRange.Melee,
            keywords = listOf("2-Handed", "+1 DICE", "+1 INJURY DICE")
        ),
        Equipment.MeleeWeapon(
            id = "m_trench_mace",
            name = "Trench Mace / War Hammer",
            cost = EquipmentCost(15, CurrencyType.DUCATS),
            source = EquipmentSource.STANDARD,
            description = "Heavy impact weapon designed to crush helmets.",
            type = BattlekitType.ONE_HANDED,
            range = WeaponRange.Melee,
            keywords = listOf("1-Handed", "+1 INJURY DICE")
        ),

        // ==================== ARMOUR ====================
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

        // ==================== SHIELDS ====================
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

        // ==================== EQUIPMENT / GEAR ====================
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

        // ==================== SPECIAL ITEMS & RELICS ====================
        Equipment.SpecialItem(
            id = "sp_holy_relic",
            name = "Saint's Relic",
            cost = EquipmentCost(3, CurrencyType.GLORY_POINTS),
            source = EquipmentSource.EXPLORATION,
            description = "Ancient sacred relic granting divine courage.",
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
