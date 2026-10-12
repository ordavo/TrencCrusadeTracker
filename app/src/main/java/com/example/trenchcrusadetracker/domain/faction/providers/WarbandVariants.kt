package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.model.faction.FactionRule
import com.example.trenchcrusadetracker.domain.model.faction.FactionVariant

/**
 * Catálogo central de subfacciones (variantes de banda) del libro de reglas.
 *
 * Se centraliza aquí para mantener los providers de facción base pequeños y
 * cumplir el principio Open/Closed: agregar una variante nueva es añadir una
 * entrada a la lista correspondiente sin tocar la lógica de cada provider.
 *
 * Fuente: Trench Crusade Rulebook + suplementos (Red Brigade, Great Hunger).
 * Nota: las variantes reutilizan por ahora el roster de la facción base; más
 * adelante se podrán refinar las restricciones de unidades por variante.
 */
internal object WarbandVariants {

    // ------------------------------------------------------------------ New Antioch
    val newAntioch: List<FactionVariant> = listOf(
        FactionVariant(
            id = "antioch_papal_states",
            name = "Papal States Intervention Force",
            description = "Elite warrior-monks and priests handpicked by the Pope himself.",
            defaultStartingDucats = 500,
            defaultStartingGloryPoints = 11,
            specialRules = listOf(
                FactionRule("rule_papal_specialist", "Specialist Force", "Starts with 500 👑 and 11 ☼. Threshold Value is reduced by 200 ducats."),
                FactionRule("rule_papal_lector", "Lector", "The Warband must include a Trench Cleric."),
                FactionRule("rule_papal_supreme_blessing", "Supreme Blessing", "Elite models receive free BLESSING MARKERS."),
                FactionRule("rule_papal_swiss_guard", "Swiss Guard", "Gains 4 ☼ per Reinforcement instead of the usual amount.")
            )
        ),
        FactionVariant(
            id = "antioch_eire_rangers",
            name = "Éire Rangers",
            description = "Guerrilla warrior-hunters of the Poet-King Tadhg O'Connor.",
            specialRules = listOf(
                FactionRule("rule_eire_anointed_ammo", "Anointed Ammunition", "Models can take Armour-Piercing Bullets at 5 👑 each (Limit: 2)."),
                FactionRule("rule_eire_berserker", "Berserker", "The Lieutenant or a Fianna may become a Berserker for +15 👑; cannot wear Armour, gains NEGATE FEAR."),
                FactionRule("rule_eire_fianna", "Fianna", "Trench Pilgrims can gain INFILTRATOR and SKIRMISHER for +5 👑 each."),
                FactionRule("rule_eire_hit_and_run", "Hit and Run", "Enemies suffer -1 DICE when attacking a Retreating Ranger.")
            )
        ),
        FactionVariant(
            id = "antioch_alba",
            name = "Kingdom of Alba Assault Detachment",
            description = "Highland close-quarters assault detachment relying on cold steel.",
            specialRules = listOf(
                FactionRule("rule_alba_bagpipes", "Bagpipes", "One Musical Instrument becomes Bagpipes; friendly models within 8\" gain NEGATE FEAR."),
                FactionRule("rule_alba_brave", "Brave", "+1 DICE to Morale Checks."),
                FactionRule("rule_alba_celtic", "Celtic Machine Armour", "Machine Armour keeps a Charge Bonus of D6\"."),
                FactionRule("rule_alba_cold_steel", "Cold Steel", "Halve the cost of the first Melee Weapon purchased."),
                FactionRule("rule_alba_highland", "Highland Strength", "The Lieutenant and Shock Troopers gain STRONG for free.")
            )
        ),
        FactionVariant(
            id = "antioch_prussia",
            name = "Stosstruppen of the Free State of Prussia",
            description = "Elite stormtroopers specialising in aggression and infiltration.",
            specialRules = listOf(
                FactionRule("rule_prussia_fireteams", "Expert Fireteams", "May include up to 3 Fireteams instead of 2."),
                FactionRule("rule_prussia_masters_grenade", "Masters of the Grenade", "Add 4\" to the Range of all Grenades."),
                FactionRule("rule_prussia_forward", "Forward Positions", "Up to 2 Shock Troopers gain INFILTRATOR for +10 👑 each."),
                FactionRule("rule_prussia_rapid", "Rapid Assault", "Lieutenant and Shock Troopers may take Rapid Assault for +5 👑 each."),
                FactionRule("rule_prussia_equipment", "Specialised Equipment", "SMGs have Limit 4; Automatic Shotguns and Pistols lose ELITE-only; Machine Guns Limit 1.")
            )
        ),
        FactionVariant(
            id = "antioch_abyssinia",
            name = "Expeditionary Forces of Abyssinia",
            description = "Warriors of the Solomonic Throne, masters of mobile warfare.",
            specialRules = listOf(
                FactionRule("rule_abyssinia_healers", "Abyssinian Healers", "May include 0-2 Combat Medics; Misericordia have Limit 2."),
                FactionRule("rule_abyssinia_chewa", "Chewa", "Shock Troopers and ELITE models may take Chewa for +5 👑 each (+1 DICE in melee per nearby ally, max +2)."),
                FactionRule("rule_abyssinia_holy", "Holy Warriors", "May include 0-1 Trench Cleric and 0-2 Holy Warriors with Blessed Psalm and Arise and be Healed! actions."),
                FactionRule("rule_abyssinia_marksmanship", "Short-Range Marksmanship", "+1 DICE to Short Range attacks by the Lieutenant and Yeomen."),
                FactionRule("rule_abyssinia_vanguard", "Vanguard Forces", "Cannot include Trench Moles; up to 4 Yeomen may gain Flanking for +5 👑 each.")
            )
        ),
        FactionVariant(
            id = "antioch_red_brigade",
            name = "The Red Brigade",
            description = "Elite shock brigade of New Antioch defined by iron discipline and red attire.",
            specialRules = listOf(
                FactionRule("rule_red_no_retreat", "No Retreat", "Models in a Red Brigade Warband cannot take a Retreat ACTION."),
                FactionRule("rule_red_wear_tear", "Wear and Tear", "Starts each game with 1 BLOOD MARKER per 200 ducats of total Cost.")
            )
        )
    )

