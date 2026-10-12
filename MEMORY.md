# MEMORY.md — Registro de Cambios

Registro de cambios significativos para poder revertir si es necesario (regla 4 de AGENTS.md).

> **Convención de informes:** todos los informes de sesión se guardan en la carpeta `informes/`
> (en la raíz del proyecto, por encima de las demás carpetas) con el nombre
> `INFORME_<AAAA-MM-DD>_<tema>.md`. `MEMORY.md` es el changelog resumido; `informes/` guarda el
> detalle extendido. (Documentado en `app/sources/spec.md`.)

## 2026-10-08 — Refactor de dominio según ficha "La guardia del Tzar" (Warband-Example-Construction.pdf)

Contexto: se leyó el PDF exportado por Trench Companion y se reestructuró el modelo de dominio para reflejar cómo se construye una Warband y un personaje en Trench Crusade.

### Cambios realizados
- `domain/model/warband/WarbandMember.kt`
    - Nuevo `enum MovementType` (INFANTRY/FLYING/MOUNTED/WALKER) y `ModelStats.movementType` con helper `movementDisplay()` → "6\"/Infantry".
    - Nuevo campo `baseCostInGloryPoints` en todos los miembros (mercenarios como Mamluk Faris cuestan solo Gloria: 0 Ducados / 4 GP).
    - Nuevos campos `keywords`, `specialRules` (✧), `skills` (△) y `flaws` (▽) en el perfil del modelo.
    - `calculateTotalCostInGloryPoints()` ahora incluye la gloria base del personaje (antes solo sumaba el battlekit).
    - Nueva subclase `MercenaryMember` (rol MERCENARY, puede costar 0 ducados + gloria).
    - `promoteToElite()` conserva keywords/specialRules y suma la habilidad a `skills`.
- `domain/model/warband/Warband.kt`
    - Nueva `data class Fireteam` (par de modelos, regla de New Antioch: máx. 2).
    - Nuevos campos `fireteams`, `maxFireteamsAllowed` y helpers `fireteamCount()`, `canAddFireteam()`, `isInFireteam()`.
    - Nuevos helpers `memberCountFor(role)`, `roleCounts()` y `warbandRating()`.
- `domain/model/campaign/Campaign.kt` y `Player.kt`: campos de campaña del PDF — `totalRounds`, `notes`, `explorationLocations`, `failedPromotions` y `Player.victoryPoints`.
- Pruebas: nuevo `WarbandStructureTest.kt` (5 tests). Suite total: 15 tests en verde.

### API preservada (UI no tocada)
`baseCostInDucats`, `calculateTotalCostInDucats/GloryPoints`, `baseStats`, `battlekit`, `role`, `customName`, `specialSkills`, `commandAbilities` y los constructores existentes siguen compilando (nuevos params con default).

### Verificación
- `.\gradlew.bat testDebugUnitTest` → BUILD SUCCESSFUL (15/15).
- `.\gradlew.bat assembleDebug` → BUILD SUCCESSFUL.

## 2026-10-08 (2) — Relleno de catálogo real en FactionProviders (libros de banda)

Contexto: el usuario pidió eliminar `courage` de los providers y completar las unidades faltantes de cada warband según el rulebook/libros de banda.

