# Informe — Armerías de variantes de banda (sesión 2026-10-12)

Resumen de lo añadido/modificado en esta sesión de trabajo, centrada en completar las
**armerías (Armoury Tables) de las variantes de banda** que aún reutilizaban la armería de
su facción base.

---

## 1. Objetivo de la sesión

Cerrar el pendiente recurrente de "armerías de variante": las 18 subfacciones de
**The Court (7 pecados), New Antioch, Trench Pilgrims e Iron Sultanate** no tenían tabla de
equipo propia. Tras esta sesión, cada variante resuelve su propia armería vía `armoryId`.

---

## 2. Archivos modificados

| Archivo | Cambio |
|---|---|
| `app/src/main/java/com/example/trenchcrusadetracker/domain/equipment/providers/FactionBattlekit.kt` | +perfiles únicos de Battlekit de las variantes |
| `app/src/main/java/com/example/trenchcrusadetracker/domain/equipment/providers/FactionArmories.kt` | refactor a listas base + 18 armerías de variante |
| `app/src/main/java/com/example/trenchcrusadetracker/domain/faction/providers/WarbandVariants.kt` | `armoryId` cableado en 18 variantes |
| `app/src/test/java/com/example/trenchcrusadetracker/domain/EquipmentCatalogTest.kt` | cobertura de las nuevas armerías |
| `MEMORY.md` | entrada de changelog `2026-10-12 (5)` |

---

## 3. Perfiles únicos de Battlekit añadidos (`FactionBattlekit.kt`)

Se añadieron los perfiles (sin duplicar los del catálogo estándar) para:

