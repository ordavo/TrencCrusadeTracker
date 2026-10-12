package com.example.trenchcrusadetracker.domain.equipment.providers

import com.example.trenchcrusadetracker.domain.equipment.ArmoryEntry
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentStipulations

/**
 * Armerías ("Armoury Tables") de las 6 facciones base de Trench Crusade.
 *
 * Cada entrada referencia un id del catálogo maestro (Battlekit estándar o único) y
 * define su coste y estipulaciones PARA ESA FACCIÓN, tal y como aparecen en
 * "Warbands of Trench Crusade". Los perfiles no se duplican.
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

    val byFactionId: Map<String, List<ArmoryEntry>> = mapOf(

        // ============================================================ NEW ANTIOCH
        "new_antioch" to listOf(
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
        ),

        // ============================================================ TRENCH PILGRIMS
        "trench_pilgrims" to listOf(
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
        ),

        // ============================================================ IRON SULTANATE
        "iron_sultanate" to listOf(
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
        ),

        // ============================================================ HERETIC LEGIONS
        "heretic_legions" to listOf(
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
        ),

        // ============================================================ BLACK GRAIL
        "black_grail" to listOf(
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
        ),

        // ============================================================ THE COURT
        "the_court" to listOf(
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
        ),

        // ============================================================ GREAT HUNGER (Black Grail variant)
        "black_grail_great_hunger" to listOf(
            d("w_shotgun", 10, st(shieldCombo = true)),
            d("gh_black_spot_rifle", 25, st(bayonetLug = true, shieldCombo = true, limit = 2)),
            d("bg_putrid_shotgun", 20, st(bayonetLug = true, shieldCombo = true, limit = 2)),

            d("m_bayonet", 2, st(shieldCombo = true)),
            d("gh_butcher_bayonet", 15, st(shieldCombo = true, limit = 2)),
            d("m_great_hammer", 10),
            d("m_great_sword", 12),
            d("bg_plague_blade", 7, st(restrictedTo = listOf("ELITE"), limit = 3)),
            d("gh_pestilent_fang", 15, st(limit = 2)),
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
        ),

        // ============================================================ DIRGE OF THE GREAT HEGEMON (Black Grail variant)
        "black_grail_dirge" to listOf(
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
        ),

        // ============================================================ TRENCH GHOSTS (Heretic variant)
        "heretic_trench_ghosts" to listOf(
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
        ),

        // ============================================================ KNIGHTS OF AVARICE (Heretic variant)
        "heretic_knights_of_avarice" to listOf(
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
        ),

        // ============================================================ NAVAL RAIDERS (Heretic variant)
        "heretic_naval_raiders" to listOf(
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
    )
}
