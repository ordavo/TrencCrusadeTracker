# MEMORY.md — Registro de Cambios

Registro de cambios significativos para poder revertir si es necesario (regla 4 de AGENTS.md).

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