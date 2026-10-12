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
        Equipment.RangedWeapon(
            id = "gh_vomitus", name = "Vomitus",
            cost = ducats(40), description = "Arma única de Gregori Gula (Unholy Gut): un torrente de bilis que empapa todo a su paso.",
            type = BattlekitType.SPECIAL, range = WeaponRange.Ranged(8),
            keywords = listOf("+1 INJURY MODIFIER", "ASSAULT", "INFECTION MARKERS"),
            specialRules = listOf("Torrent of Bile: no hace Success Roll; mide una línea de 1mm x 8\" y haz Injury Roll a cada modelo tocado.", "Vile Ferment: puedes retirar 2 INFECTION MARKERS del Gregori Gula para dar +1 INJURY MODIFIER a los Injury Rolls del ataque.")
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
        ),

        // ==================== PAPAL STATES (New Antioch variant) ====================
        Equipment.SpecialItem(
            id = "na_papal_crucifix", name = "Supreme Pontiff's Crucifix",
            cost = glory(3), description = "Reliquia sagrada bendecida por el Supremo Pontífice que inspira a su portador.",
            specialRules = listOf("Inspiring Relic: el portador no termina su activación tras fallar una Risky Success Roll.")
        ),

        // ==================== KINGDOM OF ALBA (New Antioch variant) ====================
        Equipment.MeleeWeapon(
            id = "na_alba_lochaber_axe", name = "Lochaber Axe",
            cost = ducats(20), description = "Arma de Alba que combina la fuerza de un hacha de batalla con la defensa de una pica.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+2 INJURY DICE", "BLOCK", "CRITICAL", "CUMBERSOME", "HEAVY")
        ),

        // ==================== STOSSTRUPPEN (New Antioch variant) ====================
        Equipment.MeleeWeapon(
            id = "na_prussia_tank_splitter_sword", name = "Tank-Splitter Sword",
            cost = ducats(15), description = "Espadas legendarias de la Gardekorps tratadas para fundir blindajes.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY DICE", "CRITICAL", "CUMBERSOME"),
            specialRules = listOf("Melt Armour: un dado se fija en '6' si el objetivo lleva armadura, escudo o ARM -1 o mejor.")
        ),
        Equipment.Gear(
            id = "na_prussia_holy_smoke", name = "Holy Smoke",
            cost = ducats(15), description = "Incienso adictivo inhalado antes de la batalla para ganar resiliencia.",
            keywords = listOf("CONSUMABLE"),
            specialRules = listOf("Chemical Incense: al desplegar, NEGATE FEAR y -1 INJURY DICE el resto de la partida.")
        ),

        // ==================== ABYSSINIA (New Antioch variant) ====================
        Equipment.MeleeWeapon(
            id = "na_abyssinia_shotel", name = "Shotel",
            cost = ducats(5), description = "Espada curva en forma de hoz del guerrero-élite etíope, hecha para alcanzar tras los escudos.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("CRITICAL"),
            specialRules = listOf("Bypass Shield: ignora los -INJURY DICE/MODIFIERS de Escudos en ataques de melee.")
        ),
        Equipment.Gear(
            id = "na_abyssinia_holy_water", name = "Holy Water of Lalibela",
            cost = ducats(3), description = "Frascos de agua bendita de las iglesias excavadas en la roca de Lalibela.",
            specialRules = listOf("Healing Waters: +1 DICE al retirar BLOOD/INFECTION MARKERS de un modelo.")
        ),
        Equipment.Gear(
            id = "na_abyssinia_anfarro", name = "Anfarro (Warriors Crown)",
            cost = ducats(10), description = "Corona de crin de león concedida por el Emperador como honor y valentía.",
            keywords = listOf("NEGATE FEAR"),
            specialRules = listOf("Token of Honour: +1 DICE a la característica de Melee del portador.")
        ),
        Equipment.SpecialItem(
            id = "na_abyssinia_tabot", name = "Tabot",
            cost = glory(4), description = "Réplica bendita del Arca de la Alianza portada por el sacerdote que acompaña a la fuerza.",
            specialRules = listOf("Virtuous Rewards: coloca 1 BLESSING MARKER cuando una acción aliada retire BLOOD/INFECTION MARKERS.")
        ),

        // ==================== RED BRIGADE (New Antioch variant) ====================
        Equipment.Gear(
            id = "na_red_blood_cloak", name = "Blood Cloak",
            cost = ducats(5), description = "Capas rojas entregadas a los veteranos que sobrevivieron a los asaltos enemigos.",
            keywords = listOf("SKIRMISHER")
        ),
        Equipment.MeleeWeapon(
            id = "na_red_banner", name = "Red Banner",
            cost = glory(2), description = "Estandarte remendado hecho con la capa ensangrentada de San Ernesto.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("BLOCK", "HELD"),
            specialRules = listOf("Blood of Martyrs: +2 DICE a los chequeos de Moral mientras donde y su portador no esté Down/Out.")
        ),
        Equipment.Gear(
            id = "na_red_atonement_bell", name = "Atonement Bell",
            cost = ducats(0), description = "Campana entregada al Crimson Communicant; canaliza el dolor del Segundo Meta-Cristo.",
            keywords = listOf("HELD"),
            specialRules = listOf("Channelled Sorrow: ataque de mano torpe que mueve al objetivo D3\" en vez de herir.")
        ),

        // ==================== PROCESSIONS OF THE SACRED AFFLICTION (Trench Pilgrims variant) ====================
        Equipment.Gear(
            id = "tp_affliction_bells_of_warding", name = "Bells of Warding",
            cost = glory(2), description = "Campanas reunidas y tocadas por un miembro elegido, declarando la unidad de la Procesión.",
            keywords = listOf("HELD", "LEADER"),
            specialRules = listOf("Gathering Call: +1 DICE a las Risky Rolls de Dash de aliados a 4\" de una campana o instrumento.")
        ),
        Equipment.Gear(
            id = "tp_affliction_blessed_millstone", name = "Blessed Millstone",
            cost = ducats(5), description = "Piedra de molino consagrada cargada con las plegarias de almas penitentes.",
            specialRules = listOf("Weighted with Sin: -1\" Movimiento y +2 INJURY DICE; puede liberarse tras eliminar a un enemigo.")
        ),
        Equipment.MeleeWeapon(
            id = "tp_affliction_great_flail", name = "Great Flail/Scourge",
            cost = ducats(15), description = "Manguales de piedras atadas con cuerda de cáñamo, símbolo del peso del pecado.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 DICE", "+1 INJURY DICE", "CUMBERSOME", "HEAVY")
        ),
        Equipment.Armor(
            id = "tp_affliction_holy_icon_armour", name = "Holy Icon Armour",
            cost = ducats(30), description = "Armadura hecha de iconos benditos y escritura sagrada escrita con sangre de santos.",
            armorBonus = 1, keywords = listOf("-1 INJURY MODIFIER", "IMPERVIOUS")
        ),
        Equipment.Gear(
            id = "tp_affliction_penitents_phylactery", name = "Penitent's Phylactery",
            cost = ducats(12), description = "Caja de cuero con obleas de las llagas benditas de la Sacred Affliction.",
            specialRules = listOf("Repudiation of Sin: +1 BLOOD MARKER al atacar a HERETIC, THE COURT o BLACK GRAIL.")
        ),
        Equipment.Armor(
            id = "tp_affliction_ragged_vestments", name = "Ragged Vestments",
            cost = ducats(10), description = "Ropas andrajosas de peregrinos que desafiaron los precipicios de la Creación.",
            specialRules = listOf("Leap of Faith: +1 DICE a Risky Rolls al Escalar, Saltar o hacer Dash sin escudo ni armas HEAVY.")
        ),

        // ==================== WAR PILGRIMAGE OF SAINT METHODIUS (Trench Pilgrims variant) ====================
        Equipment.RangedWeapon(
            id = "tp_methodius_autocannon", name = "Autocannon",
            cost = ducats(55), description = "Cañón automático de gran calibre montado en un Anchorite Shrine desde el que dispara sin pausa.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(48),
            keywords = listOf("+1 INJURY DICE", "AUTOMATIC 3", "HEAVY"),
            specialRules = listOf("Full Auto: elige el perfil Bursts o Full Auto (+1 INJURY DICE, AUTOMATIC 5, HEAVY, RELOAD, RISKY).")
        ),
        Equipment.RangedWeapon(
            id = "tp_methodius_gas_censer", name = "Gas Censer",
            cost = ducats(50), description = "Arma química letal y objeto de veneración que libera una nube de gas indiscriminada.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(6),
            keywords = listOf("GAS", "IGNORE ARMOUR", "RELOAD"),
            specialRules = listOf("Cloud of Gas: golpea a todos los modelos a 6\" sin tirada de Success (aliados y enemigos).")
        ),
        Equipment.RangedWeapon(
            id = "tp_methodius_trench_mortar", name = "Trench Mortar",
            cost = ducats(40), description = "Mortero de avancarga que dispara proyectiles incendiarios y explosivos en arco.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(36),
            keywords = listOf("+1 INJURY DICE", "BLAST 3\"", "FIRE", "HEAVY", "IGNORES COVER", "SCATTER"),
            specialRules = listOf("High Trajectory: el objetivo no puede estar a menos de 6\" del atacante.")
        ),
        Equipment.Gear(
            id = "tp_methodius_gas_filters", name = "Gas Filters",
            cost = ducats(5), description = "Sistema de incienso sagrado que purifica el aire respirado por el monje del Anchorite.",
            keywords = listOf("NEGATE GAS")
        ),
        Equipment.Gear(
            id = "tp_methodius_grand_anchorite", name = "Grand Anchorite",
            cost = ducats(15), description = "Construye al Anchorite a un tamaño extraordinario, casi imposible de detener.",
            specialRules = listOf("Impossible to Stop: los enemigos a 1\" no pueden atacar al retirarse; puede mover/cargar desde 1\" de enemigos.")
        ),
        Equipment.Gear(
            id = "tp_methodius_hallowed_anchorite", name = "Hallowed Anchorite",
            cost = ducats(10), description = "Anchorite ungido con crisma sagrado, apto para ser promovido a ELITE.",
            specialRules = listOf("Manifold Blessings: puede ascenderse a ELITE en la Fase de Campaña.")
        ),
        Equipment.Gear(
            id = "tp_methodius_holy_diesel_engine", name = "Holy Diesel Engine",
            cost = ducats(10), description = "Motor diésel avanzado con refrigeración experimental que mejora el Dash.",
            specialRules = listOf("Advanced Design: +2 DICE a la Risky Roll del Dash a cambio de 1 BLOOD MARKER.")
        ),
        Equipment.Gear(
            id = "tp_methodius_piston_legs", name = "Piston Legs",
            cost = ducats(10), description = "Motores de pistón en los pies diseñados para triturar a los caídos.",
            specialRules = listOf("Grind to Dust ACTION: Melee Attack contra un enemigo Down de base 32mm o menor.")
        ),
        Equipment.Gear(
            id = "tp_methodius_sacred_geometry", name = "Sacred Geometry",
            cost = ducats(10), description = "Geometrías que muestran el mundo a través de las matemáticas divinas.",
            specialRules = listOf("Divine Accuracy: +1 DICE a la característica Ranged del Anchorite.")
        ),
        Equipment.Gear(
            id = "tp_methodius_wrathful_cherub_face", name = "Wrathful Cherub Face",
            cost = ducats(20), description = "Rostro angelical que encarna la ira del Señor y hace retroceder a los enemigos.",
            specialRules = listOf("Cower Before The Lord: los enemigos a 1\" deben hacer Retreat y no pueden hacer Risky Actions.")
        ),

        // ==================== CAVALCADE OF THE TENTH PLAGUE (Trench Pilgrims variant) ====================
        Equipment.Gear(
            id = "tp_plague_sacrificial_lamb", name = "Sacrificial Lamb",
            cost = ducats(5), description = "Cordero sacrificado antes de la batalla para ungir al Peregrino con su sangre.",
            keywords = listOf("CONSUMABLE"),
            specialRules = listOf("Anointed: ignora el primer BLOOD MARKER del portador; los Ecclesiastic Prisoners no pueden llevarlo.")
        ),

        // ==================== FIDA'I OF ALAMUT (Iron Sultanate variant) ====================
        Equipment.RangedWeapon(
            id = "is_fidai_bow_of_alamut", name = "Bow of Alamut",
            cost = ducats(50), description = "Arco de doble cuerda cuyas flechas viajan por el tiempo y el espacio hasta el objetivo.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(40),
            keywords = listOf("CRITICAL", "IGNORE ARMOUR"),
            specialRules = listOf("Temporal Slipstream: si causa BLOOD MARKERS, redespliega al atacante a 1\" del objetivo.")
        ),
        Equipment.MeleeWeapon(
            id = "is_fidai_golden_khanjar", name = "Golden Khanjar",
            cost = ducats(20), description = "Daga curva de veneno cristalizado entregada por el Viejo de la Montaña.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY MODIFIER"),
            specialRules = listOf("Attack Twice: puede hacer 2 Melee Attacks seguidas con esta arma.")
        ),
        Equipment.Gear(
            id = "is_fidai_hashashin_leaf", name = "Hashashin Leaf",
            cost = ducats(5), description = "Hoja del Jardín secreto de Alamut que triplica la fuerza de quien la come.",
            keywords = listOf("CONSUMABLE"),
            specialRules = listOf("Enhanced Strength: al desplegar, el portador gana STRONG el resto de la partida.")
        ),

        // ==================== THE HOUSE OF WISDOM (Iron Sultanate variant) ====================
        Equipment.Gear(
            id = "is_wisdom_elixir_of_al_khidr", name = "Elixir of Al-Khidr",
            cost = ducats(10), description = "Fórmula alquímica elaborada con agua de la Fuente de la Vida oculta.",
            keywords = listOf("CONSUMABLE"),
            specialRules = listOf("Fountain of Life: al desplegar, el portador gana TOUGH; no valen modelos ARTIFICIAL.")
        ),
        Equipment.Shield(
            id = "is_wisdom_fire_shield", name = "Fire Shield",
            cost = ducats(20), description = "Invención de la Escuela al-Jazari tratada para resistir el fuego.",
            meleeDefenseBonus = 1, rangedDefenseBonus = 1, keywords = listOf("-1 INJURY MODIFIER", "NEGATE FIRE"),
            specialRules = listOf("Fire Shield: -1 INJURY DICE extra contra ataques FIRE, incluso con IGNORE ARMOUR.")
        ),

        // ==================== HIGH CATHEDRA OF PRIDE (Court variant) ====================
        Equipment.RangedWeapon(
            id = "court_pride_lightbringer_rifle", name = "Lightbringer Rifle",
            cost = ducats(40), description = "Rifle cuyas balas brillan con la luz del alba, castigando a los enemigos situados en alto.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(48),
            keywords = listOf("CRITICAL"),
            specialRules = listOf("Pride Comes Before the Fall: +3 DICE si el objetivo está 2\" más alto; el objetivo cae si está junto a un borde.")
        ),
        Equipment.Armor(
            id = "court_pride_cloak_of_the_morningstar", name = "Cloak of the Morningstar",
            cost = glory(3), description = "Capa angélica quemada cuando cayó el Lucero; quien la viste nunca cede terreno.",
            armorBonus = 3, keywords = listOf("-3 INJURY MODIFIER", "INNATE"),
            specialRules = listOf("Defiant Rebellion: no puede ser obligado a moverse; reemplaza permanentemente cualquier armadura.")
        ),
        Equipment.Gear(
            id = "court_pride_glorious_light", name = "Glorious Light",
            cost = ducats(20), description = "Luz imposiblemente brillante que hace difícil mirar al portador en batalla.",
            specialRules = listOf("Glorious to Behold: -1 DICE a las Success Rolls de los ataques contra el portador.")
        ),
        Equipment.Gear(
            id = "court_pride_petals_of_blood_roses", name = "Petals of Blood Roses",
            cost = ducats(5), description = "Pétalos venenosos esparcidos por los Wretched mientras avanzan sus amos demoníacos.",
            specialRules = listOf("Glorious Advance: reposiciona a un DEMONIC aliado a 3\" golpeado por BLAST.")
        ),
        Equipment.SpecialItem(
            id = "court_pride_laurels_of_the_supreme", name = "Laurels of the Supreme",
            cost = glory(3), description = "Corona de tejas de oro arrancadas de los muros del Cielo.",
            keywords = listOf("NEGATE SHRAPNEL"),
            specialRules = listOf("All are Beneath Me: con línea de visión a un enemigo 2\" por debajo, coloca 1 BLESSING MARKER.")
        ),

        // ==================== GOLDEN SEAT OF GREED (Court variant) ====================
        Equipment.RangedWeapon(
            id = "court_greed_ring_gun", name = "40-Bore Ring Gun",
            cost = ducats(25), description = "Escopeta de doble cañón que dispara una lluvia tintineante de posta de ducados.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Ranged(12),
            keywords = listOf("+1 DICE", "AUTOMATIC 2", "SHOTGUN", "SHRAPNEL"),
            specialRules = listOf("Coin Song: si los dos ataques del Shoot son Success, coloca 1 BLESSING MARKER.")
        ),
        Equipment.MeleeWeapon(
            id = "court_greed_herodic_hammer", name = "Herodic Hammer",
            cost = ducats(15), description = "Martillo forjado con la orfebrería saqueada de monarcas caídos.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY MODIFIER", "CUMBERSOME", "HEAVY"),
            specialRules = listOf("Beneath Me: +1 INJURY DICE contra modelos de menor coste.", "Press Down: segundo ataque contra enemigos no ELITE de base 32mm o menor.")
        ),
        Equipment.Gear(
            id = "court_greed_queens_bones", name = "Queen's Bones",
            cost = ducats(5), description = "Restos grotescamente modificados de actuarios Wretched de Mammon.",
            specialRules = listOf("Indentured Computation: +1 DICE a los ataques de aliados a 1\" contra modelos por encima del umbral.")
        ),
        Equipment.Gear(
            id = "court_greed_stolen_sentimentality", name = "Stolen Sentimentality",
            cost = ducats(12), description = "Pequeñas jaulas y frascos de recuerdos robados a los muertos.",
            keywords = listOf("-1 INJURY MODIFIER", "CONSUMABLE"),
            specialRules = listOf("Kill Your Darlings: los ataques con IGNORE ARMOUR ignoran esta protección.")
        ),
        Equipment.SpecialItem(
            id = "court_greed_esaus_bargain", name = "Esau's Bargain",
            cost = glory(5), description = "Contrato infernal del Banco de Mammon cosido en la corona del portador.",
            specialRules = listOf("Of the Field: duplica los PX ganados.", "Mnemoharvest: trata un resultado de Trauma como Full Recovery a cambio de perder 1 Skill.")
        ),

        // ==================== FLESH DIVAN OF LUST (Court variant) ====================
        Equipment.Gear(
            id = "court_lust_altar_of_torment", name = "Altar of Torment",
            cost = ducats(60), description = "Versión de la Corte del Altar de Tormento, colocada como equipo del Flesh Divan.",
            specialRules = listOf("Usa las reglas estándar del Altar of Torment del Glory Item Cartulary.")
        ),
        Equipment.SpecialItem(
            id = "court_lust_astragaloi", name = "Astrágaloi",
            cost = glory(2), description = "Dados de juego hechos de huesos, escombros o llagas benditas calcificadas.",
            specialRules = listOf("Tempt Fate ACTION: apuesta contra el destino de un enemigo a 6\" con 2D6.")
        ),
        Equipment.Gear(
            id = "court_lust_horn_of_luxuria", name = "Horn of Luxuria",
            cost = ducats(30), description = "Instrumento flautado del reino de la Lujuria; su música agudiza la sensibilidad de los mortales.",
            specialRules = listOf("Black Serenade: gasta 1 SIN MARKER para +1 DICE al Dash aliado.", "Rapt in Pleasure: los enemigos Down a 4\" deben tirar para levantarse.")
        ),
        Equipment.DualPurposeWeapon(
            id = "court_lust_lance_of_asmoday", name = "Lance of Asmoday",
            cost = ducats(30), description = "Finas espinas de ónice modeladas según la lanza-estandarte de la Cabeza de la Lujuria.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.DualPurpose(18),
            keywords = listOf("+1 DICE", "CUMBERSOME", "IGNORE LONG RANGE"),
            specialRules = listOf("Yours to Take: mueve al objetivo D6\" hacia el atacante.", "New Sensations: un Crítico derriba a los enemigos a 2\".")
        ),
        Equipment.MeleeWeapon(
            id = "court_lust_naamahrite_scourge", name = "Naamahrite Scourge",
            cost = ducats(20), description = "Látigo que apela al ansia de la víctima por ser dominada.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY MODIFIER", "SHRAPNEL", "RISKY"),
            specialRules = listOf("As the Tyrant Made Us: el objetivo puede aceptar más daño para negar un SIN MARKER al atacante.")
        ),
        Equipment.Gear(
            id = "court_lust_spoils_of_lust", name = "Spoils of Lust",
            cost = ducats(5), description = "Corsés de hierro con púas y arneses ganchudos llevados por los Wretched sádicos.",
            specialRules = listOf("Distressing Presence: coloca 1 BLOOD MARKER en un enemigo que se active a 1\".")
        ),

        // ==================== BLACK KLISMOS OF ENVY (Court variant) ====================
        Equipment.Gear(
            id = "court_envy_brand_of_moloch", name = "Brand of Moloch",
            cost = ducats(10), description = "La marca de Moloch, el Rey Infanticida; sacrifica un Wretched para lanzar hechicería gratis.",
            specialRules = listOf("Infanticide ACTION: elimina un Wretched a 1\" para lanzar el siguiente Goetic Spell sin coste.")
        ),
        Equipment.SpecialItem(
            id = "court_envy_craven_vassalage", name = "Craven Vassalage",
            cost = glory(5), description = "Juramento de lealtad a Moloch o Leviatán que remodela a un Hell Knight.",
            keywords = listOf("INNATE"),
            specialRules = listOf("Agent of Change: elige Beacon of Corruption o Burning Inferno; también FLYING 6\" y base 50mm.")
        ),
        Equipment.MeleeWeapon(
            id = "court_envy_lying_knife", name = "Lying Knife",
            cost = ducats(12), description = "Hoja de piedra poseída por el deseo transformador de la Envidia.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY MODIFIER", "RISKY"),
            specialRules = listOf("Mercurial Blade: copia un Keyword del arma de melee del objetivo para el ataque.")
        ),
        Equipment.Shield(
            id = "court_envy_pyrite_mirror_pavise", name = "Pyrite Mirror Pavise",
            cost = ducats(30), description = "Losa de hierro infernal cubierta de runas y pirita.",
            meleeDefenseBonus = 1, rangedDefenseBonus = 1, keywords = listOf("-1 INJURY MODIFIER"),
            specialRules = listOf("Mocking Reflection ACTION: copia una ACTION de un enemigo a 1\".")
        ),
        Equipment.Gear(
            id = "court_envy_usurpers_saw_wire", name = "Usurper's Saw-Wire",
            cost = ducats(10), description = "Alambre sierra para decapitar a quienes 'no merecen' su estatus.",
            specialRules = listOf("De-Coronate ACTION: +2 INJURY DICE contra ELITE o bases mayores.", "Bitterly Unsatisfying: 2 SIN MARKERS si elimina al objetivo.")
        ),

        // ==================== HUNGERING THRONE OF GLUTTONY (Court variant) ====================
        Equipment.Gear(
            id = "court_gluttony_jawed_bascinet", name = "Jawed Bascinet",
            cost = ducats(20), description = "Yelmo voraz con mandíbulas de dientes pulidos que desgarran y sanan al portador.",
            keywords = listOf("NEGATE SHRAPNEL"),
            specialRules = listOf("Ravening Jaws: gana REGENERATE 3 mientras esté a 1\" de cualquier enemigo.")
        ),
        Equipment.SpecialItem(
            id = "court_gluttony_lanx_ciborum", name = "Lanx Ciborum Exquisitorum",
            cost = glory(8), description = "Vasija que se rellena sin fin con delicias de Baal; atrae a los mortales cercanos.",
            keywords = listOf("HELD", "REGENERATE 6"),
            specialRules = listOf("Irresistible Delights: los enemigos a 8\" hacen Risky Rolls y son empujados 3\" hacia ella.")
        ),
        Equipment.MeleeWeapon(
            id = "court_gluttony_meaty_cleaver", name = "Meaty Cleaver",
            cost = ducats(25), description = "Cuchilla de carnicero enorme y mellada que arranca trozos de carne con facilidad sobrehumana.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY DICE", "CLEAVE 2", "CRITICAL", "CUMBERSOME", "HEAVY")
        ),
        Equipment.Gear(
            id = "court_gluttony_ostentatious_ornamentation", name = "Ostentatious Ornamentation",
            cost = ducats(15), description = "Tocado escandaloso del líder; el suelo mismo cede ante sus excesos.",
            specialRules = listOf("Part a Path: el terreno en contacto (hasta 8\"x8\") es Difficult para enemigos y Open para aliados.")
        ),
        Equipment.Gear(
            id = "court_gluttony_tawdry_finery", name = "Tawdry Finery",
            cost = ducats(45), description = "Alarde de joyas falsas y pieles robadas que atrae la atención y protege a los cercanos.",
            specialRules = listOf("Most Conspicuous: cuenta con el mayor coste para Conspicuous Consumption; -1 DICE a Ranged contra aliados a 3\".")
        ),

        // ==================== IRON CURULE OF WRATH (Court variant) ====================
        Equipment.Gear(
            id = "court_wrath_apollyons_ember", name = "Apollyon's Ember",
            cost = ducats(10), description = "Brasa permanente del dominio de Apollyon; los ataques de melee del portador arden.",
            keywords = listOf("FIRE"),
            specialRules = listOf("Undying Fire: añade el keyword FIRE a todos los ataques de melee.")
        ),
        Equipment.Gear(
            id = "court_wrath_lesser_mark_of_cain", name = "Lesser Mark of Cain",
            cost = ducats(25), description = "Copia inferior de la marca de Caín, el Primer Asesino.",
            specialRules = listOf("Murderer: el portador tiene el keyword -1 INJURY DICE.")
        ),
        Equipment.Gear(
            id = "court_wrath_locust_gait", name = "Locust Gait",
            cost = ducats(10), description = "Imitando el movimiento de las Pit Locusts, el Hell Knight de Ira obtiene vuelo.",
            keywords = listOf("FLYING"),
            specialRules = listOf("Imitation Flight: cambia el movimiento a 6\"/Flying.")
        ),
        Equipment.MeleeWeapon(
            id = "court_wrath_ruinous_pernach", name = "Ruinous Pernach",
            cost = ducats(15), description = "Maza nudosa con runas de odio y ruina en sus flanges afilados.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+1 INJURY MODIFIER", "HEAVY"),
            specialRules = listOf("Crushed Plate: tras un primer ataque con ella, los siguientes contra el mismo objetivo ganan ARMOUR PIERCING.")
        ),
        Equipment.MeleeWeapon(
            id = "court_wrath_spear_of_apollyon", name = "Spear of Apollyon",
            cost = glory(6), description = "Arma de asta monstruosa de hierro aún candente, templada en sangre de los Caídos.",
            type = BattlekitType.TWO_HANDED, range = WeaponRange.Melee,
            keywords = listOf("+2 INJURY DICE", "BLOCK", "CUMBERSOME", "FIRE"),
            specialRules = listOf("Rend the World ACTION: traza una línea de 12\" y reparte BLOOD MARKERS gastando SIN MARKERS.")
        ),

        // ==================== SILK BED OF SLOTH (Court variant) ====================
        Equipment.DualPurposeWeapon(
            id = "court_sloth_desidian_matchlock", name = "Desidian Matchlock",
            cost = ducats(10), description = "Invención de Belphegor que prescinde de la necesidad de entrenamiento.",
            type = BattlekitType.ONE_HANDED, range = WeaponRange.DualPurpose(12),
            keywords = listOf("PISTOL"),
            specialRules = listOf("Half-Cocked: +1 INJURY MODIFIER si el objetivo ya se activó este Turno.")
        ),
        Equipment.Gear(
            id = "court_sloth_flower_of_lytos", name = "Flower of Lytos",
            cost = ducats(15), description = "Opioide espiritual cultivado en suelo bañado en sangre.",
            keywords = listOf("CONSUMABLE"),
            specialRules = listOf("Lotus Eater: al cargar, el objetivo es forzado a cargar hacia el portador.")
        ),
        Equipment.SpecialItem(
            id = "court_sloth_iscariots_rope", name = "Iscariot's Rope",
            cost = glory(4), description = "Trozo de la cuerda que Judas usó para quitarse la vida.",
            keywords = listOf("CONSUMABLE"),
            specialRules = listOf("Unsummon: el portador se elimina como Full Recovery en el Trauma Step.")
        ),
        Equipment.Gear(
            id = "court_sloth_shroud_of_balak", name = "Shroud of Balak",
            cost = ducats(50), description = "Pesadas ropas tejidas con el cabello y la médula del cadáver consciente del rey Balak.",
            keywords = listOf("TOUGH"),
            specialRules = listOf("Weltschmerz Weave: no puede hacer Dash; puede lanzar dos veces el mismo Goetic Spell pagando su coste cada vez.")
        )
    )
}