    // ------------------------------------------------------------------ Trench Pilgrims
    val trenchPilgrims: List<FactionVariant> = listOf(
        FactionVariant(
            id = "pilgrim_sacred_affliction",
            name = "Procession of the Sacred Affliction",
            description = "A melee-focused procession that rejects ranged warfare and doubt alike.",
            specialRules = listOf(
                FactionRule("rule_affliction_face_fears", "Face thy Fears", "Models cannot have Iron Capirotes."),
                FactionRule("rule_affliction_hammer_anvil", "Hammer and the Anvil", "Anti-Tank Hammers lose the (ELITE only) restriction."),
                FactionRule("rule_affliction_punishing", "Punishing Millstones", "+1 INJURY DICE for Melee Attacks against Down targets (except Ecclesiastic Prisoners)."),
                FactionRule("rule_affliction_reliquary", "Reliquary Armoury", "Holy Icon Shields cost 20 👑 and lose the (ELITE only) restriction."),
                FactionRule("rule_affliction_wrath_god", "Wrath of God", "Up to 1 Castigator/Pilgrim/Martyr can take Wrath of God for 15 👑.")
            )
        ),
        FactionVariant(
            id = "pilgrim_methodius",
            name = "War Pilgrimage of Saint Methodius",
            description = "A disciplined pilgrimage requiring modest dress and fielding armoured war-shrines.",
            specialRules = listOf(
                FactionRule("rule_methodius_armoury", "Anchorite Armoury", "Anchorite Shrines gain Ranged +1 and access to the variant armoury."),
                FactionRule("rule_methodius_anchorite", "Anchorite", "May include up to 2 Anchorite Shrines."),
                FactionRule("rule_methodius_chaste", "Chaste", "All Stigmatic Nuns must have Standard Armour; no more than 3 may be fielded.")
            )
        ),
        FactionVariant(
            id = "pilgrim_tenth_plague",
            name = "Cavalcade of the Tenth Plague",
            description = "A wrathful cavalcade promising the Day of His Wrath to the unfaithful.",
            specialRules = listOf(
                FactionRule("rule_plague_blood_lamb", "Blood of the Lamb", "Castigators gain the TOUGH Keyword for free."),
                FactionRule("rule_plague_day_wrath", "Day of His Wrath", "The War Prophet replaces Laying on Hands with the Day of his Wrath ACTION."),
                FactionRule("rule_plague_favour", "Favour of the Lord", "At the start of each Turn, place 1 BLESSING MARKER next to a model."),
                FactionRule("rule_plague_heaven", "Heaven Awaits", "Trench Pilgrims cannot be resurrected as Martyr-Penitents."),
                FactionRule("rule_plague_unclean", "The Unclean", "The Warband can have at most 0-2 Ecclesiastic Prisoners.")
            )
        )
    )

