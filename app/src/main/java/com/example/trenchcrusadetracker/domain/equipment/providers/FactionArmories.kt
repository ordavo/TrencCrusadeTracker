package com.example.trenchcrusadetracker.domain.equipment.providers

import com.example.trenchcrusadetracker.domain.equipment.ArmoryEntry
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentStipulations

/**
 * Armerías ("Armoury Tables") de las facciones y sus variantes de Trench Crusade.
 *
 * Cada entrada referencia un id del catálogo maestro (Battlekit estándar o único) y
 * define su coste y estipulaciones PARA ESA FACCIÓN/VARIANTE, tal y como aparecen en
 * "Warbands of Trench Crusade" y sus suplementos. Los perfiles no se duplican.
 *
 * Convención: `byFactionId` usa como clave el id de facción base o el `armoryId` de la
 * variante (ver `FactionVariant.armoryId`).
 */
object FactionArmories {

    /** Coste en Ducados. */
    private fun d(id: String, amount: Int, st: EquipmentStipulations = EquipmentStipulations.NONE) =
        ArmoryEntry(id, EquipmentCost(amount, CurrencyType.DUCATS), st)

    /** Coste en Puntos de Gloria. */
    private fun g(id: String, amount: Int, st: EquipmentStipulations = EquipmentStipulations.NONE) =
        ArmoryEntry(id, EquipmentCost(amount, CurrencyType.GLORY_POINTS), st)

    private fun st(
        restrictedTo: List<String> = emptyList(),
        limit: Int? = null,
        limitExcluding: String? = null,
        consumable: Boolean = false,
        headgear: Boolean = false,
        shieldCombo: Boolean = false,
        bayonetLug: Boolean = false
    ) = EquipmentStipulations(
        consumable = consumable,
        headgear = headgear,
        shieldCombo = shieldCombo,
        bayonetLug = bayonetLug,
        limit = limit,
        limitExcluding = limitExcluding,
        restrictedTo = restrictedTo
    )

    /** Devuelve una copia de la lista cambiando coste y/o estipulaciones de una entrada. */
    private fun List<ArmoryEntry>.update(
        id: String,
        amount: Int? = null,
        currency: CurrencyType = CurrencyType.DUCATS,
        stip: EquipmentStipulations? = null
    ): List<ArmoryEntry> = map { entry ->
        if (entry.equipmentId == id) {
            entry.copy(
                cost = amount?.let { EquipmentCost(it, currency) } ?: entry.cost,
                stipulations = stip ?: entry.stipulations
            )
        } else entry
    }

    /** Devuelve una copia de la lista sin las entradas indicadas. */
    private fun List<ArmoryEntry>.removing(vararg ids: String): List<ArmoryEntry> =
        filterNot { it.equipmentId in ids }

    // ============================================================ NEW ANTIOCH
    private val newAntiochBase: List<ArmoryEntry> = listOf(
        d("w_automatic_pistol", 20, st(restrictedTo = listOf("ELITE"), limit = 3)),
        d("w_automatic_rifle", 40, st(bayonetLug = true, limit = 1)),
        d("w_automatic_shotgun", 15, st(bayonetLug = true, shieldCombo = true, limit = 2)),
        d("w_bolt_action_rifle", 10, st(bayonetLug = true)),
        d("w_flamethrower", 30, st(limit = 3)),
        d("w_grenade_launcher", 30, st(limit = 2)),
        d("w_heavy_flamethrower", 55, st(limit = 1)),
        d("w_heavy_shotgun", 20, st(shieldCombo = true, limit = 2)),
        d("w_machine_gun", 50, st(limit = 2)),
        d("w_pistol", 6),
        d("w_semiautomatic_rifle", 15, st(bayonetLug = true)),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        d("w_sniper_rifle", 35, st(limit = 3)),
        d("w_submachine_gun", 30, st(bayonetLug = true, shieldCombo = true, limit = 2)),

        d("m_bayonet", 2, st(shieldCombo = true)),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        d("m_misericordia", 15, st(restrictedTo = listOf("Combat Medic"), limit = 1)),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("m_sword_axe", 4),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_frag", 7),
        d("g_incendiary", 15, st(limit = 2)),
        d("g_satchel_charge", 15, st(consumable = true, limit = 3, limitExcluding = "1 per model")),

        d("na_heavy_ballistic_shield", 15, st(restrictedTo = listOf("Machine Armour"), shieldCombo = true)),
        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("na_engineer_body_armour", 45, st(restrictedTo = listOf("Combat Engineer"))),
        d("na_machine_armour", 50, st(restrictedTo = listOf("ELITE", "Mechanized Heavy Infantry"), limit = 1, limitExcluding = "Mechanized Heavy Infantry")),
        d("a_reinforced_armour", 40, st(restrictedTo = listOf("ELITE", "Mechanized Heavy Infantry"))),
        d("a_standard_armour", 15),

        d("e_binoculars", 10, st(restrictedTo = listOf("ELITE"))),
        d("e_combat_helmet", 5, st(headgear = true)),
        g("e_field_shrine", 2),
        d("e_gas_mask", 5),
        g("e_martyrdom_pills", 1, st(consumable = true, restrictedTo = listOf("ELITE"), limit = 2)),
        d("e_medikit", 5),
        d("e_mountaineer_kit", 3, st(limit = 4)),
        d("e_musical_instrument", 15, st(limit = 1)),
        d("e_shovel", 5),
        g("e_troop_flag", 1)
    )

