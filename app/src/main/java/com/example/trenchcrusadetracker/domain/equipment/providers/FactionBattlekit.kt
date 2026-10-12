package com.example.trenchcrusadetracker.domain.equipment.providers

import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitType
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.equipment.EquipmentCost
import com.example.trenchcrusadetracker.domain.model.equipment.WeaponRange

/**
 * Battlekit ÚNICO de cada facción (Warbands of Trench Crusade, sección "Battlekit" de cada banda).
 * Los perfiles son oficiales; el coste aquí es de referencia y la armería de la facción lo reafirma.
 */
object FactionBattlekit {

    private fun ducats(amount: Int) = EquipmentCost(amount, CurrencyType.DUCATS)
    private fun glory(amount: Int) = EquipmentCost(amount, CurrencyType.GLORY_POINTS)

    val items: List<Equipment> = listOf(

        // ==================== NEW ANTIOCH ====================
        Equipment.Armor(
            id = "na_engineer_body_armour", name = "Engineer Body Armour",
            cost = ducats(45), description = "Armadura reforzada de diseño especial para Zapadores de Combate.",
            armorBonus = 2, keywords = listOf("-2 INJURY MODIFIER", "NEGATE SHRAPNEL"),
            specialRules = listOf("Ballistic Box Armour: -1 INJURY DICE extra al recibir ataques con SHRAPNEL.")
        ),
        Equipment.Shield(
            id = "na_heavy_ballistic_shield", name = "Heavy Ballistic Shield",
            cost = ducats(15), description = "Escudo masivo de fibra de aramida y aleación de oricalco.",
            meleeDefenseBonus = 0, rangedDefenseBonus = 2, keywords = listOf("COVER")
        ),
        Equipment.Armor(
            id = "na_machine_armour", name = "Machine Armour",
            cost = ducats(50), description = "Aleación divina y tecnología de New Antioch; convierte a su portador en un tanque.",
            armorBonus = 3, keywords = listOf("-3 INJURY MODIFIER"),
            specialRules = listOf(
                "Bulky: base 40mm y Charge Bonus D3\".",
                "Standfast: un Down se trata como Minor Wound."
            )
        ),

        // ==================== TRENCH PILGRIMS ====================
        Equipment.RangedWeapon(
            id = "tp_punt_gun", name = "Punt Gun",
            cost = ducats(20), description = "Escopeta enorme cargada con hasta 25 onzas de posta.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(18),
            keywords = listOf("+1 DICE", "+1 INJURY DICE", "HEAVY", "SHOTGUN", "SHRAPNEL"),
            specialRules = listOf("Overcharge: gana BLAST 3\" y RELOAD; coloca 1 BLOOD MARKER y termina la activación.")
        ),
        Equipment.Shield(
            id = "tp_holy_icon_shield", name = "Holy Icon Shield",
            cost = glory(2), description = "Escudo hecho de iconos que han obrado milagros.",
            meleeDefenseBonus = 1, rangedDefenseBonus = 1, keywords = listOf("-1 INJURY MODIFIER", "IMPERVIOUS")
        ),
        Equipment.Gear(
            id = "tp_iron_capirote", name = "Iron Capirote",
            cost = ducats(7), description = "Casco cónico bendecido que contiene un fragmento de reliquia.",
            keywords = listOf("NEGATE FEAR", "NEGATE SHRAPNEL")
        ),
        Equipment.Grenade(
            id = "tp_warcross", name = "Warcross",
            cost = ducats(5), description = "Arma arrojadiza de cuatro puntas en forma de cruz.",
            range = WeaponRange.Ranged(8), keywords = listOf("ASSAULT", "IGNORE LONG RANGE")
        ),

        // ==================== IRON SULTANATE ====================
        Equipment.RangedWeapon(
            id = "is_alaybozan", name = "Alaybozan",
            cost = ducats(9), description = "Trabuco tribal modernizado, arma preferida del Cuerpo de Zapadores.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(12), keywords = listOf("SHRAPNEL")
        ),
        Equipment.RangedWeapon(
            id = "is_flame_cannon", name = "Flame Cannon",
            cost = ducats(60), description = "Gran cañón que dispara un chorro de fuego alquímico.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Ranged(12),
            keywords = listOf("FIRE", "HEAVY", "IGNORE ARMOUR")
        ),
        Equipment.RangedWeapon(
            id = "is_jezzail", name = "Jezzail",
            cost = ducats(7), description = "Arma larga de ánima lisa común entre los soldados del Sultán.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(18)
        ),
        Equipment.RangedWeapon(
            id = "is_murad_bombard", name = "M.U.R.A.D. Bombard",
            cost = ducats(50), description = "Mortero que disrumpe formaciones enemigas con su eco.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Ranged(36),
            keywords = listOf("BLAST 3\"", "HEAVY", "SCATTER")
        ),
        Equipment.RangedWeapon(
            id = "is_siege_jezzail", name = "Siege Jezzail",
            cost = ducats(30), description = "Jezzail de asedio de gran calibre.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(30)
        ),
        Equipment.MeleeWeapon(
            id = "is_assassins_dagger", name = "Assassin's Dagger",
            cost = ducats(15), description = "Hoja imbuida con veneno mortal forjado en sangre de asesinos.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY MODIFIER", "CRITICAL"),
            specialRules = listOf("Slashing Attack: permite retirada tras causar un BLOOD MARKER.")
        ),
        Equipment.DualPurposeWeapon(
            id = "is_halberd_gun", name = "Halberd-Gun",
            cost = ducats(20), description = "Símbolo de alto rango y excelente arma de combate.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.DualPurpose(24),
            keywords = listOf("ASSAULT", "BLOCK", "CUMBERSOME")
        ),
        Equipment.MeleeWeapon(
            id = "is_titan_zulfiqar", name = "Titan Zulfiqar",
            cost = ducats(30), description = "Espada abisal bifurcada, terrible y símbolo de prestigio.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+2 INJURY MODIFIER", "CRITICAL", "HEAVY")
        ),
        Equipment.Armor(
            id = "is_alchemist_armour", name = "Alchemist Armour",
            cost = ducats(50), description = "Armadura festoneada con Sellos de Salomón.",
            armorBonus = 2, keywords = listOf("-2 INJURY MODIFIER", "NEGATE FIRE", "NEGATE GAS"),
            specialRules = listOf("Protection From Harm: -1 INJURY DICE extra contra ataques FIRE/GAS.")
        ),
        Equipment.Gear(
            id = "is_alchemical_ammunition", name = "Alchemical Ammunition",
            cost = ducats(3), description = "Munición alquímica que guía los disparos con precisión.",
            keywords = listOf("ALCHEMICAL")
        ),
        Equipment.Gear(
            id = "is_cloak_of_alamut", name = "Cloak of Alamut",
            cost = ducats(25), description = "Capa que dobla la luz, haciendo al portador casi invisible.",
            keywords = listOf("STEALTH")
        ),
        Equipment.Gear(
            id = "is_marid_shovel", name = "Marid Shovel",
            cost = ducats(15), description = "Pala enorme de bronce alquímico prácticamente indestructible.",
            keywords = listOf("HEAVY", "COVER")
        ),
        Equipment.Gear(
            id = "is_takwin_anqa_bird", name = "Takwin Anqā Bird",
            cost = glory(2), description = "Ave takwin de visión mejorada que guía a su portador.",
            keywords = listOf("ENHANCED VISION")
        ),
        Equipment.Gear(
            id = "is_wind_amulet", name = "Wind Amulet",
            cost = ducats(10), description = "Amuleto que captura la esencia del Elemento del Viento.",
            keywords = listOf("GUSTS OF WIND")
        ),

        // ==================== HERETIC LEGIONS ====================
        Equipment.MeleeWeapon(
            id = "hl_blasphemous_staff", name = "Blasphemous Staff",
            cost = glory(2), description = "Bastón blasfemo que desata fuego infernal.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee, keywords = listOf("CRITICAL", "FIRE")
        ),
        Equipment.MeleeWeapon(
            id = "hl_hellblade", name = "Hellblade",
            cost = glory(1), description = "Arma forjada con mineral de las minas de Dis.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY DICE", "FIRE")
        ),
        Equipment.MeleeWeapon(
            id = "hl_sacrificial_blade", name = "Sacrificial Blade",
            cost = ducats(23), description = "Cuchillos rituales bendecidos por un gran demonio.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+2 INJURY MODIFIER", "RISKY")
        ),
        Equipment.MeleeWeapon(
            id = "hl_tartarus_claws", name = "Tartarus Claws",
            cost = ducats(15), description = "Garras hechas de manos cercenadas de Malebranche.",
            type = BattlekitType.SPECIAL, range = WeaponRange.Melee,
            keywords = listOf("CRITICAL", "CUMBERSOME"),
            specialRules = listOf("Paired Weapon: se toma como dos armas de 1 mano sin penalización por mano torpe.")
        ),

        // ==================== BLACK GRAIL ====================
        Equipment.MeleeWeapon(
            id = "bg_beelzebubs_axe", name = "Beelzebub's Axe",
            cost = ducats(30), description = "Hacha enorme con forma de mosca extendiendo sus alas.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+2 INJURY DICE", "HEAVY", "INFECTION MARKERS")
        ),
        Equipment.Shield(
            id = "bg_black_grail_shield", name = "Black Grail Shield",
            cost = ducats(20), description = "Escudo con la bendición del Señor de las Moscas, con voluntad propia.",
            meleeDefenseBonus = 1, rangedDefenseBonus = 1, keywords = listOf("-1 INJURY MODIFIER"),
            specialRules = listOf("Power of the Cult: puede reducir el ataque a distancia con una tirada de Success.")
        ),
        Equipment.Gear(
            id = "bg_compound_eyes_helmet", name = "Compound Eyes Helmet",
            cost = ducats(10), description = "Casco ancestral que se fusiona con el portador.",
            keywords = listOf("NEGATE SHRAPNEL", "ENHANCED VISION")
        ),
        Equipment.RangedWeapon(
            id = "bg_corruption_belcher", name = "Corruption Belcher",
            cost = ducats(30), description = "Arroja gas nocivo y vapores corrosivos de los pantanos de Bolgia.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(8),
            keywords = listOf("FLAMETHROWER", "GAS", "IGNORE ARMOUR")
        ),
        Equipment.Gear(
            id = "bg_grail_devotee", name = "Grail Devotee",
            cost = ducats(15), description = "Esclavo diminuto que añade fuerza a los ataques cuerpo a cuerpo de su amo.",
            keywords = listOf("DEVOTEE")
        ),
        Equipment.RangedWeapon(
            id = "bg_infested_rifle", name = "Infested Rifle",
            cost = ducats(15), description = "Rifle cuyos proyectiles semiconscientes buscan las juntas de cualquier armadura.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(18),
            keywords = listOf("ARMOUR-PIERCING")
        ),
        Equipment.Grenade(
            id = "bg_parasite_grenades", name = "Parasite Grenades",
            cost = ducats(15), description = "Granadas con gusanos mentales parasitarios.",
            range = WeaponRange.Ranged(8), keywords = listOf("ASSAULT", "IGNORE COVER", "IGNORE LONG RANGE"),
            specialRules = listOf("Terrifying Hallucinations: mueve al objetivo 3\" tras resolver la Injury Roll.")
        ),
        Equipment.MeleeWeapon(
            id = "bg_plague_blade", name = "Plague Blade",
            cost = ducats(7), description = "Arma portadora de la cepa viral de Beelzebub.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee, keywords = listOf("INFECTION MARKERS")
        ),
        Equipment.RangedWeapon(
            id = "bg_putrid_shotgun", name = "Putrid Shotgun",
            cost = ducats(20), description = "Cartuchos corroídos poseídos por demonios, repletos de larvas infectadas.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(12),
            keywords = listOf("+1 DICE", "ASSAULT", "INFECTION MARKERS", "SHOTGUN")
        ),
        Equipment.RangedWeapon(
            id = "bg_viscera_cannon", name = "Viscera Cannon",
            cost = ducats(50), description = "El arma más repugnante del arsenal del Culto; dispara las vísceras del propio portador.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(24),
            keywords = listOf("+2 DICE", "HEAVY"),
            specialRules = listOf("Self Sacrifice: coloca BLOOD MARKERS en el atacante y coloca INFECTION MARKERS en el objetivo.")
        ),

        // ==================== THE COURT ====================
        Equipment.RangedWeapon(
            id = "court_arquebus", name = "Arquebus",
            cost = ducats(8), description = "Arma antigua que incluso un Yoke Fiend puede operar.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(18),
            keywords = listOf("CUMBERSOME"),
            specialRules = listOf("Batter: puede usarse como arma de cuerpo a cuerpo de 2 manos.")
        ),
        Equipment.Gear(
            id = "court_crown_of_hellfire", name = "Crown of Hellfire",
            cost = ducats(15), description = "Corona ardiente sostenida por las manos de un alma condenada.",
            keywords = listOf("HELLFIRE")
        ),
        Equipment.MeleeWeapon(
            id = "court_headtaker", name = "Headtaker",
            cost = ducats(15), description = "Espada serpentina que halla las arterias de sus víctimas.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            specialRules = listOf("Ambush Weapon: +2 INJURY MODIFIER contra objetivos que aún no han sido Activados este Turno.")
        ),
        Equipment.Armor(
            id = "court_infernal_iron_armour", name = "Infernal Iron Armour",
            cost = ducats(50), description = "Armadura forjada simultáneamente en el Infierno y en la Tierra.",
            armorBonus = 2, keywords = listOf("-2 INJURY MODIFIER", "IMPERVIOUS")
        ),
        Equipment.MeleeWeapon(
            id = "court_malebranche_sword", name = "Malebranche Sword",
            cost = ducats(35), description = "Espadas abisales bifurcadas, símbolos de prestigio de los demonios.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("DEADLY", "HEAVY")
        ),
        Equipment.RangedWeapon(
            id = "court_ophidian_rifle", name = "Ophidian Rifle",
            cost = ducats(25), description = "Rifle de caza de ruedas forjado por el demonio Sabnock.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(30),
            keywords = listOf("HEAVY", "IGNORE COVER", "IGNORE LONG RANGE"),
            specialRules = listOf("Unnatural Inversion: los modificadores de Cover y Long Range otorgan +1 DICE.")
        ),
        Equipment.Gear(
            id = "court_restraining_muzzle_yoke", name = "Restraining Muzzle",
            cost = ducats(10), description = "Boza cruel que permite controlar a un Yoke Fiend.",
            keywords = listOf("RESTRAINED")
        ),
        Equipment.RangedWeapon(
            id = "court_serpent_assault_gun", name = "Serpent Assault Gun",
            cost = ducats(45), description = "Hand cannon gigantesco que contiene un portal a los pantanos del Quinto Círculo.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(36),
            keywords = listOf("ASSAULT", "AUTOMATIC 2", "GAS", "HEAVY")
        ),
        Equipment.MeleeWeapon(
            id = "court_torture_instrument", name = "Torture Instrument",
            cost = ducats(10), description = "Látigos de espinas vivas, varas al rojo y herramientas de tormento de Dis.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            specialRules = listOf("Agonising Injuries: tira 1D6 en la Injury Roll pero duplica los BLOOD MARKERS; incluye Torture ACTION.")
        ),
        Equipment.RangedWeapon(
            id = "court_fiendbound_gatling_gun", name = "Fiendbound Gatling Gun",
            cost = ducats(20), description = "Cañón giratorio construido en torno a un haz nervioso preservado de un Yoke Fiend.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(24), keywords = listOf("HEAVY", "SHRAPNEL"),
            specialRules = listOf("Excruciate: con STRONG o en contacto con un aliado, el ataque gana AUTOMATIC 2 y RELOAD.")
        ),
        Equipment.MeleeWeapon(
            id = "court_iron_maiden_flail", name = "Iron Maiden Flail",
            cost = glory(4), description = "Doncella de hierro unida a una cadena flexible; las púas destrozan la armadura más dura.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee, keywords = listOf("+1 DICE", "CUMBERSOME", "HEAVY"),
            specialRules = listOf("Crushed Inside: una Wretched puede ser sacrificada al inicio para que el ataque tenga IGNORE ARMOUR.")
        ),
        Equipment.Grenade(
            id = "court_jaws_of_iron", name = "Jaws of Iron",
            cost = ducats(5), description = "Cinturón de pequeñas trampas de osos que los Yoke Fiends lanzan antes de cargar.",
            range = WeaponRange.Ranged(6), keywords = listOf("-1 INJURY DICE", "ASSAULT", "IGNORE LONG RANGE"),
            specialRules = listOf("Snap Shut: si el ataque tiene éxito, +1 DICE a las Melee Attacks contra el mismo objetivo en la misma Activación.")
        ),
        Equipment.Gear(
            id = "court_chthonic_vessel", name = "Chthonic Vessel",
            cost = glory(1), description = "Alma mortal esclavizada y henchida de pecado para alimentar la hechicería del portador.",
            keywords = listOf("CONSUMABLE"),
            specialRules = listOf("Break Vessel ACTION: una vez por partida, coloca 2 SIN MARKERS junto al portador.")
        ),
        Equipment.MeleeWeapon(
            id = "court_tormentor_chain", name = "Tormentor Chain",
            cost = glory(3), description = "Cadena con ganchos de tormento usada por Hell Knights y Desecrated Saints.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee, keywords = listOf("+1 INJURY MODIFIER", "RISKY")
        ),
        Equipment.SpecialItem(
            id = "court_goetic_black_heart", name = "Black Heart (Goetic Power)",
            cost = glory(5), description = "Corazón corrupto con un agujero negro que refuerza las acciones del portador.",
            keywords = listOf("SPELL COST 1"),
            specialRules = listOf("Antes de una Success/Risky Roll del lanzador: +1 DICE.")
        ),
        Equipment.SpecialItem(
            id = "court_goetic_blessing_serpent_moon", name = "Blessing of the Serpent Moon (Goetic Power)",
            cost = glory(5), description = "Como arriba, así abajo: bendición defensiva del lanzador.",
            keywords = listOf("SPELL COST 2/4/6"),
            specialRules = listOf("Antes de una Injury Roll del lanzador: -1 INJURY MODIFIER por cada 2 SIN MARKERS pagados.")
        ),
        Equipment.SpecialItem(
            id = "court_goetic_infernal_challenge", name = "Infernal Challenge (Goetic Power)",
            cost = glory(10), description = "La gloria impía de los amos de un Hell Knight se manifiesta en combate.",
            keywords = listOf("ABILITY"),
            specialRules = listOf("+1 INJURY DICE en Melee Attacks contra modelos con el keyword TOUGH.")
        ),
        Equipment.SpecialItem(
            id = "court_goetic_raining_blood", name = "Raining Blood (Goetic Power)",
            cost = glory(10), description = "El lanzador traza lesiones en el cielo mismo.",
            keywords = listOf("SPELL COST 1-3"),
            specialRules = listOf("Cast Spell ACTION: marca tantos enemigos a 18\" como SIN MARKERS pagados con 1 BLOOD MARKER cada uno.")
        ),
        Equipment.SpecialItem(
            id = "court_goetic_slavemaster", name = "Slavemaster (Goetic Power)",
            cost = glory(10), description = "Los Yoke Fiends están sometidos a la voluntad de los nobles de la Corte.",
            keywords = listOf("SPELL COST 1"),
            specialRules = listOf("Cast Spell ACTION: da un Comando (Fight/Move/Sacrifice/Shoot) a un Yoke Fiend aliado a 18\".")
        ),

        // ==================== THE GREAT HUNGER (Black Grail variant) ====================
        Equipment.RangedWeapon(
            id = "gh_black_spot_rifle", name = "Black Spot Rifle",
            cost = ducats(25), description = "Armas recuperadas bendecidas por las Matagot Hags que disparan larvas de moscas infernales.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(18),
            keywords = listOf("+1 DICE", "-1 INJURY DICE", "ASSAULT", "INFECTION MARKERS"),
            specialRules = listOf("Fresh Meat: si no elimina al objetivo, un Ravenous/Hound a 12\" puede cargar de inmediato.")
        ),
        Equipment.MeleeWeapon(
            id = "gh_butcher_bayonet", name = "Butcher Bayonet",
            cost = ducats(15), description = "Cuchillas enormes que arrancan trozos dentados de los soldados del Dios Tirano.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee, keywords = listOf("+1 INJURY DICE", "CUMBERSOME"),
            specialRules = listOf("Twisted Addition: requiere un arma a distancia con Bayonet Lug.", "Hack and Hew: aliados a 3\" logran Critical con 11+.")
        ),
        Equipment.Gear(
            id = "gh_cup_of_filth", name = "Cup of Filth",
            cost = glory(4), description = "Líquido del río de corrupción vomitado por el Señor de las Moscas.",
            specialRules = listOf("Drink from the Cup: 1 ELITE o hasta 4 Ravenous beben; +1 DICE a sus Risky Rolls al Dash.")
        ),
        Equipment.Armor(
            id = "gh_foetid_palanquin", name = "Foetid Palanquin",
            cost = glory(4), description = "Palanquín cruciforme de hierro ungido con la bilis negra de Beelzebub.",
            armorBonus = 1, keywords = listOf("-1 INJURY MODIFIER"),
            specialRules = listOf("Bile Clot ACTION: una vez por Turno, retira INFECTION MARKERS a 18\" para ganar -1 INJURY MODIFIER por cada 2.")
        ),
        Equipment.MeleeWeapon(
            id = "gh_pestilent_fang", name = "Pestilent Fang",
            cost = ducats(15), description = "Mazas torcidas con cabezas de pulga, regalo de Beelzebub.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee, keywords = listOf("+1 INJURY MODIFIER", "HEAVY"),
            specialRules = listOf("Gnashing Blow: coloca 1 INFECTION MARKER al causar BLOOD MARKERS.", "Rotten Splinters: Critical Success otorga IGNORE ARMOUR.")
        ),
        Equipment.SpecialItem(
            id = "gh_strain_devouring_jaws", name = "Devouring Jaws (Hunger Strain)",
            cost = glory(2), description = "Cepa del Hambre que otorga una acción de Devorar cuerpo a cuerpo.",
            specialRules = listOf("Devour ACTION: Melee Attack sin arma con CRITICAL; si no elimina, recibe 1 BLOOD MARKER.")
        ),
        Equipment.SpecialItem(
            id = "gh_strain_grasping_maw", name = "Grasping Maw (Hunger Strain)",
            cost = ducats(15), description = "Cepa del Hambre que arrastra a un enemigo hasta 3\" hacia el portador.",
            specialRules = listOf("Grasp ACTION: Risky Roll; con éxito mueve 3\" a un enemigo a 12\" en línea recta hacia ti.")
        ),
        Equipment.SpecialItem(
            id = "gh_strain_hellfly_host", name = "Hellfly Host (Hunger Strain)",
            cost = ducats(0), description = "Cepa gratuita que otorga vuelo a cambio de perder Undead Fortitude.",
            specialRules = listOf("Movimiento 6\"/Flying y FLYING; pierde Undead Fortitude.")
        ),
        Equipment.SpecialItem(
            id = "gh_strain_lockjaw_bite", name = "Lockjaw Bite (Hunger Strain)",
            cost = ducats(15), description = "Cepa del Hambre que castiga a los enemigos que retroceden.",
            specialRules = listOf("Cuando un enemigo Retrocede a 1\", coloca 1 INFECTION MARKER antes de los ataques.")
        ),
        Equipment.SpecialItem(
            id = "gh_strain_papillal_hide", name = "Papillal Hide (Hunger Strain)",
            cost = ducats(5), description = "Cepa del Hambre que permite cargar sin línea de visión.",
            specialRules = listOf("No requiere Line of Sight para la Charge ACTION.")
        ),
        Equipment.SpecialItem(
            id = "gh_strain_rotten_cutters", name = "Rotten Cutters (Hunger Strain)",
            cost = ducats(10), description = "Cepa del Hambre que añade CLEAVE 2 a los ataques cuerpo a cuerpo.",
            specialRules = listOf("Añade el keyword CLEAVE 2 a las Melee Attacks.")
        ),
        Equipment.SpecialItem(
            id = "gh_strain_unending_starvation", name = "Unending Starvation (Hunger Strain)",
            cost = ducats(10), description = "Cepa del Hambre que añade +1\" al Movimiento.",
            specialRules = listOf("+1\" al Movimiento. Cada modelo puede tener 1 Cepa; los Ravenous hasta 3.")
        ),

        // ==================== DIRGE OF THE GREAT HEGEMON (Black Grail variant) ====================
        Equipment.Gear(
            id = "bg_broken_crown", name = "Broken Crown",
            cost = ducats(25), description = "Corona de púas hecha de los restos de la armadura de un Hegemon caído.",
            specialRules = listOf("Locus of Despair: al Activarse, coloca 1 INFECTION MARKER a cada enemigo a 1\".")
        ),
        Equipment.Gear(
            id = "bg_urn_of_the_bitter_ashes", name = "Urn of the Bitter Ashes",
            cost = ducats(40), description = "Urna negra con las cenizas del Hegemon caído; sus susurros protegen a los cercanos.",
            specialRules = listOf("Sinister Whispers: -1 DICE a Ranged Attacks contra el portador o modelos a 3\".")
        ),

        // ==================== TRENCH GHOSTS (Heretic variant) ====================
        Equipment.Armor(
            id = "hl_sarcophagus_mine", name = "Sarcophagus Mine",
            cost = ducats(30), description = "Armaduras cargadas de explosivos que pueden detonarse cuando el enemigo se acerca.",
            armorBonus = 3, keywords = listOf("-3 INJURY MODIFIER", "BLAST 3\""),
            specialRules = listOf("Walking Bomb: no puede llevar otro Battlekit; Trigger ACTION detona y elimina al portador.")
        ),
        Equipment.Armor(
            id = "hl_tank_palanquin", name = "Tank Palanquin",
            cost = ducats(60), description = "Plataforma de tiro blindada para Heretic Priests de los Trench Ghosts.",
            armorBonus = 3, keywords = listOf("-3 INJURY MODIFIER", "STRONG"),
            specialRules = listOf("Bulky: base 50mm, sin escudo, Charge Bonus D3\".", "Death From On High: +3\" de altura para Elevated Position.", "Standfast: un Down se trata como Minor Wound.")
        ),

        // ==================== KNIGHTS OF AVARICE (Heretic variant) ====================
        Equipment.MeleeWeapon(
            id = "hl_coin_hammer", name = "Coin Hammer",
            cost = ducats(20), description = "Martillo a dos manos con la runa de Mammon.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee, keywords = listOf("+1 INJURY DICE", "HEAVY"),
            specialRules = listOf("Rune of Mammon: si causa BLOOD MARKERS, coloca 1 BLESSING MARKER en el atacante.")
        ),
        Equipment.Gear(
            id = "hl_golden_calf_altar", name = "Golden Calf Altar",
            cost = ducats(20), description = "Altar portátil de Mammon que crea ilusiones de riqueza inmensa.",
            keywords = listOf("HEAVY"),
            specialRules = listOf("Illusions: el terreno a 3\" es Difficult incluso para voladores.", "Place Altar ACTION: despliega el altar como terreno Impassable.")
        ),
        Equipment.RangedWeapon(
            id = "hl_gas_bombs", name = "Gas Bombs",
            cost = ducats(0), description = "Bombas de gas de las Artillery Witches, que preservan el botín del fuego y el metralla.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Ranged(36),
            keywords = listOf("-1 INJURY DICE", "BLAST 3\"", "GAS", "IGNORE ARMOUR", "IGNORE COVER", "RELOAD", "SCATTER"),
            specialRules = listOf("Choking Gas: el objetivo no eliminado se tambalea D3\" alejándose del gas.")
        ),
        Equipment.Gear(
            id = "hl_standard_of_mammon", name = "Standard of Mammon",
            cost = ducats(25), description = "Estandarte blasfemo y opulento que obliga a los enemigos a arrodillarse.",
            keywords = listOf("HELD", "LEADER"),
            specialRules = listOf("Kneel Before Me: los enemigos Down a 1\" no se levantan; tirada al cargar contra el portador.")
        ),
        Equipment.Armor(
            id = "hl_tarnished_armour", name = "Tarnished Armour",
            cost = ducats(25), description = "Armadura dorada siempre empañada por la sangre; atrae la ira de los mortales.",
            armorBonus = 2, keywords = listOf("-2 INJURY MODIFIER", "NEGATE GAS"),
            specialRules = listOf("Target of Wrath: el enemigo debe cargar contra este modelo si puede alcanzarlo.")
        ),

        // ==================== NAVAL RAIDERS (Heretic variant) ====================
        Equipment.MeleeWeapon(
            id = "hl_boarding_axe", name = "Boarding Axe",
            cost = ducats(10), description = "Hachas de abordaje serradas grabadas con runas de ruina.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee, keywords = listOf("ARMOUR PIERCING", "CRITICAL", "RISKY"),
            specialRules = listOf("Boarding: +1 DICE a Risky Rolls al escalar superficies verticales.")
        ),
        Equipment.MeleeWeapon(
            id = "hl_hull_drill", name = "Hull Drill",
            cost = ducats(20), description = "Herramienta capaz de perforar el casco de un barco o la garganta de un enemigo blindado.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee, keywords = listOf("CRITICAL", "HELD", "IGNORE ARMOUR", "RISKY")
        ),
        Equipment.Gear(
            id = "hl_leviathan_bell", name = "Leviathan Bell",
            cost = ducats(10), description = "Casco sellado de aguas abisales que exime al Chorister de tiradas al trepar o caer.",
            keywords = listOf("NONE")
        ),
        Equipment.RangedWeapon(
            id = "hl_infernal_bomb", name = "Infernal Bomb",
            cost = ducats(0), description = "Minas marinas infernales lanzadas desde los reinos estigios contra las fortificaciones.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Ranged(36),
            keywords = listOf("BLAST 3\"", "IGNORE COVER", "IGNORE ELEVATED POSITION", "IGNORE LONG RANGE", "RELOAD", "SCATTER", "SHRAPNEL"),
            specialRules = listOf("Duck: -1 INJURY DICE si hay cobertura de 1/2\".", "Infernal Mine: puede dejarse como mina detonable (+10 Ducados).", "Infernal Strike: aplica DEADLY al objetivo directo.", "Mighty Explosion: el objetivo no eliminado es lanzado D3\".")
        ),
        Equipment.Gear(
            id = "hl_shark_tooth_talisman", name = "Shark Tooth Talisman",
            cost = ducats(10), description = "Talismán de dientes de tiburón cuyo portador no teme peligro alguno.",
            keywords = listOf("CONSUMABLE", "NEGATE FEAR"),
            specialRules = listOf("Blood Frenzy: una vez por partida, +1 DICE a Melee Attacks si hay BLOOD MARKERS a 1\".")
        ),
        Equipment.MeleeWeapon(
            id = "hl_wreckers_torch", name = "Wrecker's Torch",
            cost = ducats(15), description = "Antorcha alimentada por los fuegos del río Phlegethon, arde sin fin.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee, keywords = listOf("ARMOUR PIERCING", "BLOCK", "CUMBERSOME", "FIRE")
        )
    )
}