    // ------------------------------------------------------------------ Iron Sultanate
    val ironSultanate: List<FactionVariant> = listOf(
        FactionVariant(
            id = "sultanate_fidai",
            name = "Fida'i of Alamut – The Cabal of Assassins",
            description = "The shadow order of Alamut, devoted to the art of assassination.",
            specialRules = listOf(
                FactionRule("rule_fidai_alamut_alone", "Alamut Alone", "Cannot include a Yüzbaşı, Jabirean Alchemist, Janissaries, Lions of Jabir or Brazen Bulls."),
                FactionRule("rule_fidai_art", "Art of Assassination", "Each Assassin may take one unique ability (Hallucinogen Disguise 20 👑, Mirage of Time 15 👑, ...).")
            )
        ),
        FactionVariant(
            id = "sultanate_house_of_wisdom",
            name = "The House of Wisdom",
            description = "Scholar-alchemists who wield science and Takwin as weapons of war.",
            specialRules = listOf(
                FactionRule("rule_wisdom_alchemists", "Alchemists", "The Warband must include 1-2 Jabirean Alchemists; Alchemist Armour has Limit 2."),
                FactionRule("rule_wisdom_kavasses", "Kavasses", "Up to 3 Azebs may gain Melee +1 DICE for +5 👑 each."),
                FactionRule("rule_wisdom_secrets", "Secrets", "Each Alchemist may take one unique ability (Medicine 15 👑, Cartography 20 👑, Takwin 20 👑).")
            )
        ),
        FactionVariant(
            id = "sultanate_iron_wall",
            name = "Defenders of the Iron Wall",
            description = "The Sultan's garrison, masters of elevated marksmanship and siege engines.",
            specialRules = listOf(
                FactionRule("rule_wall_far_gate", "Far from the Sublime Gate", "Cannot include Lions of Jabir, Yüzbaşı or Assassins, nor Cloak of Alamut or Wind Amulet."),
                FactionRule("rule_wall_cannons", "Grand Cannons", "May include 0-2 Sultanate Grand Cannons at 60 👑 each."),
                FactionRule("rule_wall_marksmanship", "Marksmanship", "+2 DICE instead of +1 when attacking from an Elevated Position."),
                FactionRule("rule_wall_silahdar", "Silahdar", "The Warband must include 1 Silahdar (Yüzbaşı profile; replaces Mubarizun with STRONG)."),
                FactionRule("rule_wall_janissary", "Janissary Officers", "May have 0-2 Janissaries that gain the ELITE Keyword for free.")
            )
        )
    )