    // ============================================================ TRENCH PILGRIMS
    private val trenchPilgrimsBase: List<ArmoryEntry> = listOf(
        d("w_automatic_pistol", 20, st(limit = 2)),
        d("w_blunderbuss", 5, st(shieldCombo = true)),
        d("w_bolt_action_rifle", 10, st(bayonetLug = true)),
        d("w_flamethrower", 30, st(limit = 2)),
        g("w_machine_gun", 2),
        d("w_musket", 5, st(bayonetLug = true)),
        d("w_pistol", 6),
        d("tp_punt_gun", 20, st(limit = 2)),
        d("w_semiautomatic_rifle", 15, st(bayonetLug = true)),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        g("w_sniper_rifle", 2),
        g("w_submachine_gun", 2, st(bayonetLug = true, shieldCombo = true)),

        d("m_anti_tank_hammer", 35, st(restrictedTo = listOf("ELITE"), limit = 3)),
        d("m_bayonet", 2, st(shieldCombo = true)),
        d("m_flail_scourge", 5),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        d("m_misericordia", 15, st(limit = 1)),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("m_sword_axe", 4),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_incendiary", 15),
        d("g_molotov", 5),
        d("tp_warcross", 5),

        g("tp_holy_icon_shield", 2, st(restrictedTo = listOf("ELITE"), shieldCombo = true)),
        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("a_reinforced_armour", 40, st(restrictedTo = listOf("ELITE"))),
        d("a_standard_armour", 15),

        d("e_blessed_icon", 15, st(consumable = true)),
        g("e_field_shrine", 2),
        d("e_gas_mask", 5),
        g("e_holy_relic", 2, st(restrictedTo = listOf("ELITE"))),
        d("e_incendiary_ammunition", 15, st(consumable = true, limit = 1)),
        d("tp_iron_capirote", 7, st(headgear = true)),
        d("e_martyrdom_pills", 20, st(consumable = true, limit = 3)),
        d("e_medikit", 5),
        d("e_mountaineer_kit", 3, st(limit = 1)),
        d("e_musical_instrument", 15, st(limit = 1)),
        g("e_troop_flag", 1, st(limit = 1))
    )