- **Papal States (New Antioch):** `na_papal_crucifix` (Supreme Pontiff's Crucifix).
- **Kingdom of Alba (New Antioch):** `na_alba_lochaber_axe` (Lochaber Axe).
- **Stosstruppen (New Antioch):** `na_prussia_tank_splitter_sword` (Tank-Splitter Sword), `na_prussia_holy_smoke` (Holy Smoke).
- **Abyssinia (New Antioch):** `na_abyssinia_shotel`, `na_abyssinia_holy_water`, `na_abyssinia_anfarro`, `na_abyssinia_tabot`.
- **Red Brigade (New Antioch):** `na_red_blood_cloak`, `na_red_banner`, `na_red_atonement_bell`.
- **Sacred Affliction (Trench Pilgrims):** `tp_affliction_bells_of_warding`, `tp_affliction_blessed_millstone`, `tp_affliction_great_flail`, `tp_affliction_holy_icon_armour`, `tp_affliction_penitents_phylactery`, `tp_affliction_ragged_vestments`.
- **Saint Methodius (Trench Pilgrims):** `tp_methodius_autocannon`, `tp_methodius_gas_censer`, `tp_methodius_trench_mortar`, `tp_methodius_gas_filters`, `tp_methodius_grand_anchorite`, `tp_methodius_hallowed_anchorite`, `tp_methodius_holy_diesel_engine`, `tp_methodius_piston_legs`, `tp_methodius_sacred_geometry`, `tp_methodius_wrathful_cherub_face`.
- **Tenth Plague (Trench Pilgrims):** `tp_plague_sacrificial_lamb`.
- **Fida'i of Alamut (Iron Sultanate):** `is_fidai_bow_of_alamut`, `is_fidai_golden_khanjar`, `is_fidai_hashashin_leaf`.
- **House of Wisdom (Iron Sultanate):** `is_wisdom_elixir_of_al_khidr`, `is_wisdom_fire_shield`.
- **The Court (7 pecados):** 5 ítems por pecado —
    - **Pride:** `court_pride_lightbringer_rifle`, `court_pride_cloak_of_the_morningstar`, `court_pride_glorious_light`, `court_pride_petals_of_blood_roses`, `court_pride_laurels_of_the_supreme`.
    - **Greed:** `court_greed_ring_gun`, `court_greed_herodic_hammer`, `court_greed_queens_bones`, `court_greed_stolen_sentimentality`, `court_greed_esaus_bargain`.
    - **Lust:** `court_lust_altar_of_torment`, `court_lust_astragaloi`, `court_lust_horn_of_luxuria`, `court_lust_lance_of_asmoday`, `court_lust_naamahrite_scourge`, `court_lust_spoils_of_lust`.
    - **Envy:** `court_envy_brand_of_moloch`, `court_envy_craven_vassalage`, `court_envy_lying_knife`, `court_envy_pyrite_mirror_pavise`, `court_envy_usurpers_saw_wire`.
    - **Gluttony:** `court_gluttony_jawed_bascinet`, `court_gluttony_lanx_ciborum`, `court_gluttony_meaty_cleaver`, `court_gluttony_ostentatious_ornamentation`, `court_gluttony_tawdry_finery`.
    - **Wrath:** `court_wrath_apollyons_ember`, `court_wrath_lesser_mark_of_cain`, `court_wrath_locust_gait`, `court_wrath_ruinous_pernach`, `court_wrath_spear_of_apollyon`.
    - **Sloth:** `court_sloth_desidian_matchlock`, `court_sloth_flower_of_lytos`, `court_sloth_iscariots_rope`, `court_sloth_shroud_of_balak`.

---

## 4. Refactor de `FactionArmories.kt`

- Las 6 tablas base se extrajeron a `private val`: `newAntiochBase`, `trenchPilgrimsBase`,
  `ironSultanateBase`, `hereticLegionBase`, `blackGrailBase`, `courtBase`. **Contenido sin cambios**
  (verificado contra el `HEAD`; la única diferencia es la clave `heretic_legions` → `heretic_legion`,
  corregida antes).
- Nuevos helpers para componer variantes por diferencia:
    - `List<ArmoryEntry>.update(id, amount?, currency, stip)` — cambia coste y/o estipulaciones.
    - `List<ArmoryEntry>.removing(vararg ids)` — elimina entradas de la base.
- Las variantes ya existentes (Great Hunger, Dirge, Trench Ghosts, Knights of Avarice, Naval Raiders)
  se extrajeron a `private val` equivalentes; su contenido no cambió.

### Armerías de variante nuevas (18)

| `armoryId` | Composición |
|---|---|
| `antioch_papal_states` | base NA + `na_papal_crucifix` (Glory, Limit 1) |
| `antioch_eire_rangers` | base NA + Anointed Ammunition (`e_armour_piercing_bullets`, Limit 2); Satchel Charge Limit 4 |
| `antioch_alba` | base NA con re-costes (Great Sword 7, etc.), límites reducidos y restricciones de armadura; + Dum-Dum Bullets, Lochaber Axe |
| `antioch_prussia` | base NA − Grenade Launcher − Martyrdom Pills; SMG Limit 4; restricciones de armadura; + Tank-Splitter Sword, Holy Smoke |
| `antioch_abyssinia` | base NA + Misericordia Limit 2; + Shotel, Holy Water, Anfarro, Tabot |
| `antioch_red_brigade` | base NA − Troop Flag; + Molotov, Blood Cloak, Red Banner, Atonement Bell |
| `pilgrim_sacred_affliction` | base TP − Machine Gun − Iron Capirote; Punt Gun Limit 1; Holy Icon Shield a 20👑 sin ELITE-only; + 6 ítems de Affliction |
| `pilgrim_methodius` | base TP con re-costes (MG 50👑, SMG 30👑 Limit 1); + armamento y módulos del Anchorite Shrine |
| `pilgrim_tenth_plague` | base TP + `tp_plague_sacrificial_lamb` |
| `sultanate_fidai` | base IS + Bow of Alamut, Golden Khanjar, Hashashin Leaf |
| `sultanate_house_of_wisdom` | base IS con Alchemist Armour Limit 2; + Elixir of Al-Khidr, Fire Shield |
| `court_pride` | base Court + Troop Flag a 10👑 (ELITE/Desecrated Saint); + 5 ítems de Pride |
| `court_greed` | base Court con Reinforced Armour sin ELITE-only; + 5 ítems de Greed |
| `court_lust` | base Court − Flamethrowers − Malebranche Sword; Iron Maiden/Torture Instrument liberados; + 6 ítems de Lust |
| `court_envy` | base Court con Iron Maiden Flail abierta; + 5 ítems de Envy |
| `court_gluttony` | base Court − CONSUMABLE; + 5 ítems de Gluttony |
| `court_wrath` | base Court − Gatling − Serpent Assault; Heavy Flamethrower Limit 3; Hellblade sin límite; + 5 ítems de Wrath |
| `court_sloth` | base Court con Ophidian Rifle sin ELITE/límite; + Flail/Scourge y 5 ítems de Sloth |

`court_pride`, `court_greed`, `court_lust`, `court_envy`, `court_gluttony`, `court_wrath`,
`court_sloth` son las 7 variantes de pecado del Court.

---

## 5. Wiring en `WarbandVariants.kt`

Se añadió `armoryId` a las 18 variantes:

- New Antioch: `antioch_papal_states`, `antioch_eire_rangers`, `antioch_alba`, `antioch_prussia`, `antioch_abyssinia`, `antioch_red_brigade`.
- Trench Pilgrims: `pilgrim_sacred_affliction`, `pilgrim_methodius`, `pilgrim_tenth_plague`.
- Iron Sultanate: `sultanate_fidai`, `sultanate_house_of_wisdom` (**`sultanate_iron_wall` queda sin `armoryId`**, usa la base `iron_sultanate`).
- The Court: `court_pride`, `court_greed`, `court_lust`, `court_envy`, `court_gluttony`, `court_wrath`, `court_sloth`.

---

## 6. Tests (`EquipmentCatalogTest.kt`)

La lista `variantArmories` pasa a 23 claves (las 5 previas + las 18 nuevas). Los tests de
existencia y de resolución al catálogo maestro ahora las recorren todas.

---

## 7. Verificación

- `.\gradlew.bat testDebugUnitTest` → **BUILD SUCCESSFUL** (suite completa).
- `.\gradlew.bat assembleDebug` → **BUILD SUCCESSFUL**.
- Nota de entorno: `JAVA_HOME` = `C:\Program Files\Android\Android Studio\jbr`.

---

## 8. Decisiones y limitaciones

- Se mantiene composición por diferencias (`base + update + removing`) en lugar de duplicar tablas
  completas: menor riesgo de divergencias y patrón Open/Closed.
- **Doble tarifa no modelable:** p. ej. Iron Maiden Flail (Envy) cuesta distinto para el Desecrated
  Saint; se aproxima a un único valor.

---

## 9. Pendiente

- Las nuevas armerías reutilizan el roster de su facción base (solo Great Hunger, Dirge y Naval
  Raiders tienen roster propio).
- Restricciones de battlekit por unidad siguen en texto, no como lógica ejecutable.
- Puntos 2 (restricciones de battlekit), 3 (validaciones de conteo) y 4 (lógica de marcadores) del
  plan general siguen pendientes.
- No se ha hecho commit; los cambios quedan en el árbol de trabajo.
