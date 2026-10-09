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