    // ============================================================ IRON SULTANATE
    private val ironSultanateBase: List<ArmoryEntry> = listOf(
        d("is_alaybozan", 9, st(restrictedTo = listOf("Sultanate Sappers"), shieldCombo = true)),
        d("is_flame_cannon", 60, st(restrictedTo = listOf("Brazen Bull"), limit = 1)),
        d("w_flamethrower", 30, st(limit = 3)),
        d("is_jezzail", 7),
        d("w_machine_gun", 50, st(limit = 1)),
        d("is_murad_bombard", 50, st(restrictedTo = listOf("Brazen Bull"), limit = 1)),
        d("w_musket", 5, st(bayonetLug = true)),
        d("w_pistol", 6),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        d("is_siege_jezzail", 30),
        d("w_sniper_rifle", 35, st(restrictedTo = listOf("ELITE", "Janissaries"), limit = 2)),

        d("is_assassins_dagger", 15, st(restrictedTo = listOf("Assassin"))),
        d("m_bayonet", 2, st(shieldCombo = true)),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        d("is_halberd_gun", 20, st(restrictedTo = listOf("ELITE"))),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("m_sword_axe", 4),
        d("is_titan_zulfiqar", 30, st(restrictedTo = listOf("Brazen Bull"))),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_frag", 7),
        d("g_incendiary", 15, st(restrictedTo = listOf("Alchemist"))),

        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("is_alchemist_armour", 50, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("a_reinforced_armour", 40, st(restrictedTo = listOf("ELITE", "Janissaries"))),
        d("a_standard_armour", 15),

        d("is_alchemical_ammunition", 3, st(restrictedTo = listOf("Jezzail", "Siege Jezzail", "Alaybozan", "Halberd-Gun"))),
        d("e_binoculars", 10, st(restrictedTo = listOf("ELITE"))),
        d("is_cloak_of_alamut", 25, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("e_combat_helmet", 5),
        d("e_gas_mask", 5),
        g("e_holy_relic", 2, st(restrictedTo = listOf("ELITE"))),
        d("is_marid_shovel", 15, st(restrictedTo = listOf("Brazen Bull"))),
        d("e_medikit", 5),
        d("e_mountaineer_kit", 3, st(limit = 2)),
        d("e_musical_instrument", 15, st(limit = 1)),
        d("e_shovel", 5),
        g("is_takwin_anqa_bird", 2, st(restrictedTo = listOf("ELITE"), limit = 1)),
        g("e_troop_flag", 1, st(limit = 1)),
        d("is_wind_amulet", 10, st(limit = 2))
    )

    // ============================================================ HERETIC LEGIONS
    // Clave = id de la facción base ("heretic_legion"), no el nombre de la facción.
    private val hereticLegionBase: List<ArmoryEntry> = listOf(
        g("w_antimateriel_rifle", 3, st(limit = 1)),
        g("w_automatic_rifle", 2, st(bayonetLug = true, limit = 2)),
        d("w_automatic_shotgun", 15, st(bayonetLug = true, shieldCombo = true)),
        d("w_bolt_action_rifle", 10, st(bayonetLug = true)),
        d("w_flamethrower", 30, st(limit = 3)),
        d("w_grenade_launcher", 30, st(limit = 2)),
        d("w_heavy_flamethrower", 55, st(limit = 2)),
        d("w_machine_gun", 50, st(limit = 1)),
        d("w_pistol", 6),
        d("w_semiautomatic_rifle", 15, st(bayonetLug = true)),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        d("w_silenced_pistol", 15, st(restrictedTo = listOf("ELITE"))),
        g("w_submachine_gun", 2, st(bayonetLug = true, shieldCombo = true)),

        d("m_bayonet", 2, st(shieldCombo = true)),
        g("hl_blasphemous_staff", 2, st(restrictedTo = listOf("ELITE"))),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        g("hl_hellblade", 1, st(limit = 2)),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("hl_sacrificial_blade", 23, st(restrictedTo = listOf("ELITE"), limit = 2)),
        d("m_sword_axe", 4),
        d("hl_tartarus_claws", 15, st(restrictedTo = listOf("Death Commando"))),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_frag", 7),
        d("g_gas", 10),
        d("g_incendiary", 15),

        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("a_reinforced_armour", 40, st(restrictedTo = listOf("Anointed", "ELITE"))),
        d("a_standard_armour", 15),

        d("e_binoculars", 10, st(restrictedTo = listOf("ELITE"))),
        d("e_combat_helmet", 5, st(headgear = true)),
        d("e_gas_mask", 5),
        d("e_hellbound_soul_contract", 5, st(restrictedTo = listOf("Heretic Troopers", "Legionnaires"), limit = 3)),
        d("e_incendiary_ammunition", 15, st(consumable = true, limit = 1)),
        d("e_infernal_brand", 5),
        d("e_mountaineer_kit", 3, st(limit = 2)),
        d("e_musical_instrument", 15, st(limit = 1)),
        d("e_shovel", 5),
        g("e_troop_flag", 1, st(limit = 1)),
        d("e_unholy_relic", 15),
        d("e_unholy_trinket", 15, st(consumable = true))
    )

    // ============================================================ BLACK GRAIL
    private val blackGrailBase: List<ArmoryEntry> = listOf(
        d("w_blunderbuss", 5, st(shieldCombo = true)),
        d("w_bolt_action_rifle", 10, st(bayonetLug = true)),
        d("bg_corruption_belcher", 30, st(limit = 2)),
        d("bg_infested_rifle", 15, st(bayonetLug = true)),
        d("w_machine_gun", 50, st(restrictedTo = listOf("Amalgam"), limit = 1)),
        d("w_musket", 5, st(bayonetLug = true)),
        d("w_pistol", 6),
        d("bg_putrid_shotgun", 20, st(bayonetLug = true, shieldCombo = true, limit = 2)),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        d("bg_viscera_cannon", 50, st(restrictedTo = listOf("ELITE"), limit = 2)),

        d("m_bayonet", 2, st(shieldCombo = true)),
        d("bg_beelzebubs_axe", 30, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        d("bg_plague_blade", 7, st(restrictedTo = listOf("ELITE"), limit = 3)),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("m_sword_axe", 4),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_gas", 10),
        d("bg_parasite_grenades", 15),

        d("bg_black_grail_shield", 20, st(restrictedTo = listOf("ELITE"), shieldCombo = true)),
        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("a_reinforced_armour", 40, st(restrictedTo = listOf("ELITE"))),
        d("a_standard_armour", 15),

        d("e_combat_helmet", 5, st(headgear = true)),
        d("bg_compound_eyes_helmet", 10, st(restrictedTo = listOf("ELITE", "Heralds"), headgear = true, limit = 3)),
        d("bg_grail_devotee", 15, st(restrictedTo = listOf("ELITE"), limit = 2)),
        g("e_field_shrine", 2),
        d("e_musical_instrument", 15, st(limit = 1)),
        g("e_troop_flag", 1, st(limit = 1)),
        d("e_unholy_trinket", 15, st(consumable = true))
    )

    // ============================================================ THE COURT
    private val courtBase: List<ArmoryEntry> = listOf(
        d("court_arquebus", 8),
        d("w_blunderbuss", 5, st(shieldCombo = true)),
        d("court_fiendbound_gatling_gun", 20, st(limit = 3)),
        d("w_flamethrower", 30, st(limit = 2)),
        d("w_heavy_flamethrower", 55, st(limit = 1)),
        d("court_ophidian_rifle", 25, st(restrictedTo = listOf("ELITE"), limit = 3)),
        d("w_pistol", 6),
        d("court_serpent_assault_gun", 45, st(limit = 2)),
        d("w_shotgun", 10, st(shieldCombo = true)),

        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        d("court_headtaker", 15, st(limit = 2)),
        d("hl_hellblade", 15, st(limit = 3)),
        g("court_iron_maiden_flail", 4, st(restrictedTo = listOf("Hell Knights"), limit = 2)),
        d("court_malebranche_sword", 35, st(limit = 1)),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("m_sword_axe", 4),
        d("court_torture_instrument", 10, st(restrictedTo = listOf("DEMONIC"))),
        g("court_tormentor_chain", 3, st(restrictedTo = listOf("Hell Knights", "Desecrated Saint"), limit = 2)),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_gas", 10),
        d("g_incendiary", 15, st(limit = 2)),
        d("court_jaws_of_iron", 5, st(restrictedTo = listOf("Yoke Fiends"))),

        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("court_infernal_iron_armour", 50, st(restrictedTo = listOf("Hell Knights"))),
        d("a_reinforced_armour", 40, st(restrictedTo = listOf("ELITE"))),
        d("a_standard_armour", 15),

        g("court_chthonic_vessel", 1, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("e_combat_helmet", 5, st(headgear = true)),
        d("court_crown_of_hellfire", 15, st(restrictedTo = listOf("ELITE", "Pit Locusts"))),
        d("e_gas_mask", 5),
        d("e_incendiary_ammunition", 15, st(consumable = true, limit = 1)),
        d("e_musical_instrument", 15, st(limit = 1)),
        d("court_restraining_muzzle_yoke", 10, st(restrictedTo = listOf("Fiend"), limit = 3)),
        d("e_shovel", 5, st(restrictedTo = listOf("Wretched", "Yoke Fiends"))),
        g("e_troop_flag", 1, st(limit = 1)),
        d("e_unholy_relic", 15),
        d("e_unholy_trinket", 15, st(consumable = true)),

        g("court_goetic_black_heart", 5, st(restrictedTo = listOf("ELITE"), limit = 2)),
        g("court_goetic_blessing_serpent_moon", 5, st(restrictedTo = listOf("Sorcerer"))),
        g("court_goetic_infernal_challenge", 10, st(restrictedTo = listOf("Hell Knights"))),
        g("court_goetic_raining_blood", 10, st(restrictedTo = listOf("ELITE"), limit = 3)),
        g("court_goetic_slavemaster", 10, st(restrictedTo = listOf("ELITE")))
    )

    // ============================================================ GREAT HUNGER (Black Grail variant)
    private val greatHungerArmory: List<ArmoryEntry> = listOf(
        d("w_shotgun", 10, st(shieldCombo = true)),
        d("gh_black_spot_rifle", 25, st(bayonetLug = true, shieldCombo = true, limit = 2)),
        d("bg_putrid_shotgun", 20, st(bayonetLug = true, shieldCombo = true, limit = 2)),

        d("m_bayonet", 2, st(shieldCombo = true)),
        d("gh_butcher_bayonet", 15, st(shieldCombo = true, limit = 2)),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        d("bg_plague_blade", 7, st(restrictedTo = listOf("ELITE"), limit = 3)),
        d("gh_pestilent_fang", 15, st(limit = 2)),
        d("gh_vomitus", 40, st(restrictedTo = listOf("Gregori Gula"))),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("m_sword_axe", 4),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("bg_parasite_grenades", 15),

        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("a_reinforced_armour", 40, st(restrictedTo = listOf("ELITE"))),
        d("a_standard_armour", 15),

        d("e_combat_helmet", 5, st(headgear = true)),
        d("bg_grail_devotee", 15, st(restrictedTo = listOf("ELITE"), limit = 2)),
        g("e_field_shrine", 2),
        d("e_unholy_trinket", 15, st(consumable = true)),

        g("gh_cup_of_filth", 4, st(restrictedTo = listOf("Matagot Hag"), limit = 1)),
        g("gh_foetid_palanquin", 4, st(restrictedTo = listOf("Matagot Hag"), limit = 1)),

        g("gh_strain_devouring_jaws", 2, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("gh_strain_grasping_maw", 15),
        d("gh_strain_hellfly_host", 0, st(restrictedTo = listOf("Ravenous"))),
        d("gh_strain_lockjaw_bite", 15),
        d("gh_strain_papillal_hide", 5, st(restrictedTo = listOf("Ravenous", "Hounds"))),
        d("gh_strain_rotten_cutters", 10, st(restrictedTo = listOf("Matagot Hag", "Ravenous"))),
        d("gh_strain_unending_starvation", 10, st(restrictedTo = listOf("Matagot Hag", "Great Maw", "Desiccated Husks", "Ravenous")))
    )

    // ============================================================ DIRGE OF THE GREAT HEGEMON (Black Grail variant)
    private val dirgeArmory: List<ArmoryEntry> = listOf(
        d("w_blunderbuss", 5, st(shieldCombo = true)),
        d("w_bolt_action_rifle", 10, st(bayonetLug = true)),
        d("bg_corruption_belcher", 30, st(limit = 2)),
        d("bg_infested_rifle", 15, st(bayonetLug = true)),
        d("w_machine_gun", 50, st(restrictedTo = listOf("Amalgam"), limit = 1)),
        d("w_musket", 5, st(bayonetLug = true)),
        d("w_pistol", 6),
        d("bg_putrid_shotgun", 20, st(bayonetLug = true, shieldCombo = true, limit = 3)),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        d("bg_viscera_cannon", 50, st(limit = 3)),

        d("m_bayonet", 2, st(shieldCombo = true)),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        d("bg_plague_blade", 7, st(restrictedTo = listOf("ELITE"), limit = 3)),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("m_sword_axe", 4),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_gas", 10),
        d("bg_parasite_grenades", 15),

        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("a_reinforced_armour", 40, st(restrictedTo = listOf("ELITE"))),
        d("a_standard_armour", 15),

        d("e_combat_helmet", 5, st(headgear = true)),
        d("bg_compound_eyes_helmet", 10, st(restrictedTo = listOf("ELITE", "Heralds"), headgear = true, limit = 3)),
        d("bg_grail_devotee", 15, st(restrictedTo = listOf("ELITE"), limit = 2)),
        g("e_field_shrine", 2),
        d("e_musical_instrument", 15, st(limit = 1)),
        g("e_troop_flag", 1, st(limit = 1)),
        d("e_unholy_trinket", 15, st(consumable = true)),

        d("bg_broken_crown", 25, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("bg_urn_of_the_bitter_ashes", 40, st(restrictedTo = listOf("ELITE"), limit = 1))
    )

    // ============================================================ TRENCH GHOSTS (Heretic variant)
    private val trenchGhostsArmory: List<ArmoryEntry> = listOf(
        g("w_antimateriel_rifle", 3, st(limit = 1)),
        g("w_automatic_rifle", 2, st(bayonetLug = true, limit = 2)),
        d("w_automatic_shotgun", 15, st(bayonetLug = true, shieldCombo = true)),
        d("w_bolt_action_rifle", 10, st(bayonetLug = true)),
        d("w_flamethrower", 30, st(limit = 3)),
        d("w_grenade_launcher", 30, st(limit = 2)),
        d("w_heavy_flamethrower", 55, st(limit = 2)),
        d("w_machine_gun", 50, st(limit = 1)),
        d("w_pistol", 6),
        d("w_semiautomatic_rifle", 15, st(bayonetLug = true)),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        d("w_silenced_pistol", 15, st(restrictedTo = listOf("ELITE"))),
        g("w_submachine_gun", 2, st(bayonetLug = true, shieldCombo = true)),

        d("m_bayonet", 2, st(shieldCombo = true)),
        g("hl_blasphemous_staff", 2, st(restrictedTo = listOf("ELITE"))),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        g("hl_hellblade", 1, st(limit = 2)),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("hl_sacrificial_blade", 23, st(restrictedTo = listOf("ELITE"), limit = 2)),
        d("m_sword_axe", 4),
        d("hl_tartarus_claws", 15, st(restrictedTo = listOf("Death Commando"))),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_frag", 7),
        d("g_gas", 10),
        d("g_incendiary", 15),

        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("a_reinforced_armour", 40, st(restrictedTo = listOf("Anointed", "ELITE"))),
        d("a_standard_armour", 15),
        d("hl_sarcophagus_mine", 30, st(restrictedTo = listOf("Heretic Troopers"), limit = 2)),
        d("hl_tank_palanquin", 60, st(restrictedTo = listOf("Heretic Priest"))),

        d("e_binoculars", 10, st(restrictedTo = listOf("ELITE"))),
        d("e_combat_helmet", 5, st(headgear = true)),
        d("e_gas_mask", 5),
        d("e_incendiary_ammunition", 15, st(consumable = true, limit = 1)),
        d("e_mountaineer_kit", 3, st(limit = 2)),
        d("e_musical_instrument", 15, st(limit = 1)),
        d("e_shovel", 5),
        g("e_troop_flag", 1, st(limit = 1)),
        d("e_unholy_relic", 15),
        d("e_unholy_trinket", 15, st(consumable = true))
    )

    // ============================================================ KNIGHTS OF AVARICE (Heretic variant)
    private val knightsOfAvariceArmory: List<ArmoryEntry> = listOf(
        g("w_antimateriel_rifle", 3, st(limit = 1)),
        g("w_automatic_rifle", 2, st(bayonetLug = true, limit = 2)),
        d("w_automatic_shotgun", 15, st(bayonetLug = true, shieldCombo = true)),
        d("w_bolt_action_rifle", 10, st(bayonetLug = true)),
        d("w_flamethrower", 30, st(limit = 3)),
        d("w_grenade_launcher", 30, st(limit = 2)),
        d("w_heavy_flamethrower", 55, st(limit = 2)),
        d("w_machine_gun", 50, st(limit = 1)),
        d("w_pistol", 6),
        d("w_semiautomatic_rifle", 15, st(bayonetLug = true)),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        d("w_silenced_pistol", 15, st(restrictedTo = listOf("ELITE"))),
        g("w_submachine_gun", 2, st(bayonetLug = true, shieldCombo = true)),

        d("m_bayonet", 2, st(shieldCombo = true)),
        g("hl_blasphemous_staff", 2, st(restrictedTo = listOf("ELITE"))),
        d("hl_coin_hammer", 20, st(limit = 2)),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        g("hl_hellblade", 1, st(limit = 2)),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("hl_sacrificial_blade", 23, st(restrictedTo = listOf("ELITE"), limit = 2)),
        d("m_sword_axe", 4),
        d("hl_tartarus_claws", 15, st(restrictedTo = listOf("Death Commando"))),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_gas", 10),
        d("g_incendiary", 15),

        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("a_reinforced_armour", 40, st(restrictedTo = listOf("Anointed", "ELITE"))),
        d("a_standard_armour", 15),
        d("hl_tarnished_armour", 25, st(limit = 1)),

        d("e_binoculars", 10, st(restrictedTo = listOf("ELITE"))),
        d("e_combat_helmet", 5, st(headgear = true)),
        d("e_gas_mask", 5),
        d("hl_golden_calf_altar", 20, st(consumable = true, limit = 3)),
        d("e_hellbound_soul_contract", 5, st(restrictedTo = listOf("Heretic Troopers", "Legionnaires"), limit = 3)),
        d("e_incendiary_ammunition", 15, st(consumable = true, limit = 1)),
        d("e_infernal_brand", 5),
        d("e_mountaineer_kit", 3, st(limit = 2)),
        d("e_musical_instrument", 15, st(limit = 1)),
        d("e_shovel", 5),
        d("hl_standard_of_mammon", 25, st(limit = 1)),
        d("hl_gas_bombs", 0, st(restrictedTo = listOf("Artillery Witch"))),
        g("e_troop_flag", 1, st(limit = 1)),
        d("e_unholy_relic", 15),
        d("e_unholy_trinket", 15, st(consumable = true))
    )

    // ============================================================ NAVAL RAIDERS (Heretic variant)
    private val navalRaidersArmory: List<ArmoryEntry> = listOf(
        g("w_antimateriel_rifle", 3, st(limit = 1)),
        g("w_automatic_rifle", 2, st(bayonetLug = true, limit = 2)),
        d("w_automatic_shotgun", 15, st(bayonetLug = true, shieldCombo = true)),
        d("w_bolt_action_rifle", 10, st(bayonetLug = true)),
        d("w_flamethrower", 30, st(limit = 3)),
        d("w_grenade_launcher", 30, st(limit = 2)),
        d("w_heavy_flamethrower", 55, st(limit = 2)),
        d("w_machine_gun", 50, st(limit = 1)),
        d("w_pistol", 6),
        d("w_semiautomatic_rifle", 15, st(bayonetLug = true)),
        d("w_shotgun", 10, st(bayonetLug = true, shieldCombo = true)),
        d("w_silenced_pistol", 15, st(restrictedTo = listOf("ELITE"))),
        d("w_submachine_gun", 25, st(bayonetLug = true, shieldCombo = true)),

        d("m_bayonet", 2, st(shieldCombo = true)),
        g("hl_blasphemous_staff", 2, st(restrictedTo = listOf("ELITE"))),
        d("hl_boarding_axe", 10),
        d("m_great_hammer", 10),
        d("m_great_sword", 12),
        g("hl_hellblade", 1, st(limit = 2)),
        d("hl_hull_drill", 20, st(restrictedTo = listOf("Abyssal Commando"))),
        d("m_polearm", 7, st(shieldCombo = true)),
        d("hl_sacrificial_blade", 23, st(restrictedTo = listOf("ELITE"), limit = 2)),
        d("m_sword_axe", 4),
        d("hl_tartarus_claws", 15, st(restrictedTo = listOf("Death Commando"))),
        d("hl_wreckers_torch", 15, st(restrictedTo = listOf("ELITE"), shieldCombo = true)),
        d("m_trench_club", 3),
        d("m_trench_knife", 1),

        d("g_frag", 7),
        d("g_gas", 10),
        d("g_incendiary", 10),

        d("s_trench_shield", 10, st(shieldCombo = true)),

        d("a_reinforced_armour", 40, st(restrictedTo = listOf("ELITE", "Anointed Heretic Raiders"))),
        d("a_standard_armour", 15),

        d("e_combat_helmet", 5, st(headgear = true)),
        d("e_gas_mask", 5),
        d("e_hellbound_soul_contract", 5, st(restrictedTo = listOf("Heretic Raiders", "Heretic Raider Legionnaires"), limit = 3)),
        d("e_incendiary_ammunition", 15, st(consumable = true, limit = 1)),
        d("e_infernal_brand", 5),
        d("hl_infernal_bomb", 0, st(restrictedTo = listOf("Sea Hag"))),
        d("hl_leviathan_bell", 10, st(restrictedTo = listOf("Drowned Chorister"))),
        d("e_mountaineer_kit", 3, st(limit = 2)),
        d("e_musical_instrument", 15, st(limit = 1)),
        d("e_shovel", 5),
        d("hl_shark_tooth_talisman", 10, st(consumable = true)),
        g("e_troop_flag", 1, st(limit = 1)),
        d("e_unholy_relic", 15),
        d("e_unholy_trinket", 15, st(consumable = true))
    )

    // ============================================================ NEW ANTIOCH VARIANTS
    // Papal States: arsenal base + Crucifix; el resto sin cambios.
    private val papalStatesArmory: List<ArmoryEntry> = newAntiochBase + listOf(
        g("na_papal_crucifix", 3, st(limit = 1))
    )

    // Éire Rangers: base + Anointed Ammunition; Satchel Charge sube a Limit 4.
    private val eireRangersArmory: List<ArmoryEntry> = newAntiochBase
        .update(
            "g_satchel_charge",
            stip = st(consumable = true, limit = 4, limitExcluding = "1 per model")
        ) + listOf(
        d("e_armour_piercing_bullets", 5, st(consumable = true, limit = 2))
    )

    // Kingdom of Alba: Cold Steel/Claymore Smiths/Dum-Dum y límites reducidos.
    private val albaArmory: List<ArmoryEntry> = newAntiochBase
        .update("m_great_sword", amount = 7)
        .update("w_automatic_shotgun", stip = st(bayonetLug = true, shieldCombo = true, limit = 1))
        .update("w_grenade_launcher", stip = st(limit = 1))
        .update("w_machine_gun", stip = st(limit = 1))
        .update("w_sniper_rifle", stip = st(limit = 1))
        .update("w_submachine_gun", stip = st(bayonetLug = true, shieldCombo = true, limit = 1))
        .update("na_machine_armour", stip = st(restrictedTo = listOf("Lieutenant", "Mechanized Heavy Infantry"), limit = 1, limitExcluding = "Mechanized Heavy Infantry"))
        .update("a_reinforced_armour", stip = st(restrictedTo = listOf("Lieutenant", "Mechanized Heavy Infantry"))) + listOf(
        d("e_dumdum_bullets", 5, st(consumable = true, limit = 3)),
        d("na_alba_lochaber_axe", 20)
    )

    // Stosstruppen: sin Grenade Launcher ni Martyrdom Pills; SMG Limit 4; armaduras restringidas.
    private val prussiaArmory: List<ArmoryEntry> = newAntiochBase
        .removing("w_grenade_launcher", "e_martyrdom_pills")
        .update("w_machine_gun", stip = st(limit = 1))
        .update("w_sniper_rifle", stip = st(limit = 2))
        .update("w_submachine_gun", stip = st(bayonetLug = true, shieldCombo = true, limit = 4))
        .update("a_reinforced_armour", stip = st(restrictedTo = listOf("Hauptmann", "Mechanized Heavy Infantry")))
        .update("na_machine_armour", stip = st(restrictedTo = listOf("Hauptmann", "Mechanized Heavy Infantry"), limit = 1, limitExcluding = "Mechanized Heavy Infantry")) + listOf(
        d("na_prussia_tank_splitter_sword", 15, st(limit = 2)),
        d("na_prussia_holy_smoke", 15, st(consumable = true, restrictedTo = listOf("Feldkaplan")))
    )

    // Abyssinia: Misericordia Limit 2 + Battlekit único.
    private val abyssiniaArmory: List<ArmoryEntry> = newAntiochBase
        .update("m_misericordia", stip = st(restrictedTo = listOf("Combat Medic"), limit = 2)) + listOf(
        d("na_abyssinia_shotel", 5, st(restrictedTo = listOf("Chewa", "ELITE"))),
        d("na_abyssinia_holy_water", 3, st(consumable = true, limit = 5)),
        d("na_abyssinia_anfarro", 10, st(restrictedTo = listOf("Chewa", "ELITE"), headgear = true, limit = 6)),
        g("na_abyssinia_tabot", 4, st(restrictedTo = listOf("Holy warrior"), limit = 1))
    )

    // Red Brigade: sin Troop Flag; Molotovs y Battlekit propio.
    private val redBrigadeArmory: List<ArmoryEntry> = newAntiochBase
        .removing("e_troop_flag") + listOf(
        d("g_molotov", 5),
        d("na_red_blood_cloak", 5, st(limit = 4)),
        g("na_red_banner", 2, st(limit = 1)),
        d("na_red_atonement_bell", 0)
    )

    // ============================================================ TRENCH PILGRIMS VARIANTS
    // Sacred Affliction: sin Machine Gun ni Iron Capirote; Punt Gun Limit 1 y kit único.
    private val sacredAfflictionArmory: List<ArmoryEntry> = trenchPilgrimsBase
        .removing("w_machine_gun", "tp_iron_capirote")
        .update("tp_punt_gun", stip = st(limit = 1))
        .update("g_incendiary", amount = 10)
        .update("w_flamethrower", stip = st(limit = 2, bayonetLug = true))
        .update("m_anti_tank_hammer", stip = st(limit = 3))
        .update("tp_holy_icon_shield", amount = 20, currency = CurrencyType.DUCATS, stip = st(shieldCombo = true)) + listOf(
        g("tp_affliction_bells_of_warding", 2, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("tp_affliction_blessed_millstone", 5, st(restrictedTo = listOf("Leper-Pilgrims"), limit = 2)),
        d("tp_affliction_great_flail", 15, st(limit = 3)),
        d("tp_affliction_holy_icon_armour", 30),
        d("tp_affliction_penitents_phylactery", 12, st(limit = 2)),
        d("tp_affliction_ragged_vestments", 10)
    )

    // War Pilgrimage of Saint Methodius: re-costes base + armamento y equipo de Anchorite Shrine.
    private val methodiusArmory: List<ArmoryEntry> = trenchPilgrimsBase
        .update("w_machine_gun", amount = 50, currency = CurrencyType.DUCATS, stip = st(limit = 2))
        .update("w_submachine_gun", amount = 30, currency = CurrencyType.DUCATS, stip = st(bayonetLug = true, shieldCombo = true, limit = 1)) + listOf(
        d("w_automatic_rifle", 40, st(bayonetLug = true, limit = 1)),
        d("w_antimateriel_rifle", 40, st(restrictedTo = listOf("Anchorite Shrine"))),
        d("w_heavy_flamethrower", 45, st(restrictedTo = listOf("Anchorite Shrine"))),
        d("tp_methodius_autocannon", 55, st(restrictedTo = listOf("Anchorite Shrine"))),
        d("tp_methodius_gas_censer", 50, st(restrictedTo = listOf("Anchorite Shrine"))),
        d("tp_methodius_trench_mortar", 40, st(restrictedTo = listOf("Anchorite Shrine"))),
        d("tp_methodius_gas_filters", 5, st(restrictedTo = listOf("Anchorite Shrine"))),
        d("tp_methodius_grand_anchorite", 15, st(restrictedTo = listOf("Anchorite Shrine"), limit = 1)),
        d("tp_methodius_hallowed_anchorite", 10, st(restrictedTo = listOf("Anchorite Shrine"), limit = 1)),
        d("tp_methodius_holy_diesel_engine", 10, st(restrictedTo = listOf("Anchorite Shrine"))),
        d("tp_methodius_piston_legs", 10, st(restrictedTo = listOf("Anchorite Shrine"))),
        d("tp_methodius_sacred_geometry", 10, st(restrictedTo = listOf("Anchorite Shrine"), limit = 1)),
        d("tp_methodius_wrathful_cherub_face", 20, st(restrictedTo = listOf("Anchorite Shrine")))
    )

    // Cavalcade of the Tenth Plague: base + Sacrificial Lamb.
    private val tenthPlagueArmory: List<ArmoryEntry> = trenchPilgrimsBase + listOf(
        d("tp_plague_sacrificial_lamb", 5, st(consumable = true))
    )

    // ============================================================ IRON SULTANATE VARIANTS
    // Fida'i of Alamut: base + Battlekit propio del Cabal de Asesinos.
    private val fidaiArmory: List<ArmoryEntry> = ironSultanateBase + listOf(
        d("is_fidai_bow_of_alamut", 50, st(restrictedTo = listOf("Master Assassins", "Sultanate Assassins"), limit = 1)),
        d("is_fidai_golden_khanjar", 20, st(restrictedTo = listOf("Master Assassins", "Sultanate Assassins"), limit = 2)),
        d("is_fidai_hashashin_leaf", 5, st(consumable = true, limit = 4))
    )

    // House of Wisdom: Alchemist Armour Limit 2 + Elixir y Fire Shield.
    private val houseOfWisdomArmory: List<ArmoryEntry> = ironSultanateBase
        .update("is_alchemist_armour", stip = st(restrictedTo = listOf("ELITE"), limit = 2)) + listOf(
        d("is_wisdom_elixir_of_al_khidr", 10, st(consumable = true, limit = 1)),
        d("is_wisdom_fire_shield", 20, st(shieldCombo = true, limit = 2))
    )

    // ============================================================ THE COURT VARIANTS
    // High Cathedra of Pride.
    private val courtPrideArmory: List<ArmoryEntry> = courtBase
        .update("e_troop_flag", amount = 10, currency = CurrencyType.DUCATS, stip = st(restrictedTo = listOf("ELITE", "Desecrated Saint"), limit = 1)) + listOf(
        d("court_pride_lightbringer_rifle", 40, st(restrictedTo = listOf("ELITE"), limit = 1)),
        g("court_pride_cloak_of_the_morningstar", 3, st(restrictedTo = listOf("ELITE", "Desecrated Saint"), limit = 1)),
        d("court_pride_glorious_light", 20, st(restrictedTo = listOf("Hell Knight", "Desecrated Saint"), limit = 2)),
        d("court_pride_petals_of_blood_roses", 5, st(restrictedTo = listOf("Wretched"), limit = 2)),
        g("court_pride_laurels_of_the_supreme", 3, st(restrictedTo = listOf("Praetor", "Sorcerer"), headgear = true, limit = 1))
    )

    // Golden Seat of Greed: Reinforced Armour sin ELITE only + Battlekit propio.
    private val courtGreedArmory: List<ArmoryEntry> = courtBase
        .update("a_reinforced_armour", stip = st()) + listOf(
        d("court_greed_ring_gun", 25, st(limit = 2)),
        d("court_greed_herodic_hammer", 15, st(restrictedTo = listOf("ELITE"), limit = 2)),
        d("court_greed_queens_bones", 5, st(restrictedTo = listOf("Wretched"))),
        d("court_greed_stolen_sentimentality", 12, st(consumable = true, limit = 4)),
        g("court_greed_esaus_bargain", 5, st(restrictedTo = listOf("ELITE"), limit = 1))
    )

    // Flesh Divan of Lust: sin lanzallamas ni Malebranche; Iron Maiden/Torture Instrument liberados.
    private val courtLustArmory: List<ArmoryEntry> = courtBase
        .removing("w_flamethrower", "w_heavy_flamethrower", "court_malebranche_sword")
        .update("court_iron_maiden_flail", stip = st(limit = 2))
        .update("court_torture_instrument", stip = st())
        .update("a_reinforced_armour", stip = st(restrictedTo = listOf("ELITE"), limit = 1)) + listOf(
        d("court_lust_altar_of_torment", 60, st(limit = 3)),
        g("court_lust_astragaloi", 2, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("court_lust_horn_of_luxuria", 30, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("court_lust_lance_of_asmoday", 30, st(restrictedTo = listOf("Hell Knights", "Desecrated Saints"))),
        d("court_lust_naamahrite_scourge", 20, st(restrictedTo = listOf("ELITE"), limit = 3)),
        d("court_lust_spoils_of_lust", 5, st(restrictedTo = listOf("Wretched")))
    )

    // Black Klismos of Envy: Iron Maiden Flail abierta; Battlekit propio.
    private val courtEnvyArmory: List<ArmoryEntry> = courtBase
        .update("court_iron_maiden_flail", stip = st(restrictedTo = listOf("Hell Knights", "Desecrated Saint"), limit = 2)) + listOf(
        d("court_envy_brand_of_moloch", 10, st(restrictedTo = listOf("ELITE"))),
        g("court_envy_craven_vassalage", 5, st(restrictedTo = listOf("Hell Knight"), limit = 1)),
        d("court_envy_lying_knife", 12, st(restrictedTo = listOf("ELITE"))),
        d("court_envy_pyrite_mirror_pavise", 30, st(restrictedTo = listOf("Hell Knight"), shieldCombo = true, limit = 1)),
        d("court_envy_usurpers_saw_wire", 10, st(restrictedTo = listOf("Hell Knights", "Desecrated Saints")))
    )

    // Hungering Throne of Gluttony: sin Battlekit CONSUMABLE; Battlekit propio.
    private val courtGluttonyArmory: List<ArmoryEntry> = courtBase
        .removing("court_chthonic_vessel", "e_incendiary_ammunition", "e_unholy_trinket") + listOf(
        d("court_gluttony_jawed_bascinet", 20, st(restrictedTo = listOf("ELITE"), headgear = true, limit = 2)),
        g("court_gluttony_lanx_ciborum", 8, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("court_gluttony_meaty_cleaver", 25, st(limit = 3)),
        d("court_gluttony_ostentatious_ornamentation", 15, st(restrictedTo = listOf("LEADER"), headgear = true, limit = 1)),
        d("court_gluttony_tawdry_finery", 45, st(restrictedTo = listOf("ELITE"), limit = 1))
    )

    // Iron Curule of Wrath: sin gatling ni Serpent Assault; Heavy Flamethrower Limit 3 y Hellblade sin límite.
    private val courtWrathArmory: List<ArmoryEntry> = courtBase
        .removing("court_fiendbound_gatling_gun", "court_serpent_assault_gun")
        .update("w_heavy_flamethrower", stip = st(limit = 3))
        .update("hl_hellblade", stip = st()) + listOf(
        d("court_wrath_apollyons_ember", 10, st(restrictedTo = listOf("ELITE"), limit = 2)),
        d("court_wrath_lesser_mark_of_cain", 25, st(restrictedTo = listOf("ELITE"), limit = 1)),
        d("court_wrath_locust_gait", 10, st(restrictedTo = listOf("Hell Knights"))),
        d("court_wrath_ruinous_pernach", 15, st(limit = 3)),
        g("court_wrath_spear_of_apollyon", 6, st(restrictedTo = listOf("ELITE"), shieldCombo = true, limit = 1))
    )

    // Silk Bed of Sloth: Ophidian Rifle sin ELITE ni límite; Flail/Scourge + Battlekit propio.
    private val courtSlothArmory: List<ArmoryEntry> = courtBase
        .update("court_ophidian_rifle", stip = st()) + listOf(
        d("m_flail_scourge", 5),
        d("court_sloth_desidian_matchlock", 10),
        d("court_sloth_flower_of_lytos", 15, st(consumable = true, restrictedTo = listOf("ELITE"), limit = 1)),
        g("court_sloth_iscariots_rope", 4, st(consumable = true, restrictedTo = listOf("ELITE"), limit = 1)),
        d("court_sloth_shroud_of_balak", 50, st(restrictedTo = listOf("Sorcerer")))
    )

    val byFactionId: Map<String, List<ArmoryEntry>> = mapOf(

        // ----- Facciones base -----
        "new_antioch" to newAntiochBase,
        "trench_pilgrims" to trenchPilgrimsBase,
        "iron_sultanate" to ironSultanateBase,
        "heretic_legion" to hereticLegionBase,
        "black_grail" to blackGrailBase,
        "the_court" to courtBase,

        // ----- Variantes de Black Grail -----
        "black_grail_great_hunger" to greatHungerArmory,
        "black_grail_dirge" to dirgeArmory,

        // ----- Variantes de Heretic Legions -----
        "heretic_trench_ghosts" to trenchGhostsArmory,
        "heretic_knights_of_avarice" to knightsOfAvariceArmory,
        "heretic_naval_raiders" to navalRaidersArmory,

        // ----- Variantes de New Antioch -----
        "antioch_papal_states" to papalStatesArmory,
        "antioch_eire_rangers" to eireRangersArmory,
        "antioch_alba" to albaArmory,
        "antioch_prussia" to prussiaArmory,
        "antioch_abyssinia" to abyssiniaArmory,
        "antioch_red_brigade" to redBrigadeArmory,

        // ----- Variantes de Trench Pilgrims -----
        "pilgrim_sacred_affliction" to sacredAfflictionArmory,
        "pilgrim_methodius" to methodiusArmory,
        "pilgrim_tenth_plague" to tenthPlagueArmory,

        // ----- Variantes de Iron Sultanate -----
        "sultanate_fidai" to fidaiArmory,
        "sultanate_house_of_wisdom" to houseOfWisdomArmory,

        // ----- Variantes de The Court (7 pecados) -----
        "court_pride" to courtPrideArmory,
        "court_greed" to courtGreedArmory,
        "court_lust" to courtLustArmory,
        "court_envy" to courtEnvyArmory,
        "court_gluttony" to courtGluttonyArmory,
        "court_wrath" to courtWrathArmory,
        "court_sloth" to courtSlothArmory
    )
}