    // ------------------------------------------------------------------ Heretic Legions
    val hereticLegions: List<FactionVariant> = listOf(
        FactionVariant(
            id = "heretic_trench_ghosts",
            name = "Trench Ghosts",
            description = "Semi-corporeal echoes of the damned that haunt No Man's Land.",
            specialRules = listOf(
                FactionRule("rule_ghosts_banshee", "Barbed Wire Banshee", "May replace a Chorister with a Barbed Wire Banshee."),
                FactionRule("rule_ghosts_enemies", "Enemies of All", "Cannot include Mercenaries."),
                FactionRule("rule_ghosts_lost_souls", "Lost Souls", "Cannot include ARTIFICIAL models; no Hellbound Soul Contracts or Infernal Brands."),
                FactionRule("rule_ghosts_semi_corporeal", "Semi-corporeal", "-1 INJURY DICE from Ranged Attacks; ignores Difficult terrain; gains FEAR and NEGATE GAS.")
            ),
            armoryId = "heretic_trench_ghosts"
        ),
        FactionVariant(
            id = "heretic_knights_of_avarice",
            name = "Knights of Avarice",
            description = "Opulent servants of Mammon who trade in blood and gold alike.",
            specialRules = listOf(
                FactionRule("rule_avarice_merchants", "Corrupt Merchants", "Buy 1 piece of Battlekit from the New Antioch Armoury and 1 from the Iron Sultanate Armoury at creation."),
                FactionRule("rule_avarice_hammers", "Coin Hammers", "Favour hammers and clubs that break bone but leave flesh intact."),
                FactionRule("rule_avarice_scorn_fire", "Scorn of Fire", "Cannot use fire or explosive weapons that could destroy objects of value."),
                FactionRule("rule_avarice_gas", "Poison Gas", "Relies on poison gas and highly accurate rifles.")
            ),
            armoryId = "heretic_knights_of_avarice"
        ),
        FactionVariant(
            id = "heretic_naval_raiders",
            name = "Heretic Naval Raiders",
            description = "Corsairs and naval raiders devoted to soul-harvesting and maritime plunder.",
            specialRules = listOf(
                FactionRule("rule_naval_captain", "Heretic Captain", "The Warband must include 1 Heretic Captain."),
                FactionRule("rule_naval_fast", "Fast as Lightning", "Add +1 DICE to the Risky Success Roll for models taking a Dash ACTION."),
                FactionRule("rule_naval_unseen", "Unseen Advance", "Up to 3 non-ELITE models can gain INFILTRATOR for +10 👑 each."),
                FactionRule("rule_naval_mercenaries", "Mercenaries", "Can use any Fallen Mercenaries available to HERETIC Warbands.")
            ),
            units = NavalRaidersRoster.units(),
            armoryId = "heretic_naval_raiders"
        )
    )

    // ------------------------------------------------------------------ Cult of the Black Grail
    val blackGrail: List<FactionVariant> = listOf(
        FactionVariant(
            id = "black_grail_dirge",
            name = "Dirge of the Great Hegemon",
            description = "A profane funeral procession of the corruption-ridden echoes of a fallen Hegemon.",
            specialRules = listOf(
                FactionRule("rule_dirge_executor", "The Executor", "The Warband must include 1 Executor (uses the Plague Knight entry with Ranged +1, TOUGH, 80 👑)."),
                FactionRule("rule_dirge_fallen", "The Fallen", "Cannot include a Lord of Tumours or an Amalgam."),
                FactionRule("rule_dirge_lost", "The Lost", "May include 0-2 Hounds of the Black Grail and 0-2 Heralds of Beelzebub."),
                FactionRule("rule_dirge_will", "Hegemon's Will", "An Executor or Plague Knight may take a Command Bereaved ACTION to direct nearby Bereaved.")
            ),
            units = BlackGrailRoster.dirge(),
            armoryId = "black_grail_dirge"
        ),
        FactionVariant(
            id = "black_grail_great_hunger",
            name = "The Great Hunger",
            description = "A ravenous Black Grail offshoot that devours all in its path.",
            specialRules = listOf(
                FactionRule("rule_hunger_devour", "Devouring Swarm", "The Great Hunger prioritises the consumption of the living."),
                FactionRule("rule_hunger_infection", "Rampant Infection", "Spreads INFECTION MARKERS with even greater speed.")
            ),
            units = GreatHungerRoster.units(),
            armoryId = "black_grail_great_hunger"
        )
    )