### Cambios realizados
- Los 9 providers de `domain/faction/providers/*` fueron reescritos:
    - Se quitó el parámetro `courage = N` (ya no es necesario; `ModelStats.courage` queda con default 0 y sin uso por providers).
    - Se añadieron los rosters reales con costos en ducados, stats (movimiento, melee/ranged en dados, armor como Injury Modifier), keywords y habilidades, verificados contra los PDFs de `app/sources/` (extraídos a `C:\Users\kmilo\AppData\Local\Temp\*.txt`):
        - `NewAntioch`: Lieutenant (70), Sniper Priest (50), Trench Cleric (60), Yeoman (30), Shock Trooper (45), Combat Engineer (80), Mechanized Heavy Infantry (85), Combat Medic (65) = 8 unidades.
        - `TrenchPilgrims`: War Prophet (80), Communicant (100), Castigator (50), Trench Pilgrim (30), Stigmatic Nun (50), Ecclesiastic Prisoner (20), Anchorite Shrine (140) = 7 unidades.
        - `IronSultanate`: Yüzbaşı (70), Jabirean Alchemist (55), Sultanate Assassin (85), Azeb (25), Sultanate Sapper (50), Lion of Jabir (60), Janissary (55), Brazen Bull (100) = 8 unidades.
        - `HereticLegion`: Heretic Priest (80), Death Commando (90), Chorister (65), Heretic Trooper (30), War Wolf Assault Beast (145), Wretched (25), Anointed Heavy Infantry (95), Artillery Witch (100) = 8 unidades.
        - `PrussianStosstruppen`: Hauptmann (70), Sniper Priest (50), Feldkaplan (60), Yeoman (30), Stosstrupp (45), Combat Engineer (80), Mechanized Heavy Infantry (85), Combat Medic (65) = 8 unidades.
        - `SacredAffliction`: Lazarist Prophet (90), Lazarist Communicant (100), Lazarist Castigator (50), Leper-Pilgrim (30), Stigmatic Nun (60), Ecclesiastic Prisoner (20), Anchorite Shrine (140) = 7 unidades.
        - `HereticNavalRaiders`: Heretic Captain (80), Abyssal Commando (90), Drowned Chorister (65), Heretic Raider (30), Wretched (25), Anointed Heretic Raider (95), Sea Hag (100) = 7 unidades.
        - `RedBrigade`: roster de New Antioch + Crimson Communicant (75) + Trench Dog of the Red Brigade (35) = 8 unidades.
        - `GreatHunger`: Matagot Hag (135, LEADER), Ravenous (35, ELITE), Gregori Gula (140, FLYING 9") = 3 unidades; `defaultStartingDucats = 700` (campañas de Black Grail).
- Nota de restaurar: `WarbandStructureTest.kt` y `FactionRepositoryTest.kt` no se modificaron; siguen verdes con los datos reales (New Antioch = 8 unidades ya esperadas).

### Verificación
- `.\gradlew.bat testDebugUnitTest` → BUILD SUCCESSFUL (15/15, `compileDebugKotlin` real, sin cache).
- `.\gradlew.bat assembleDebug` → BUILD SUCCESSFUL.

### Pendiente (no inventado, requiere libros de banda)
- Catálogos de equipo por facción (armas/objetos de `app/sources/`) aún no están completos; el arsenal maestro en `EquipmentRepositoryImpl.kt` sigue como está.

## 2026-10-08 (3) — "The Court of the Seven-Headed Serpent" desde October-Rules-Review-2026.pdf

Contexto: salió la actualización de reglas `October-Rules-Review-2026.pdf` (36 pág., libro completo de The Court, 36 pág.) con la mecánica de SIN MARKERS (antes eran BLOOD MARKERS en el libro maestro). El usuario pidió crear las facciones faltantes del Court y actualizar el resto.

### Cambios realizados
- Nuevo `TheCourtRoster.kt` (en `domain/faction/providers/`): unidades base del Court compartidas vía `internal object` con funciones `praetor()`, `sorcerer()`, `hellKnight()`, `hunter()`, `wretched()`, `pitLocust()`, `yokeFiend()`, `desecratedSaint()`. Stats/costos verificados contra el PDF de Octubre y el libro maestro (páginas 145-167).
    - Praetor 115👑 (8"/Flying, +3/+3, ARM 0) — Líder; Sorcerer 85👑 (6"/Flying, +1/+1); Hell Knight 0-3 = 100👑 (6"/Inf, +0/+2, ARM -2); Hunter 0-1 = 110👑 (6"/Inf, +2/+1); Wretched 20👑 (6"/Inf, -1/-1); Pit Locust 0-3 = 90👑 (8"/Flying, +0/+2, ARM -2); Yoke Fiend 35👑 (6"/Inf, +0/+1); Desecrated Saint 0-1 = 140👑 (6"/Inf, +0/+2, ARM -3).
- 8 nuevos providers (patrón OCP, registrados en `FactionRepositoryImpl.providers`):
    - `TheCourtFactionProvider` (id `the_court`, "The Court of the Seven-Headed Serpent").
    - Variantes de Pecado Capital (700👑): `HighCathedraOfPride` (`court_pride`), `GoldenSeatOfGreed` (`court_greed`), `FleshDivanOfLust` (`court_lust`, Desecrated Saint 8"/Flying ARM -2), `BlackKlismosOfEnvy` (`court_envy`), `HungeringThroneOfGluttony` (`court_gluttony`), `IronCuruleOfWrath` (`court_wrath`, Pit Locust FIRE), `SilkBedOfSloth` (`court_sloth`, Hell Knight Witch Knight Ranged +1).
    - Cada variante usa `.copy()` del roster base con keywords/reglas propias (Prideful, Greedy, Lustful, Envious, Gluttonous, Wrathful, Slothful, + abilities de sustitución del Desecrated Saint).
- `FactionRepositoryImpl.kt`: 9 → 17 providers registrados (imports añadidos).
- `FactionRepositoryTest.kt`: espera 17 facciones (antes 9).
- `defaultStartingDucats = 700` para todas las facciones del Court (confirmado en el libro maestro: "You have 700 👑 to recruit a Court of the Seven-Headed Serpent Warband").

### Verificación
- `.\gradlew.bat testDebugUnitTest assembleDebug` → BUILD SUCCESSFUL (compileDebugKotlin real).
- Suite de tests sigue en verde (FactionRepositoryTest actualizado; el resto no dependía de datos de providers).

### Pendiente / notas
- El PDF de Octubre NO modifica stats de las 9 facciones anteriores (solo es The Court); "actualizar las demás warbands" = registrarlas en el repositorio, ya hecho.
- Black Grail base (Cult of the Black Grail, libro maestro pág. 126-145) sigue sin provider; Great Hunger ya existe como su variante. Pendiente si el usuario lo pide.
- Mercenarios del Court (Goetic Warlock, Sin Eater, Perilous Sword Saint) no están en el PDF de Octubre (se referencian como "Any Mercenary"); no se inventaron stats.

## 2026-10-08 (4) — Selector de facción en la UI (elimina el hardcode de New Antioch)

Contexto: `WarbandCreationScreen` tenía hardcodeado `NewAntiochFactionProvider`/`NewAntiochArmoryProvider`, por lo que las 17 facciones registradas no eran seleccionables.

### Cambios realizados
- `ui/screens/warband/WarbandCreationScreen.kt`:
    - Se usa `FactionRepositoryImpl()` para listar facciones (`getAllFactions()`) y resolver el provider activo (`getProviderByFactionId`).
    - Nuevo estado `selectedFactionId` + arsenal resuelto con `remember(selectedFactionId)`.
    - Nuevo `FactionSelectorDialog`: lista todas las facciones (descripción y ducados iniciales) y resalta la activa. Se abre desde la tarjeta de encabezado (ahora clicable, icono `ArrowDropDown`).
    - Al cambiar de facción se limpian los miembros y se reinicia la pestaña (unidades específicas por facción).
    - Si ya hay unidades reclutadas, se muestra `ConfirmFactionChangeDialog` (AlertDialog) advirtiendo que se borrarán antes de aplicar el cambio.
- Nuevo `domain/equipment/providers/DefaultFactionArmoryProvider.kt`: arsenal genérico (fallback) para facciones sin armería propia, expone el arsenal maestro con las restricciones por rol (Troopers sin objetos de Gloria). `new_antioch` mantiene `NewAntiochArmoryProvider`.

### Verificación
- `.\gradlew.bat testDebugUnitTest` → BUILD SUCCESSFUL.
- `.\gradlew.bat assembleDebug` → BUILD SUCCESSFUL.

### Pendiente
- Crear `*ArmoryProvider` específicos (restricciones reales del libro) para cada facción; por ahora todas menos New Antioch usan el arsenal maestro genérico.

## 2026-10-09 — Subfacciones (variantes de banda) y ducados iniciales 700

Contexto: el libro "Warbands of Trench Crusade" define subfacciones (variantes) por cada facción base. Decisión del usuario: convertir los providers sueltos que ya eran variantes en `Faction.variants` (reutilizando el roster base por ahora) y fijar todas las facciones base en 700 👑.

### Cambios realizados
- `domain/model/faction/Faction.kt`: nuevo `data class FactionVariant(id, name, description, specialRules, defaultStartingDucats=700, defaultStartingGloryPoints=0)`. `Faction` añade `defaultStartingGloryPoints` y `variants`. Default de `defaultStartingDucats` pasa de 500 a 700.
- Nuevo `domain/faction/providers/WarbandVariants.kt` (`internal object`): catálogo central con las variantes por facción (reglas verificadas contra el libro, páginas 34-141):
    - New Antioch (6): Papal States Intervention Force (500👑 + 11☼), Éire Rangers, Kingdom of Alba Assault Detachment, Stosstruppen of the Free State of Prussia, Expeditionary Forces of Abyssinia, The Red Brigade.
    - Trench Pilgrims (3): Procession of the Sacred Affliction, War Pilgrimage of Saint Methodius, Cavalcade of the Tenth Plague.
    - Iron Sultanate (3): Fida'i of Alamut, The House of Wisdom, Defenders of the Iron Wall.
    - Heretic Legions (3): Trench Ghosts, Knights of Avarice, Naval Raiding Party.
    - Black Grail (2): Dirge of the Great Hegemon, The Great Hunger.
    - The Court (7): los siete pecados capitales.
- Nuevo `domain/faction/providers/BlackGrailFactionProvider.kt` (`black_grail`, "Cult of the Black Grail", 700👑): roster real del libro maestro pp.126-139 (Lord of Tumours 130, Plague Knight 60, Corpse Guard 55, Grail/Fly Thrall 25, Hound 55, Herald of Beelzebub 50, Amalgam 140) y reglas base (INFECTION MARKERS, Morale).
- Providers base actualizados con `variants = WarbandVariants.*` y `defaultStartingDucats = 700`: NewAntioch, TrenchPilgrims, IronSultanate, HereticLegion, TheCourt.
- `FactionRepositoryImpl.kt`: de 17 providers a 6 **facciones base** (NewAntioch, TrenchPilgrims, IronSultanate, HereticLegion, BlackGrail, TheCourt).
- **Eliminados** los 12 providers sueltos de variantes (ya incluidos como `FactionVariant`): `SacredAffliction`, `PrussianStosstruppen`, `HereticNavalRaiders`, `RedBrigade`, `GreatHunger`, `HighCathedraOfPride`, `GoldenSeatOfGreed`, `FleshDivanOfLust`, `BlackKlismosOfEnvy`, `HungeringThroneOfGluttony`, `IronCuruleOfWrath`, `SilkBedOfSloth`. Se conserva `TheCourtRoster.kt` (usado por `TheCourtFactionProvider`).
- `ui/screens/warband/WarbandCreationScreen.kt`:
    - Nuevos estados `selectedVariantId`, `variantPickerFaction`, `pendingSelection` (y data class `FactionSelection`).
    - Flujo: tarjeta de facción → `FactionSelectorDialog` (6 base) → `VariantSelectorDialog` (base o variante, muestra descripción, ducados/gloria iniciales y reglas especiales) → si hay unidades, `ConfirmFactionChangeDialog` → aplica (limpia unidades).
    - Encabezado y barra de recursos usan los ducados/gloria de la variante activa (`startingDucats`/`startingGlory`).
- `FactionRepositoryTest.kt`: actualizado a 6 facciones base; nuevos tests para 700👑, variantes de New Antioch y Black Grail.

### Verificación
- `.\gradlew.bat assembleDebug` → BUILD SUCCESSFUL.
- `.\gradlew.bat testDebugUnitTest` → BUILD SUCCESSFUL.

### Pendiente / notas
- Las variantes reutilizan el roster de su facción base (por decisión del usuario); las restricciones de unidades por variante se refinarán después.
- La armería sigue resuelta por facción base (`new_antioch` → `NewAntiochArmoryProvider`; resto → `DefaultFactionArmoryProvider`). Pendiente subfacciones con armería propia (p. ej. Papal States, Red Brigade).

## 2026-10-09 (2) — Catálogo completo de Battlekit + Armerías reales por facción

Contexto: el usuario pidió agregar TODO el equipamiento equipable y generar la armería/restricciones por facción. Alcance acordado (vía preguntas): 6 facciones base + Battlekit estándar (variantes en 2ª iteración). Decisiones: modelar estipulaciones reales; provider por facción que filtra el catálogo maestro + ítems únicos (sin duplicar perfiles).

### Hallazgos clave
- Los costes son **por facción**: el Battlekit estándar del rulebook solo tiene perfiles (Type/Range/Keywords), no costes. Los costes viven en cada Armoury Table (Warbands of Trench Crusade).
- Las estipulaciones (`ELITE only`, `Limit: N`, `Limit: N excluding X`, `(X only)`, `Shield Combo`, `Consumable`, `Headgear`, `Bayonet Lug`) también varían por facción (p. ej. Reinforced Armour: NA "ELITE & Mechanized Heavy Infantry", IS "ELITE & Janissaries", HL "Anointed & ELITE").

### Cambios realizados
- `domain/model/equipment/Equipment.kt`:
    - Nuevo `data class EquipmentStipulations(consumable, headgear, shieldCombo, bayonetLug, limit, limitExcluding, restrictedTo)` con `eliteOnly`, `isEmpty` y `display()`.
    - Nuevo `abstract val stipulations` en la sealed class y en las 8 subclases (default `EquipmentStipulations.NONE` → API compatible con tests existentes).
    - Nuevo helper `Equipment.withArmory(cost, stipulations)` que aplica coste+estipulaciones devolviendo una copia tipada.
- `domain/model/warband/Battlekit.kt`: `canAddEquipment` ahora respeta `stipulations.shieldCombo` además del keyword legacy.
- Nuevo `domain/equipment/ArmoryEntry.kt`: entrada de armería (equipmentId + coste + stipulations).
- Nuevo `domain/equipment/providers/StandardBattlekit.kt`: catálogo del Battlekit ESTÁNDAR real (18 armas a distancia, 10 de melee, 5 granadas, escudo, 2 armaduras, 20 equipos) verificados contra el Digital Rulebook pp. 67-85. Los costes son de referencia.
- Nuevo `domain/equipment/providers/FactionBattlekit.kt`: Battlekit ÚNICO de las 6 facciones (NA: Engineer Body Armour/Heavy Ballistic Shield/Machine Armour; TP: Punt Gun/Holy Icon Shield/Iron Capirote/Warcross; IS: Alaybozan/Flame Cannon/Jezzail/M.U.R.A.D./Siege Jezzail/Assassin's Dagger/Halberd-Gun/Titan Zulfiqar/Alchemist Armour/Alchemical Ammunition/Cloak of Alamut/Marid Shovel/Takwin Anqā/Wind Amulet; HL: Blasphemous Staff/Hellblade/Sacrificial Blade/Tartarus Claws; BG: Beelzebub's Axe/Black Grail Shield/Compound Eyes Helmet/Corruption Belcher/Grail Devotee/Infested Rifle/Parasite Grenades/Plague Blade/Putrid Shotgun/Viscera Cannon; Court: Arquebus/Crown of Hellfire/Headtaker/Hellblade/Infernal Iron Armour/Malebranche Sword/Ophidian Rifle/Restraining Muzzle/Serpent Assault Gun/Torture Instrument).
- Nuevo `domain/equipment/providers/FactionArmories.kt`: las 6 Armoury Tables completas con coste (Ducados/Gloria) y estipulaciones exactas del libro.
- Nuevo `domain/equipment/providers/FactionArmoryProviderImpl.kt`: provider genérico (resuelve `ArmoryEntry` → perfil + `withArmory`) + `FactionArmoryRegistry.forFaction(...)` (fallback a `DefaultFactionArmoryProvider`). Troopers excluyen Gloria y `ELITE only`.
- `domain/equipment/EquipmentRepositoryImpl.kt`: reescrito; el maestro ahora es `StandardBattlekit.items + FactionBattlekit.items` (se eliminó el catálogo inventado anterior).
- `ui/screens/warband/WarbandCreationScreen.kt`: usa `FactionArmoryRegistry` (se eliminó el hardcode `if new_antioch`); `EquipmentOptionCard` muestra las estipulaciones.
- Nuevo test `EquipmentCatalogTest.kt` (7 tests): sin ids duplicados, armerías resuelven al maestro, costes/stipulaciones de New Antioch, restricción Trooper/ELITE, 6 facciones, fallback, perfiles estándar.

### Verificación
- `.\gradlew.bat assembleDebug testDebugUnitTest` → BUILD SUCCESSFUL.

### Pendiente / notas
- `NewAntiochArmoryProvider.kt` queda sin uso (declara `package domain.faction.providers` estando en `domain/equipment/providers`); no se eliminó por la regla 2 de AGENTS.md. Candidato a eliminar con permiso.
- Las variantes (Papal States, Red Brigade, etc.) aún no tienen armería propia; se reutiliza la de su facción base.
- Reglas especiales muy específicas (p. ej. Grail Devotee 2º en Gloria, Overcharge) están modeladas como texto en `specialRules`, no como lógica ejecutable.

## 2026-10-09 (3) — Pendientes de las facciones "pecadoras" (Heretic Legions, Black Grail, Great Hunger, The Court)

Contexto: el usuario pidió registrar en MEMORY lo que falta por implementar de las facciones Fallen. Revisado contra `app/sources/` (Warbands of Trench Crusade pp.108-170, October-Rules-Review-2026.pdf, The-Great-Hunger-1.0.2.pdf). Esto es SOLO registro de pendientes — no se modificó código.

### Estado actual (verificado)
- **Heretic Legions**: roster base de 8 unidades completo y correcto. Armería `heretic_legions` completa vs libro.
- **Black Grail**: roster base de 8 entradas (Lord/Plague Knight/Corpse Guard/Grail Thrall/Fly Thrall/Hound/Herald/Amalgam) correcto vs pp.133-139.
- **Great Hunger**: NO es una facción aparte; es variante `black_grail_great_hunger` de Black Grail que REUTILIZA el roster base (decisión previa). El libro ofrece un roster propio completo que NO está implementado.
- **The Court**: roster ya actualizado al October-Rules-Review-2026 (Praetor 115 con Beacon of Corruption; Sorcerer 85 con Burning Inferno; Hell Knight 100/Blood Magic; Hunter 110/Cleth; Wretched 20 Goaded Forth; Pit Locust 90 Insectile Stingers; Yoke Fiend 35 Glory Lost; Desecrated Saint 140 Aura de Sin/Relentless/Totemic). ✅ La ARMERÍA base `the_court` y los Goetic Powers SÍ tienen divergencias con October.

### Pendiente por facción

**A) The Great Hunger (fuente ya disponible: The-Great-Hunger-1.0.2.pdf)**
- Roster propio ausente (hoy usa los de Black Grail):
  - Matagot Hag — 135👑, **obligatorio ×1** (6"/Inf, Melee +2, ARM 0). Abilities: Cadre of Flesh, Frenzied Followers, Mother's Call ACTION, Pestilent, Undead Fortitude. Keywords: BLACK GRAIL, ELITE, FEAR, LEADER, NEGATE GAS, TOUGH.
  - Ravenous — 35👑 (5"/Inf, Melee +0). Gluttonous Horde, Ravenous Infection ACTION, Undead Fortitude. Sin Battlekit; hasta 3 Hunger Strains. Keywords: BLACK GRAIL, FEAR, NEGATE GAS.
  - Gregori Gula — 0-2 = 140👑 (9"/Flying, Melee +2). Dormant Hunger, Gnashing and Tearing, Plague-Ridden Flesh, Unholy Gut (+Vomitus 40👑 opcional). Keywords: BLACK GRAIL, FEAR, INFILTRATOR, NEGATE GAS.
  - Butcher Knights (0-2 Plague Knights c/ Ravenous Infection + rangos: Butcher King 10👑, Knight Companion of the Feast 5👑, Knight of Twin Cleavers 5👑), Cradle Thralls (0-3, entry Ravenous + INFILTRATOR, 2☼), Desiccated Husks (0-2, entry Corpse Guard, Melee CRITICAL, More Worm Than Man), Great Maw (0-1 solo si warband ≥1000👑, entry Lord of Tumours sin LEADER).
- Reglas especiales de variante faltantes como lógica: Eternal Appetence (4 efectos por turno), Spawn of Gluttony (prohibe Lord of Tumours, Corpse Guards, Grail Thralls, Heralds, Amalgam), Excruciating Hunger (lista de battlekit prohibido), The Great Maw, Cradle of Filth.
- Armería/battlekit Great Hunger faltante: Black Spot Rifle (25👑, Limit 2, Bayonet Lug+Shield), Butcher Bayonet (15👑, Limit 2, Shield Combo), Cup of Filth (4☼, Matagot Hag, L1), Foetid Palanquin (4☼, Matagot Hag, L1), Pestilent Fang (15👑, L2), Grail Devotee (15👑 o 2☼), Parasite Grenades.
- Hunger Strains no modeladas: Devouring Jaws (2☼ ELITE L1), Grasping Maw (15👑), Hellfly Host (0☼ Ravenous, 6"/Flying), Lockjaw Bite (15👑), Papillal Hide (5👑 Ravenous+Hounds), Rotten Cutters (10👑 Matagot Hag+Ravenous, CLEAVE 2), Unending Starvation (10👑, +1" Mov).

**B) Heretic Legions**
- Upgrade **Heretic Legionnaire** ausente (pp.111): +10👑 sobre Heretic Trooper, subir Ranged o Melee de 0→+1, nunca más Legionnaires que Troopers. La armería ya referencia "Legionnaires" (`e_hellbound_soul_contract`) pero no existe la unidad.
- Restricciones de battlekit por unidad sin aplicar: Death Commando (solo Silenced Pistols + Tormentor Chains; solo Gas Grenades; **Tormentor Chain no existe en el catálogo**), Artillery Witch (sin Ranged Weapons ni Grenades; Infernal Bomb no está en StandardBattlekit), Wretched (≤10👑 de kit + ≥1 arma), War Wolf (Chainsaw Mouth + Shredding Claws fijas, sin kit), Anointed (Reinforced Armour + Infernal Brand fijos, ya en perfil).
- Armerías y reglas de variantes ausentes:
  - Trench Ghosts (pp.118): Sarcophagus Mine (30👑, Heretic Troopers only, L2), Tank Palanquin (60👑, Heretic Priest only); restricciones (-ARTIFICIAL, sin Soul Contracts/Infernal Brands, Barbed Wire Banshee). Reglas Semi-corporeal/Slow/Spectral/Undead Horror sólo como texto parcial.
  - Knights of Avarice (pp.119-122): armario único faltante — Coin Hammer (20👑, L2), Golden Calf Altar (20👑, Consumable, L3), Gas Bombs (sustituyen Infernal Bomb), Standard of Mammon (25👑, L1), Tarnished Armour (25👑, L1); reglas Corrupt Merchants (1 NA + 1 IS), sin Death Commandos, min 80👑/modelo, sin FIRE/SHRAPNEL, Price of Greed ACTION.
  - Naval Raiders (pp.123 + Heretic-Naval-Raiders-Warband.pdf): SMGs a 25👑, sin War Wolf, máx 2 Anointed + 1 Artillery Witch, Unseen Advance (+10👑 INFILTRATOR sin ELITE); el PDF suplementario define unidades propias (Heretic Captain, Abyssal Commando, Drowned Chorister, Sea Hag) que hoy no existen como entradas.
- `w_antimateriel_rifle`/`w_automatic_rifle` en Gloria: correcto (pp.106).

**C) Black Grail (base)**
- Abilities de unidades incompletas:
  - Corpse Guard: falta **Parasitic Tick** (tiene Bodyguard + Undead Fortitude).
  - Hound: falta upgrade **Infested Carcasses** (+5👑).
  - Herald: falta upgrade **Maddening Buzzing** (+10👑); battlekit debe limitarse a Ranged Weapons + Compound Eyes Helmet + Grail Devotee (sin aplicar); falta keyword SKIRMISHER.
  - Plague Knights Ranks (sistema de rangos) sin modelar.
- Dirge of the Great Hegemon (pp.140-142) como variante, pendientes:
  - **Bereaved**: Thralls pasan a Ranged +0, 30👑, pueden llevar Ranged/Grenades/Musical Instrument/Troop Flag (hoy reutilizan Thrall base 25👑).
  - **Executor** (entry Plague Knight + Ranged +1 + TOUGH, 80👑) sólo como texto en `FactionRule`, no como unidad reclutable.
  - Armería Dirge faltante: **Broken Crown** (25👑, ELITE only, L1), **Urn of the Bitter Ashes** (40👑, ELITE only, L1).
  - Ajustes: Putrid Shotgun Limit 3; Viscera Cannon Limit 3 y sin ELITE-only; prohibido Beelzebub's Axe y Black Grail Shield; Hegemon's Will (Command Bereaved ACTION).

**D) The Court — divergencias de armería vs October (corregir `the_court` en FactionArmories.kt)**
- Precios desactualizados respecto a October-Rules-Review-2026:
  - `court_serpent_assault_gun`: app 50 → libro 45.
  - `court_malebranche_sword`: app 50 → libro 35.
  - `court_torture_instrument`: app 8 sin restricción → libro 10 y **DEMONIC only**.
  - `g_incendiary` (Court): app 15 → libro 10, Limit 2.
- Items del Court faltantes en el catálogo:
  - `court_fiendbound_gatling_gun` (20👑, Limit 3).
  - `court_jaws_of_iron` (5👑, Yoke Fiends only) — granada.
  - `court_tormentor_chain` (3☼, Hell Knights & Desecrated Saint only, Limit 2).
  - `court_iron_maiden_flail` (4☼, Hell Knights only, Limit 2).
  - `court_chthonic_vessel` (1☼, ELITE only, Limit 1, Consumable).
  - Correcciones de perfil menores: Bow of Lethe (ASSAULT, HELD, INNATE), Infernal Iron Armour (IMPERVIOUS, INNATE, ya en perfil -2).
- Goetic Powers base (ANY WARBAND, pp.7) NO modeladas como datos: Black Heart (5👑, Spell Cost 1, ELITE, L2), Blessing of the Serpent Moon (5👑, Spell 2/4/6, Sorcerer), Infernal Challenge (10👑, Ability, Hell Knights), Raining Blood (10👑, Spell 1-3, ELITE, L3), Slavemaster (10👑, Spell Cost 1, ELITE).
- Variantes de pecado (October pp.12-36): reclutamiento por pecado, Goetic Powers propios (Light of the Morning Star, Covetous, Tempt Fate, Agent of Change, Blind Rage, Charm of Acedia…) y battlekit único (Laurels of the Supreme, Lance of Asmoday, Apollyon's Ember, Spear of Apollyon, Iscariot's Rope, Queen's Bones, Jawed Bascinet, Tawdry Finery…). Hoy las 7 variantes reutilizan el roster/armería base y las reglas están sólo como texto en `FactionRule`.

### Nota de restauración
No se borró ni modificó código en esta entrada; solo se registró el estado pendiente de las facciones Fallen para reanudar trabajo futuro.

## 2026-10-12 — Roster y armería propios por variante (Great Hunger, Dirge, Heretic variants, The Court)

Contexto: se retomaron los pendientes registrados en la entrada 2026-10-09 (3). Se añadió soporte de roster/armería por variante y se implementaron los datos ya disponibles (Great Hunger, Dirge, Trench Ghosts, Knights of Avarice, Naval Raiders y las correcciones de The Court). Fuentes: `The-Great-Hunger-1.0.2.pdf`, `October-Rules-Review-2026.pdf`, `Warbands of Trench Crusade` y los suplementos, más los textos extraídos en `C:\Users\kmilo\AppData\Local\Temp\` (incl. `opencode\variant_armories\`).

### Cambios realizados
- `domain/model/faction/Faction.kt`: `FactionVariant` ahora acepta `units: List<WarbandMember> = emptyList()` (vacío = roster de la facción base) y `armoryId: String? = null` (null = armería de la facción base). Añadido import de `WarbandMember`.
- `ui/screens/warband/WarbandCreationScreen.kt`: la armería se resuelve con `activeVariant.armoryId ?: selectedFactionId` (`remember(selectedFactionId, selectedVariantId)`) y el diálogo de unidades usa `activeVariant.units.takeIf { it.isNotEmpty() } ?: factionProvider.getAvailableUnits()`.
- Nuevos rosters propios (`domain/faction/providers/`):
    - `GreatHungerRoster.kt`: Matagot Hag (135, Leader), Butcher Knight (60, Elite), Desiccated Husk (55, Elite), Great Maw (130, Elite), Ravenous (35), Cradle Thrall (0👑/2☼), Gregori Gula (140, Flying).
    - `BlackGrailRoster.kt`: `base()` (extraído de `BlackGrailFactionProvider`; añade **Parasitic Tick** a Corpse Guard, **Infested Carcasses +5👑** al Hound, **Maddening Buzzing +10👑** y **SKIRMISHER** al Herald) y `dirge()` (Executor 80 con Ranged +1/TOUGH, Bereaved Grail/Fly a 30 con Ranged +0, Plague Knight y Corpse Guard con Command Bereaved).
    - `NavalRaidersRoster.kt`: Heretic Captain (80), Abyssal Commando (90), Drowned Chorister (65), Sea Hag (100), Heretic Raider (30), Wretched (25), Anointed Heretic Raider (95).
- `BlackGrailFactionProvider.kt`: reducido; `getAvailableUnits()` delega en `BlackGrailRoster.base()`.
- `WarbandVariants.kt`: conectados `units`/`armoryId` a Great Hunger, Dirge, Trench Ghosts, Knights of Avarice y Naval Raiders; reescritas las reglas de Naval Raiders (Fast as Lightning, Unseen Advance, Mercenaries) y renombrada a "Heretic Naval Raiders".
- `domain/equipment/providers/FactionBattlekit.kt`:
    - Correcciones de The Court: Serpent Assault Gun 50→45; Malebranche Sword 50→35 con keywords DEADLY/HEAVY; Torture Instrument 8→10; Headtaker (Ambush Weapon +2 INJURY MODIFIER).
    - Nuevos items: Court (Fiendbound Gatling Gun, Iron Maiden Flail, Jaws of Iron, Chthonic Vessel, Tormentor Chain, 5 Goetic Powers), Great Hunger (Black Spot Rifle, Butcher Bayonet, Cup of Filth, Foetid Palanquin, Pestilent Fang, 7 Hunger Strains), Dirge (Broken Crown, Urn of the Bitter Ashes), Trench Ghosts (Sarcophagus Mine, Tank Palanquin), Knights of Avarice (Coin Hammer, Golden Calf Altar, Gas Bombs, Standard of Mammon, Tarnished Armour), Naval Raiders (Boarding Axe, Hull Drill, Leviathan Bell, Infernal Bomb, Shark Tooth Talisman, Wrecker's Torch).
- `domain/equipment/providers/FactionArmories.kt`:
    - `the_court` corregido (precios October) y ampliado con Fiendbound Gatling Gun, Iron Maiden Flail, Jaws of Iron, Chthonic Vessel, Tormentor Chain, Torture Instrument DEMONIC-only, Incendiary 15→10 Limit 2 y los 5 Goetic Powers base.
    - Nuevas armerías: `black_grail_great_hunger`, `black_grail_dirge`, `heretic_trench_ghosts`, `heretic_knights_of_avarice`, `heretic_naval_raiders` (con SMG a 25👑, Incendiary 10👑, sin Binoculars, Reinforced Armour ELITE/Anointed-only, Soul Contract restringido a Raiders/Legionnaires).
- `EquipmentCatalogTest.kt`: test de facciones pasa a `containsAll(baseFactions)` + claves de variante; nuevos tests de armerías de variante y correcciones de The Court.

### Decisión de arquitectura
- Se optó por resolver roster y armería por `armoryId`/`units` en `FactionVariant` en lugar de crear providers sueltos, manteniendo el patrón Open/Closed. Las armerías de variante son claves adicionales en el mismo `FactionArmories.byFactionId`.

### Verificación
- `.\gradlew.bat assembleDebug` → BUILD SUCCESSFUL.
- `.\gradlew.bat testDebugUnitTest` → BUILD SUCCESSFUL.
- Nota de entorno: `JAVA_HOME` debe apuntar a `C:\Program Files\Android\Android Studio\jbr` en esta máquina.

### Pendiente / notas
- **Heretic Legionnaire** (upgrade +10👑 sobre Heretic Trooper, +1 a Ranged o Melee, nunca más Legionnaires que Troopers) sigue sin unidad; sólo referenciado en la armería.
- Restricciones de battlekit muy específicas por unidad (Death Commando = sólo Silenced Pistols/Tormentor Chains + Gas Grenades; Wretched ≤10👑 + ≥1 arma; Artillery Witch sin Ranged/Grenades; War Wolf sin kit) están en texto, no como lógica ejecutable.
- Armerías de las 7 variantes de pecado del Court (pride/greed/lust/envy/gluttony/wrath/sloth) y de las variantes de New Antioch/Pilgrims/Sultanate aún no implementadas (hay datos extraídos en `Temp\opencode\variant_armories\` para retomarlas). Hoy las variantes Court reutilizan `the_court` y las de NA/TP/IS su facción base.
- `NewAntiochArmoryProvider.kt` ya no existe (pendiente resuelto de la entrada anterior).
- No se hizo commit; los cambios quedan en el árbol de trabajo.

## 2026-10-12 (2) — Heretic Legionnaire + corrección de la clave de armería heretic

Contexto: se eligió cerrar el pendiente del **Heretic Legionnaire** (Warbands of Trench Crusade p.111). Al revisarlo se descubrió un bug latente: la armería de los Heretic estaba registrada bajo la clave `"heretic_legions"` (plural) mientras el id de facción es `"heretic_legion"` (singular), por lo que `FactionArmoryRegistry.forFaction("heretic_legion")` nunca encontraba la tabla y caía silenciosamente al `DefaultFactionArmoryProvider`.

### Cambios realizados
- `domain/faction/providers/HereticLegionFactionProvider.kt`: añadidas dos entradas `TrooperMember` (misma unidad, perfiles alternativos para expresar la elección obligatoria del libro):
    - `heretic_legionnaire_ranged` — "Heretic Legionnaire (Ranged)", 40👑, 6"/Infantry, Ranged +1 / Melee +0, keyword HERETIC.
    - `heretic_legionnaire_melee` — "Heretic Legionnaire (Melee)", 40👑, 6"/Infantry, Melee +1 / Ranged +0, keyword HERETIC.
    - Regla en texto: "Legionnaires of Hell: no puede haber más Heretic Legionnaires que Heretic Troopers" (upgrade = +10👑 sobre Heretic Trooper; el libro permite subir Ranged **o** Melee de 0 a +1).
- `domain/equipment/providers/FactionArmories.kt`: la clave de la tabla heretic pasa de `"heretic_legions"` a `"heretic_legion"` para coincidir con `Faction.id` (arregla el fallback silencioso al arsenal genérico).
- `EquipmentCatalogTest.kt`: `baseFactions` actualizado (`heretic_legions` → `heretic_legion`).
- `FactionRepositoryTest.kt`: nuevo test `test heretic legionnaires upgrade the trooper profile` (coste 40 y perfiles Ranged/Melee esperados).

### Decisión de modelado
- El libro muestra un solo perfil "Heretic Legionnaire" con "See below" en Ranged/Melee y obliga a elegir +1 en uno de los dos. Como el reclutador actual es una lista sin selector de características, se representó como **dos entradas** equivalentes (Ranged/Melee) para poder materializar la elección. Documentado aquí por si se prefiere una única entrada con un selector en el futuro.

### Verificación
- `.\gradlew.bat testDebugUnitTest` → BUILD SUCCESSFUL.
- `.\gradlew.bat assembleDebug` → BUILD SUCCESSFUL.
- Nota de entorno: `JAVA_HOME` = `C:\Program Files\Android\Android Studio\jbr`.

### Pendiente / notas
- La regla "no más Legionnaires que Troopers" queda como texto; no hay validación de conteo en la UI.
- Restricciones de battlekit por unidad (Death Commando, Wretched, Artillery Witch, War Wolf) siguen sin ser lógica ejecutable.
- Armerías de variantes de The Court (7 pecados) y de New Antioch/Pilgrims/Sultanate aún pendientes.
- Las entradas de Legionnaire reutilizan la armería heretic; el id `heretic_legionnaire` (sin sufijo) no se usa, se usan `_ranged`/`_melee`.
- No se hizo commit.

## 2026-10-12 (3) — Black Grail: Plague Knight Ranks y reglas de Dirge

Contexto: se continuó con Black Grail (Warbands of Trench Crusade pp.126-142). El pendiente principal era el sistema de **Plague Knight Ranks** (p.127), marcado como "no modelado". También se completaron las reglas de la variante Dirge of the Great Hegemon.

### Cambios realizados
- `domain/faction/providers/BlackGrailRoster.kt`: nuevo helper privado `plagueKnightRanks(extraSkills)` que devuelve las 4 entradas de rango (mismo patrón de "entrada por perfil" que el Heretic Legionnaire):
    - `grail_plague_almoner` — "Plague Almoner", 70👑 (base 60 +10), Melee +2: reduce en 1 el coste en BLOOD/INFECTION MARKERS al convertir una Injury Roll en Bloodbath Roll.
    - `grail_knight_companion_of_the_fly_ranged` — 65👑 (base 60 +5), Ranged +1 / Melee +2.
    - `grail_knight_companion_of_the_fly_melee` — 65👑, Ranged +0 / Melee +3.
    - `grail_knight_of_the_rotten_cross` — 65👑, Limit 1: puede comprar 1 arma a distancia o de melee de la armería de New Antioch o Heretic Legions.
    - Los rangos se añaden a `base()` y a `dirge()` (en Dirge con la habilidad extra "Command Bereaved").
- `domain/faction/providers/WarbandVariants.kt`: la variante Dirge ahora incluye las reglas que faltaban — The Bereaved, Dishonoured y Hegemon's Last Blessing — y se amplió The Executor y Hegemon's Will con su texto real.
- `FactionRepositoryTest.kt`: nuevo test `test black grail exposes plague knight ranks` (costes, perfiles y unicidad de ids).

### Verificación
- `.\gradlew.bat testDebugUnitTest assembleDebug` → BUILD SUCCESSFUL.

### Pendiente / notas
- La armería del Black Grail base ya refleja las armas únicas; la restricción de battlekit por unidad del Herald (solo Ranged Weapons + Compound Eyes Helmet + Grail Devotee) sigue sin ser lógica ejecutable.
- La regla "0-2 Plague Knights" / "1-3 si no hay Lord of Tumours" y "el Executor es obligatorio en Dirge" no se validan; quedan como texto.
- Los rangos son add-ons de un Plague Knight; la app los representa como entradas independientes (un Plague Knight ya con el rango), no como modificadores del mismo modelo.
- Armerías de variantes de The Court (7 pecados) y de New Antioch/Pilgrims/Sultanate aún pendientes.
- No se hizo commit.

## 2026-10-12 (4) — The Great Hunger: reglas reales, rangos de Butcher Knight y Vomitus

Contexto: se continuó con la variante The Great Hunger (suplemento The-Great-Hunger-1.0.2.pdf). Se detectó que las reglas de la variante en `WarbandVariants` eran genéricas/inventadas y no coincidían con el suplemento; además faltaban los rangos propios de los Butcher Knights y el arma Vomitus.

### Cambios realizados
- `domain/faction/providers/WarbandVariants.kt`: la variante `black_grail_great_hunger` ahora tiene las reglas reales del suplemento (11 en total): Spawn of Gluttony, Eternal Appetence + sus 4 efectos (Agonised Churning, Ruinous Masticating, Spasmodic Wretching, Vile Craving), Butcher Knights, Cradle of Filth, Desiccated Husks, Excruciating Hunger y The Great Maw.
- `domain/faction/providers/GreatHungerRoster.kt`:
    - Añadidos los 3 rangos propios de Butcher Knight: `gh_butcher_king` (70👑), `gh_knight_companion_of_the_feast` (65👑) y `gh_knight_of_twin_cleavers` (65👑).
    - `gh_butcher_knight` ya no lista los rangos como texto; ahora tiene la regla que los referencia.
    - `gh_desiccated_husk`: añadido Parasitic Tick y notificado el keyword CRITICAL en Melee (sustituye Bodyguard por More Worm Than Man).
- `domain/equipment/providers/FactionBattlekit.kt`: nueva arma única `gh_vomitus` (Vomitus, 40👑, tipo SPECIAL, 8", +1 INJURY MODIFIER / ASSAULT / INFECTION MARKERS; Torrent of Bile + Vile Ferment). Es el Unholy Gut de Gregori Gula.
- `domain/equipment/providers/FactionArmories.kt`: `gh_vomitus` añadido a `black_grail_great_hunger` restringido a Gregori Gula.
- Tests: `FactionRepositoryTest` (nuevo test de la variante Great Hunger y sus rangos) y `EquipmentCatalogTest` (comprueba `gh_vomitus` en el master).

### Verificación
- `.\gradlew.bat testDebugUnitTest assembleDebug` → BUILD SUCCESSFUL.

### Pendiente / notas
- Los efectos de Eternal Appetence y el resto de reglas quedan como texto; no hay lógica ejecutable de INFECTION MARKERS.
- La restricción de battlekit de la Matagot Hag (solo Cup of Filth, Grail Devotees y/o Foetid Palanquin) y la de Gregori Gula (sin Battlekit salvo Vomitus) siguen sin ser lógica ejecutable.
- El límite de Great Maw (solo si el resto de la banda ≥1000👑) no se valida.
- Armerías de variantes de The Court (7 pecados) y de New Antioch/Pilgrims/Sultanate aún pendientes.
- No se hizo commit.

## 2026-10-12 (5) — Armerías de todas las variantes pendientes (Court 7 pecados, New Antioch, Trench Pilgrims, Iron Sultanate)

Contexto: se cerró el pendiente recurrente de las armerías de variante. Se implementaron las tablas de equipo de las 18 variantes que aún reutilizaban la armería de su facción base, usando los datos ya extraídos en `C:\Users\kmilo\AppData\Local\Temp\opencode\variant_armories\` (`new_antioch.txt`, `sultanate_pilgrims.txt`, `standalone_books.txt`, `heretic_grail_court.txt`) y los perfiles creados en la entrada (4).

### Cambios realizados
- `domain/equipment/providers/FactionBattlekit.kt` (sesión previa a este registro, ya en el árbol): añadidos todos los perfiles únicos de Battlekit que faltaban para las variantes (Papal Crucifix, Lochaber Axe, Tank-Splitter Sword, Holy Smoke, shotel/agua bendita/anfarro/tabot de Abyssinia, kit de Red Brigade, kit de Sacred Affliction, armamento y módulos del Anchorite Shrine de Saint Methodius, Sacrificial Lamb del Tenth Plague, Bow of Alamut/Golden Khanjar/Hashashin Leaf de los Fida'i, Elixir of Al-Khidr/Fire Shield de House of Wisdom, y los 5 ítems de cada uno de los 7 pecados del Court).
- `domain/equipment/providers/FactionArmories.kt` (refactor):
    - Las 6 tablas base se extraen a `private val` (`newAntiochBase`, `trenchPilgrimsBase`, `ironSultanateBase`, `hereticLegionBase`, `blackGrailBase`, `courtBase`) sin cambiar su contenido (verificado contra el HEAD, salvo la clave `heretic_legions`→`heretic_legion` ya corregida en (2)).
    - Nuevos helpers `List<ArmoryEntry>.update(id, amount?, currency, stip)` y `.removing(vararg ids)` para componer variantes por diferencia.
    - Las variantes ya existentes (Great Hunger, Dirge, Trench Ghosts, Knights of Avarice, Naval Raiders) se extrajeron a `private val` equivalentes; su contenido no cambió.
    - 18 nuevas armerías de variante y registro en `byFactionId`: `antioch_papal_states`, `antioch_eire_rangers`, `antioch_alba`, `antioch_prussia`, `antioch_abyssinia`, `antioch_red_brigade`, `pilgrim_sacred_affliction`, `pilgrim_methodius`, `pilgrim_tenth_plague`, `sultanate_fidai`, `sultanate_house_of_wisdom`, `court_pride`, `court_greed`, `court_lust`, `court_envy`, `court_gluttony`, `court_wrath`, `court_sloth`.
- `domain/faction/providers/WarbandVariants.kt`: cableado `armoryId` en las 18 variantes anteriores (New Antioch, Trench Pilgrims, Iron Sultanate Fida'i/House of Wisdom, y los 7 pecados del Court). `sultanate_iron_wall` queda sin `armoryId` (usa la base `iron_sultanate`).
- `EquipmentCatalogTest.kt`: la lista `variantArmories` ahora incluye las 18 nuevas claves (23 en total, incl. las 5 previas) y los tests de existencia/resolución al catálogo maestro las recorren todas.

### Decisión de modelado
- Se mantiene la composición por diferencias (`base + update + removing`) en vez de duplicar tablas completas, reduciendo el riesgo de divergencias y respetando el patrón Open/Closed: añadir una variante es añadir una lista + una entrada en `byFactionId` + su `armoryId`.
- Limitación conocida: los precios con doble tarifa no modelables (p. ej. Iron Maiden Flail del Envy, que cuesta distinto para el Desecrated Saint) se aproximan a un único valor; documentado aquí.

### Verificación
- `.\gradlew.bat testDebugUnitTest` → BUILD SUCCESSFUL (suite completa).
- `.\gradlew.bat assembleDebug` → BUILD SUCCESSFUL.
- Nota de entorno: `JAVA_HOME` = `C:\Program Files\Android\Android Studio\jbr`.

### Pendiente / notas
- Las nuevas armerías reutilizan el roster de su facción base (las variantes de NA/TP/IS/Court no tienen unidades propias aún; sólo Great Hunger/Dirge/Naval Raiders las tienen).
- Restricciones de battlekit por unidad siguen en texto, no como lógica ejecutable.
- Puntos 2 (restricciones de battlekit), 3 (validaciones de conteo) y 4 (lógica de marcadores) del plan general siguen pendientes.
- No se hizo commit; los cambios quedan en el árbol de trabajo.

## 2026-10-12 (6) — Convención de documentación: carpeta `informes/`

Contexto: se definió una ubicación única para los informes de sesión.

### Cambios realizados
- Creada la carpeta `informes/` en la raíz del proyecto (por encima de las demás carpetas).
- Movido el informe creado en la sesión anterior a `informes/INFORME_2026-10-12_ARMERIAS_VARIANTES.md` (antes estaba en la raíz).
- Convención de nombre: `INFORME_<AAAA-MM-DD>_<tema>.md`.
- `app/sources/spec.md`: nueva sección "Registro de informes (carpeta `informes/`)".
- `MEMORY.md`: nota de convención al inicio del archivo.

### Verificación
- No aplica compilación (solo documentación). El código no se modificó en esta entrada.
