# AGENTS.md — Trench Crusade Tracker

Android application (Kotlin + Jetpack Compose, Material 3) for building Trench Crusade warbands, campaigns, reading rulebooks/profiles, and tracking live matches turn-by-turn. Single Gradle module `:app`.

---

## 1. Visión General del Proyecto
- **Propósito:** Permitir la creación y guardado de *warbands*, gestión de campañas, consulta interactiva de reglas (por sección, personaje, arma y juego) y registro en vivo de partidas (turnos, puntos de victoria y *Glorious Deaths*).
- **Mantenibilidad:** Diseñado con arquitectura limpia y desacoplada (principios SOLID) para que sea fácil de mantener y extender por un ingeniero junior.
- **Evolución de Reglas:** La arquitectura debe permitir agregar nuevas funcionalidades o actualizar mecánicas ante nuevos lanzamientos del libro de reglas de Trench Crusade sin romper las características existentes.

---

## 2. Comandos de Consola (Windows)
*Nota: Usar siempre los comandos oficiales de Gradle. No existen scripts o alias personalizados (`orden66`, `testealo`, etc.).*

- **Compilar Debug:** `.\gradlew.bat assembleDebug`
- **Pruebas Unitarias:** `.\gradlew.bat testDebugUnitTest`
- **Pruebas de una Clase/Método específico:**  
  `.\gradlew.bat testDebugUnitTest --tests "com.example.trenchcrusadetracker.domain.ElitePromotionTest"`
- **Pruebas de Instrumentación (requiere emulador/dispositivo):**  
  `.\gradlew.bat connectedDebugAndroidTest`
- **Formateo y Linter:** No existen tareas configuradas de `ktlint`, `detekt` o `spotless`. No las inventes ni intentes ejecutarlas.

---

## 3. Arquitectura y Estructura
- **Package Raíz:** `com.example.trenchcrusadetracker`
- **Capas:**
    - `domain/model/*`: Clases de datos puras (`warband`, `campaign`, `game`, etc.).
    - `domain/<feature>`: Interfaces de repositorios e implementaciones (`*Impl`). Separado por funciones como `faction`, `equipment`, etc.
    - `domain/**/providers/`: Catálogos de datos estáticos y reglas.
- **Inyección de Dependencias y Persistencia:** Actualmente **no** se usa ningún framework de DI (Hilt/Koin) ni base de datos local (Room). Los repositorios se instancian directamente (ej. `FactionRepositoryImpl()`).
- **Navegación:** Control manual mediante un `enum Screen` + `mutableStateOf` dentro de `MainActivity.kt`. No se utiliza la librería Navigation-Compose.
- **Pantallas Actuales:** Solo `HomeScreen` y `WarbandCreationScreen` están implementadas. Las rutas `GAMES`, `RULES`, `CAMPAIGN` y `PROFILE` redirigen a `PlaceholderScreen` en `MainActivity.kt`.

---

## 4. Patrón OCP: Agregar Facciones y Equipamiento
Para extender facciones sin modificar el código core (Open/Closed Principle):
1. Crear una clase `XxxFactionProvider : FactionProvider` (ejemplo: `<FactionName>FactionProvider.kt`) dentro de `domain/faction/providers/`.
2. Registrar el nuevo provider en la lista `providers` dentro de `FactionRepositoryImpl.kt`.
3. Los catálogos de equipamiento residen en `domain/equipment/providers/` y el arsenal maestro en `EquipmentRepositoryImpl.kt`.

---

## 5. Convenciones de Código y Estilo UI
- **Idiomas:**
    - Código fuente, nombres de variables, funciones e identificadores: **Inglés**.
    - Comentarios en el código y documentación interna: **Español**.
- **Interfaz de Usuario (Jetpack Compose):**
    - Seguir Material 3 en toda la app.
    - **Paleta de Colores Exclusiva:** Definida únicamente en `ui/theme/Color.kt`. Nunca escribas valores hexadecimales quemados (*hardcoded*) en los composables.
        - Fondo Principal (`DarkBackground`): `#1A0B2E`
        - Primario / Barras / Botones (`PrimaryCrimson`): `#960018`
        - Texto Principal (`ParchmentText`): `#E5D8C8`
        - Acentos / Detalles (`GoldAccent`): `#C8A762`
- **Gestión de Dependencias:**
    - Declarar todas las librerías en `gradle/libs.versions.toml` y referenciarlas mediante `libs.*`. No agregues versiones en texto plano dentro de `build.gradle.kts`.

---

## 6. Configuración del Toolchain y Build (No Modificar)
- **AGP & Gradle:** Fijado estrictamente a **AGP 8.10.1** y **Gradle 8.13** (límite para Android Studio Meerkat). No actualizar a AGP 9.
- **Plugin de Kotlin:** `app/build.gradle.kts` debe aplicar explícitamente `libs.plugins.kotlin.android`. De lo contrario, AGP 8 ignorará los archivos `.kt` y el build dirá que fue exitoso sin haber compilado nada.
- **AndroidX:** `gradle.properties` requiere `android.useAndroidX=true` (requerido por `checkDebugAarMetadata` en AGP 8).
- **SDK & Java Target:**
    - `compileSdk` = 35 / `targetSdk` = 35 (SDK instalado localmente: `android-35`).
    - `minSdk` = 24.
    - Compatibilidad de Java: `Java 11`. `jvmTarget` de Kotlin: `"11"`.

---

## 7. Reglas de Trabajo y Gobernanza para Agentes
1. **Fuente de Verdad del Juego:** Revisa los manuales PDF en `app/sources/` (reglas base, libros de banda, FAQs) y la especificación activa en `app/sources/spec.md`. Lee el manual en PDF correspondiente antes de implementar unidades o equipamiento de una facción.
2. **Conservación de Código:** Nunca borres código existente sin antes preguntar, justificar los cambios y asegurar que la app compila.
3. **Modificación de Funcionalidades:** No agregues features mayores no solicitadas sin preguntar y explicar el impacto.
4. **Registro de Cambios (Memory MD):** Cada vez que realices cambios significativos o completes una tarea, crea o actualiza un archivo de registro/memoria (`MEMORY.md` o documentación del sprint) para rastrear lo que se modificó y poder revertir si es necesario.
5. **Verificación Obligatoria:** Tras finalizar cualquier tarea, ejecuta las pruebas unitarias afectadas y compila el proyecto (`.\gradlew.bat testDebugUnitTest` y `.\gradlew.bat assembleDebug`) para garantizar que no hay regresiones.