    // ------------------------------------------------------------------ The Court
    val court: List<FactionVariant> = listOf(
        FactionVariant(
            id = "court_pride",
            name = "High Cathedra of Pride",
            description = "The prideful court of Prudence, Lucifer's resplendent and imperious knights.",
            specialRules = listOf(
                FactionRule("rule_pride_burning_shame", "Burning Shame", "An enemy can place a BLESSING MARKER if a Pride model retreats."),
                FactionRule("rule_pride_non_pavebo", "Non Pavebo", "Lost Mortal Suffering; ELITE models in Open terrain gain 2 SIN MARKERS when they Activate."),
                FactionRule("rule_pride_proud_standard", "Proud Standard", "While a Troop Flag is held, lower the Critical Success threshold by 1.")
            )
        ),
        FactionVariant(
            id = "court_wrath",
            name = "Iron Curule of Wrath",
            description = "The raging court of Apollyon, burning all that the Tyrant-God has made.",
            specialRules = listOf(
                FactionRule("rule_wrath_exterminans", "Exterminans Omnium", "Lost Mortal Suffering; gain SIN MARKERS when FIRE attacks wound enemies."),
                FactionRule("rule_wrath_hellfire_spit", "Hellfire Spit", "Pit Locusts with 2+ SIN MARKERS can make a 4\" FIRE Ranged Attack.")
            )
        ),
        FactionVariant(
            id = "court_envy",
            name = "Black Klismos of Envy",
            description = "The spiteful court of Leviathan, consuming the achievements of its betters.",
            specialRules = listOf(
                FactionRule("rule_envy_petty_noble", "Petty Noble", "Each Turn a SIN MARKER is placed on the Hell Knight with the fewest SIN MARKERS."),
                FactionRule("rule_envy_envious_eyes", "Envious Eyes", "Add 2 pieces of Battlekit from New Antioch, Pilgrims, Sultanate or Heretic Armouries to yours."),
                FactionRule("rule_envy_potestas_negata", "Potestas Negata", "Lost Mortal Suffering; DEMONIC allies gain SIN MARKERS when enemies are taken Down.")
            )
        ),
        FactionVariant(
            id = "court_lust",
            name = "Flesh Divan of Lust",
            description = "The hedonistic court of Asmodeus, where pain and pleasure are one and the same.",
            specialRules = listOf(
                FactionRule("rule_lust_vetiti_voluptas", "Vetiti Voluptas", "Lost Mortal Suffering; SIN MARKERS can fuel Goetic Spells on any model."),
                FactionRule("rule_lust_no_armour", "Lustful Yoke Fiends", "Yoke Fiends without Restraining Muzzles cannot wear Armour and weaken nearby enemies.")
            )
        ),
        FactionVariant(
            id = "court_greed",
            name = "Golden Seat of Greed",
            description = "The avaritian court of Mammon, where wealth is hoarded and everything has a price.",
            specialRules = listOf(
                FactionRule("rule_greed_avaritian_audit", "Avaritian Audit", "Lost Mortal Suffering; gain SIN MARKERS via the Weregild Threshold each Turn."),
                FactionRule("rule_greed_mammon", "Mammonite Trade Network", "Sell 1 piece of Battlekit for full price each Quartermaster Step."),
                FactionRule("rule_greed_50ducats", "Conspicuous Wealth", "Elites and Yoke Fiends must have at least 50 👑 of Battlekit.")
            )
        ),
        FactionVariant(
            id = "court_gluttony",
            name = "Hungering Throne of Gluttony",
            description = "The voracious court of Beelzebub, whose hunger is never sated.",
            specialRules = listOf(
                FactionRule("rule_gluttony_conspicuous", "Conspicuous Consumption", "The most expensive model starts the first Turn with 2 SIN MARKERS."),
                FactionRule("rule_gluttony_omnia_devoranda", "Omnia Devoranda", "Lost Mortal Suffering; gain SIN MARKERS when a DEMONIC model takes foes Down."),
                FactionRule("rule_gluttony_overindulgence", "Overindulgence", "Spend the maximum possible amount of BLOOD MARKERS.")
            )
        ),
        FactionVariant(
            id = "court_sloth",
            name = "Silk Bed of Sloth",
            description = "The slothful court of Belphegor, where comfort is the highest virtue.",
            specialRules = listOf(
                FactionRule("rule_sloth_taedium", "Taedium Vitae", "Lost Mortal Suffering; gain SIN MARKERS by refusing to Activate."),
                FactionRule("rule_sloth_morphean", "Morphean Mind", "May give up Initiative to choose a foe that cannot be Activated first."),
                FactionRule("rule_sloth_witch_knight", "Witch Knight", "Hell Knights have Ranged +1 DICE and an extra Goetic Power.")
            )
        )
    )
}
