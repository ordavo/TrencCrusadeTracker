package com.example.trenchcrusadetracker.domain.equipment.providers

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitType
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.WeaponRange

/**
 * Catálogo del Battlekit ESTÁNDAR de Trench Crusade (Trench Crusade Digital Rulebook, pp. 67-85).
 *
 * Contiene únicamente los perfiles oficiales (tipo, alcance, keywords y reglas especiales).
 * El coste aquí es meramente de referencia: el coste real y las estipulaciones de cada
 * pieza los define cada Armería de facción (ver FactionArmories.kt).
 */
object StandardBattlekit {

    val items: List<Equipment> = listOf(

        // ==================== RANGED WEAPONS ====================
        Equipment.DualPurposeWeapon(
            id = "w_automatic_pistol", name = "Automatic Pistol",
            cost = EquipmentCost(20), description = "Pistola automática de prestigio, letal en asaltos y cuerpo a cuerpo.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.DualPurpose(12),
            keywords = listOf("ASSAULT", "AUTOMATIC 2", "PISTOL")
        ),
        Equipment.RangedWeapon(
            id = "w_automatic_rifle", name = "Automatic Rifle",
            cost = EquipmentCost(40), description = "Fusil automático de alta cadencia y recarga rápida.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(24),
            keywords = listOf("ASSAULT", "AUTOMATIC 2"),
            specialRules = listOf("Focused Fire: al disparar 2 veces, ambas deben apuntar al mismo modelo.")
        ),
        Equipment.RangedWeapon(
            id = "w_automatic_shotgun", name = "Automatic Shotgun",
            cost = EquipmentCost(15), description = "Escopeta con auto-cargador ideal para combate en trincheras.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(12),
            keywords = listOf("+1 DICE", "ASSAULT", "SHOTGUN")
        ),
        Equipment.RangedWeapon(
            id = "w_blunderbuss", name = "Blunderbuss",
            cost = EquipmentCost(5), description = "Arma antigua cargada con clavos y metralla.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(10),
            keywords = listOf("SHRAPNEL")
        ),
        Equipment.RangedWeapon(
            id = "w_bolt_action_rifle", name = "Bolt-Action Rifle",
            cost = EquipmentCost(10), description = "El fusil de cerrojo, caballo de batalla de la Gran Guerra.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(24),
            keywords = emptyList()
        ),
        Equipment.RangedWeapon(
            id = "w_flamethrower", name = "Flamethrower",
            cost = EquipmentCost(30), description = "Proyecta chorros de fuego y líquidos inflamables.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(8),
            keywords = listOf("-1 INJURY DICE", "FIRE", "FLAMETHROWER", "IGNORE ARMOUR")
        ),
        Equipment.RangedWeapon(
            id = "w_grenade_launcher", name = "Grenade Launcher",
            cost = EquipmentCost(30), description = "Lanza granadas a larga distancia por encima de coberturas.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(36),
            keywords = listOf("BLAST 3\"", "HEAVY", "IGNORE COVER", "SHRAPNEL")
        ),
        Equipment.RangedWeapon(
            id = "w_heavy_flamethrower", name = "Heavy Flamethrower",
            cost = EquipmentCost(55), description = "Lanzallamas masivo que requiere gran fuerza para portarlo.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(10),
            keywords = listOf("-1 INJURY DICE", "AUTOMATIC 2", "FIRE", "FLAMETHROWER", "HEAVY", "IGNORE ARMOUR")
        ),
        Equipment.RangedWeapon(
            id = "w_heavy_shotgun", name = "Heavy Shotgun",
            cost = EquipmentCost(20), description = "Escopeta de calibre 8 para abatir oponentes poderosos a corta distancia.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(12),
            keywords = listOf("+1 DICE", "+1 INJURY DICE", "HEAVY", "SHOTGUN"),
            specialRules = listOf("Tungsten-orichalcum Alloy Shot: +1 INJURY DICE a corta distancia.")
        ),
        Equipment.RangedWeapon(
            id = "w_machine_gun", name = "Machine Gun",
            cost = EquipmentCost(50), description = "Ametralladora totalmente automática, letal contra formaciones.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(36),
            keywords = listOf("AUTOMATIC 3", "HEAVY", "RELOAD")
        ),
        Equipment.RangedWeapon(
            id = "w_musket", name = "Musket",
            cost = EquipmentCost(5), description = "Mosquete de ánima lisa, barato y de construcción sencilla.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(18),
            keywords = listOf("-1 INJURY DICE")
        ),
        Equipment.DualPurposeWeapon(
            id = "w_pistol", name = "Pistol",
            cost = EquipmentCost(6), description = "Pistola de variada variedad, de uso extensivo a corta distancia.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.DualPurpose(12),
            keywords = listOf("PISTOL")
        ),
        Equipment.RangedWeapon(
            id = "w_semiautomatic_rifle", name = "Semi-Automatic Rifle",
            cost = EquipmentCost(15), description = "Fusil semiautomático preciso en asalto, propenso a encasquillarse.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(24),
            keywords = listOf("ASSAULT")
        ),
        Equipment.RangedWeapon(
            id = "w_shotgun", name = "Shotgun",
            cost = EquipmentCost(10), description = "Escopeta de corredera ideal para despejar trincheras.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(12),
            keywords = listOf("+1 DICE", "SHOTGUN")
        ),
        Equipment.DualPurposeWeapon(
            id = "w_silenced_pistol", name = "Silenced Pistol",
            cost = EquipmentCost(15), description = "Sidearm silenciada excelente para emboscadas.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.DualPurpose(12),
            keywords = listOf("ASSAULT", "PISTOL"),
            specialRules = listOf("Silent: +1 DICE si el atacante está en contacto con una cobertura.")
        ),
        Equipment.RangedWeapon(
            id = "w_sniper_rifle", name = "Sniper Rifle",
            cost = EquipmentCost(35), description = "Fusil de precisión de largo alcance para abatir objetivos de alto valor.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(48),
            keywords = listOf("+1 DICE", "CRITICAL", "RISKY"),
            specialRules = listOf("Bull's Eye: un Crítico otorga IGNORE ARMOUR a la tirada de Injury.")
        ),
        Equipment.RangedWeapon(
            id = "w_submachine_gun", name = "Submachine Gun",
            cost = EquipmentCost(30), description = "Subfusil totalmente automático para combate a corta distancia.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(16),
            keywords = listOf("ASSAULT"),
            specialRules = listOf("Quick Bursts: permite dos acciones Shoot en la misma activación.")
        ),
        Equipment.RangedWeapon(
            id = "w_antimateriel_rifle", name = "Anti-Materiel Rifle",
            cost = EquipmentCost(40), description = "Rifle enorme para abatir objetivos fuertemente blindados.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(36),
            keywords = listOf("+1 INJURY DICE", "CRITICAL", "HEAVY", "IGNORE ARMOUR")
        ),

        // ==================== MELEE WEAPONS ====================
        Equipment.MeleeWeapon(
            id = "m_anti_tank_hammer", name = "Anti-Tank Hammer",
            cost = EquipmentCost(35), description = "Pértiga con carga explosiva direccional; devastadora pero peligrosa.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY DICE", "CRITICAL", "IGNORE ARMOUR", "RISKY"),
            specialRules = listOf("Dangerous: coloca 1 BLOOD MARKER en el portador si acierta.")
        ),
        Equipment.MeleeWeapon(
            id = "m_bayonet", name = "Bayonet",
            cost = EquipmentCost(2), description = "Hoja que se fija a la punta de un arma de fuego.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("CUMBERSOME")
        ),
        Equipment.MeleeWeapon(
            id = "m_great_hammer", name = "Great Hammer/Maul/Club/Mace",
            cost = EquipmentCost(10), description = "Arma pesada de impacto, especialmente buena contra armaduras.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY MODIFIER", "HEAVY")
        ),
        Equipment.MeleeWeapon(
            id = "m_great_sword", name = "Great Sword/Axe",
            cost = EquipmentCost(12), description = "Espadón o hacha de guerra de dos manos para seccionar blindajes.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY DICE", "HEAVY")
        ),
        Equipment.MeleeWeapon(
            id = "m_trench_knife", name = "Trench Knife",
            cost = EquipmentCost(1), description = "Cuchillo de trinchera humilde pero letal.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("-1 DICE")
        ),
        Equipment.MeleeWeapon(
            id = "m_misericordia", name = "Misericordia",
            cost = EquipmentCost(15), description = "Daga diseñada para rematar hallando las juntas de la armadura.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            specialRules = listOf("Despatch: obtiene IGNORE ARMOUR si el objetivo está Down.")
        ),
        Equipment.MeleeWeapon(
            id = "m_flail_scourge", name = "Scourge/Flail",
            cost = EquipmentCost(5), description = "Látigo o mangual que golpea con furia indiferente a la defensa.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 DICE")
        ),
        Equipment.MeleeWeapon(
            id = "m_sword_axe", name = "Sword/Axe",
            cost = EquipmentCost(4), description = "Sable o hacha de mano útil para rematar enemigos derribados.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("CRITICAL")
        ),
        Equipment.MeleeWeapon(
            id = "m_trench_club", name = "Trench Club",
            cost = EquipmentCost(3), description = "Maza de trinchera de madera reforzada con hierro.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = emptyList()
        ),
        Equipment.MeleeWeapon(
            id = "m_polearm", name = "Polearm",
            cost = EquipmentCost(7), description = "Pica, billhook o lanza excelente a la defensiva.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("BLOCK", "CUMBERSOME")
        ),

        // ==================== GRENADES ====================
        Equipment.Grenade(
            id = "g_frag", name = "Frag Grenades",
            cost = EquipmentCost(7), description = "Granada de fragmentación, pilar de la guerra de trincheras.",
            range = WeaponRange.Ranged(8),
            keywords = listOf("ASSAULT", "BLAST 2\"", "IGNORE COVER", "IGNORE LONG RANGE", "SHRAPNEL")
        ),
        Equipment.Grenade(
            id = "g_gas", name = "Gas Grenades",
            cost = EquipmentCost(10), description = "Granada de gas que ataca pulmones y órganos internos.",
            range = WeaponRange.Ranged(8),
            keywords = listOf("-1 INJURY DICE", "ASSAULT", "BLAST 3\"", "GAS", "IGNORE ARMOUR", "IGNORE COVER", "IGNORE LONG RANGE")
        ),
        Equipment.Grenade(
            id = "g_incendiary", name = "Incendiary Grenades",
            cost = EquipmentCost(15), description = "Granada incendiaria de azufre o fósforo.",
            range = WeaponRange.Ranged(8),
            keywords = listOf("ASSAULT", "FIRE", "IGNORE COVER", "IGNORE LONG RANGE"),
            specialRules = listOf("Liquid Fire: un Crítico otorga IGNORE ARMOUR a la tirada de Injury.")
        ),
        Equipment.Grenade(
            id = "g_molotov", name = "Molotov Cocktail",
            cost = EquipmentCost(5), description = "Botella incendiaria improvisada.",
            range = WeaponRange.Ranged(6),
            keywords = listOf("-1 INJURY DICE", "ASSAULT", "FIRE", "IGNORE COVER", "IGNORE LONG RANGE"),
            specialRules = listOf("Liquid Fire: un Crítico otorga IGNORE ARMOUR a la tirada de Injury.")
        ),
        Equipment.Grenade(
            id = "g_satchel_charge", name = "Satchel Charge",
            cost = EquipmentCost(15), description = "Explosivo de asalto para reventar fortificaciones y blindajes.",
            range = WeaponRange.Ranged(6),
            keywords = listOf("+1 INJURY DICE", "BLAST 3\"", "CONSUMABLE", "HEAVY", "IGNORE ARMOUR", "IGNORE COVER", "SCATTER"),
            specialRules = listOf("Heavy Explosive: solo puede usarse una vez por partida.")
        ),

        // ==================== SHIELDS ====================
        Equipment.Shield(
            id = "s_trench_shield", name = "Trench Shield",
            cost = EquipmentCost(10), description = "Escudo de trinchera de acero reforzado con oricalco.",
            keywords = listOf("-1 INJURY MODIFIER")
        ),

        // ==================== ARMOUR ====================
        Equipment.Armor(
            id = "a_standard_armour", name = "Standard Armour",
            cost = EquipmentCost(15), description = "Armadura estándar de acero.",
            armorBonus = 1, keywords = listOf("-1 INJURY MODIFIER")
        ),
        Equipment.Armor(
            id = "a_reinforced_armour", name = "Reinforced Armour",
            cost = EquipmentCost(40), description = "Armadura reforzada de aleación divina.",
            armorBonus = 2, keywords = listOf("-2 INJURY MODIFIER")
        ),

        // ==================== EQUIPMENT ====================
        Equipment.Gear(
            id = "e_armour_piercing_bullets", name = "Armour-Piercing Bullets",
            cost = EquipmentCost(15), description = "Munición de tungsteno endurecida para perforar blindajes.",
            keywords = listOf("CONSUMABLE")
        ),
        Equipment.Gear(
            id = "e_binoculars", name = "Binoculars",
            cost = EquipmentCost(10), description = "Prismáticos de oficial para detectar infiltradores.",
            keywords = listOf("ANTI-INFILTRATOR")
        ),
        Equipment.Gear(
            id = "e_blessed_icon", name = "Blessed Icon",
            cost = EquipmentCost(15), description = "Icono sagrado que protege a quien lo porta.",
            keywords = listOf("CONSUMABLE")
        ),
        Equipment.Gear(
            id = "e_combat_helmet", name = "Combat Helmet",
            cost = EquipmentCost(5), description = "Casco de combate probado por generaciones.",
            keywords = listOf("NEGATE SHRAPNEL")
        ),
        Equipment.Gear(
            id = "e_dumdum_bullets", name = "Dum-Dum Bullets",
            cost = EquipmentCost(15), description = "Balas de punta hueca altamente letales.",
            keywords = listOf("CONSUMABLE")
        ),
        Equipment.Gear(
            id = "e_field_shrine", name = "Field Shrine",
            cost = EquipmentCost(2, CurrencyType.GLORY_POINTS),
            description = "Relicario portátil que guía la moral de la banda.",
            keywords = listOf("SHRINE")
        ),
        Equipment.Gear(
            id = "e_gas_mask", name = "Gas Mask",
            cost = EquipmentCost(5), description = "Máscara antigás que protege de humos y gases.",
            keywords = listOf("NEGATE GAS")
        ),
        Equipment.Gear(
            id = "e_hellbound_soul_contract", name = "Hellbound Soul Contract",
            cost = EquipmentCost(5), description = "Contrato infernal que estalla en fuego al morir.",
            keywords = listOf("NEGATE FIRE")
        ),
        Equipment.Gear(
            id = "e_holy_relic", name = "Holy Relic",
            cost = EquipmentCost(2, CurrencyType.GLORY_POINTS),
            description = "Reliquia sagrada distribuida a las tropas del frente.",
            keywords = listOf("BLESSED 1")
        ),
        Equipment.Gear(
            id = "e_incendiary_ammunition", name = "Incendiary Ammunition",
            cost = EquipmentCost(15), description = "Munición incendiaria que prende fuego a los objetivos.",
            keywords = listOf("CONSUMABLE")
        ),
        Equipment.Gear(
            id = "e_infernal_brand", name = "Infernal Brand",
            cost = EquipmentCost(5), description = "Marca ardiente que inmuniza contra fuego mortal.",
            keywords = listOf("NEGATE FIRE")
        ),
        Equipment.Gear(
            id = "e_martyrdom_pills", name = "Martyrdom Pills",
            cost = EquipmentCost(20), description = "Píldoras de martirio que evitan el dolor de la muerte.",
            keywords = listOf("CONSUMABLE")
        ),
        Equipment.Gear(
            id = "e_medikit", name = "Medi-kit",
            cost = EquipmentCost(5), description = "Botiquín de campaña para tratar heridas.",
            keywords = listOf("TREAT")
        ),
        Equipment.Gear(
            id = "e_mountaineer_kit", name = "Mountaineer Kit",
            cost = EquipmentCost(3), description = "Cuerdas, mosquetones y piolets para escalar verticales.",
            keywords = listOf("CLIMB")
        ),
        Equipment.Gear(
            id = "e_musical_instrument", name = "Musical Instrument",
            cost = EquipmentCost(15), description = "Cuernos, tambores y gaitas que animan a la tropa.",
            keywords = listOf("HELD")
        ),
        Equipment.Gear(
            id = "e_shovel", name = "Shovel",
            cost = EquipmentCost(5), description = "Pala que permite atrincherarse y luchar desde cobertura.",
            keywords = listOf("COVER")
        ),
        Equipment.Gear(
            id = "e_tracer_bullets", name = "Tracer Bullets",
            cost = EquipmentCost(15), description = "Balas trazadoras que permiten ajustar la puntería.",
            keywords = listOf("CONSUMABLE")
        ),
        Equipment.Gear(
            id = "e_troop_flag", name = "Troop Flag",
            cost = EquipmentCost(1, CurrencyType.GLORY_POINTS),
            description = "Estandarte que cohesiona a la banda.",
            keywords = listOf("LEADER", "HELD")
        ),
        Equipment.Gear(
            id = "e_unholy_relic", name = "Unholy Relic",
            cost = EquipmentCost(15), description = "Artefacto impío cargado de poder maligno.",
            keywords = listOf("FEAR")
        ),
        Equipment.Gear(
            id = "e_unholy_trinket", name = "Unholy Trinket",
            cost = EquipmentCost(15), description = "Amuleto cubierto de runas goéticas.",
            keywords = listOf("CONSUMABLE")
        )
    )
}
