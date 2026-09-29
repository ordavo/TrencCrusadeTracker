# ⚔️ Trench Crusade Tracker

> **Nota:** Este es mi primer proyecto desarrollado con la asistencia de Inteligencia Artificial (IA), diseñado para servir como herramienta de acompañamiento, gestión de bandas de guerra (*Warbands*) y seguimiento de partidas en vivo para el juego de mesa y miniaturas **Trench Crusade**.

---

## 📜 Descripción General

**Trench Crusade Tracker** es una aplicación móvil nativa para Android desarrollada en Kotlin utilizando **Jetpack Compose**. La aplicación permite a los jugadores crear y administrar sus huestes de guerra, llevar un registro detallado del equipamiento de cada miniatura, gestionar campañas por fases (Cuartel y Batalla) y anotar en tiempo real el transcurso de los enfrentamientos.

---

## 🚀 Características Principales

### 1. 🛡️ Gestión de Huestes de Guerra (*Warbands*)
- Creación de bandas pertenecientes a las distintas facciones oficiales (Nueva Antioquía, Legión Herética, Peregrinos de las Trincheras, Sultanato de Hierro, Procesión de la Afligida Sagrada, etc.).
- Gestión de integrantes por roles: **Líderes**, **Tropas**, **Élite** y **Mercenarios**.
- Cálculo automático de costos totales en **Ducados** y **Puntos de Gloria**.

### 2. 🎒 Battlekit Estructurado en 7 Categorías
El equipamiento (*Battlekit*) de cada modelo se divide en las 7 categorías oficiales del libro de reglas:
1. **Ranged Weapons** (*Armas a distancia*)
2. **Grenades** (*Granadas*)
3. **Melee Weapons** (*Armas cuerpo a cuerpo*)
4. **Armour** (*Armaduras*)
5. **Shields** (*Escudos*)
6. **Equipment** (*Equipo general*)
7. **Special** (*Objetos especiales, reliquias y habilidades*)

### 3. 💰 Monedas y Fuertes de Equipamiento
- Soporte para compras con **Ducados** y **Puntos de Gloria**.
- Clasificación según la procedencia del objeto:
  - **Standard** (Armería Estándar)
  - **Black Market** (Mercado Negro)
  - **Exploration** (Tabla de Exploración del libro general)

### 4. 🏕️ Control de Campañas y Fases
- **Fase de Cuartel (*Barracks*)**: Permite la edición completa de miniaturas, compras y equipamiento.
- **Fase de Batalla (*Battle*)**: Bloquea la edición de bandas para garantizar la integridad durante los enfrentamientos y oculta los detalles completos de la banda rival.

### 5. 🎲 Registro de Partidas en Vivo
- Control de turnos de juego.
- Anotación de Puntos de Victoria (VP).
- Registro de Muertes Gloriosas (*Glorious Deaths*).

### 6. 👤 Perfil y Reglamento
- Interfaz de entrada con navegación unificada a las 5 secciones: *Warbands*, *Juegos*, *Reglas*, *Campaña* y *Perfil*.
- Acceso a las reglas e información técnica.

---

## 🎨 Paleta de Colores y Estilo Visual

La interfaz sigue una estética *grimdark* adecuada para el universo de Trench Crusade:

- **Fondo Principal**: `#1A0B2E` (Morado Oscuro)
- **Barras de Navegación y Botones**: `#960018` (Rojo Carmesí)
- **Texto Principal**: `#E5D8C8` (Blanco Pergamino)
- **Acentos**: `#C8A762` (Dorado Antiguo)

---

## 🏗️ Arquitectura del Proyecto

El código está estructurado siguiendo los principios de **Clean Architecture** orientada a dominio:

```
app/src/main/java/com/example/trenchcrusadetracker/
├── domain/model/                # Modelos de dominio
│   ├── equipment/
│   │   └── Equipment.kt         # Monedas, Fuentes, Categorías del Battlekit y Sealed Class de Equipamiento
│   ├── warband/
│   │   ├── Battlekit.kt         # Estructura del kit de combate (7 categorías)
│   │   ├── WarbandMember.kt     # Superclase abstracta de miniaturas (Leader, Trooper, etc.)
│   │   └── Warband.kt           # Agregado principal de la banda de guerra
│   ├── faction/
│   │   └── Faction.kt           # Facciones y reglas especiales
│   ├── campaign/
│   │   ├── CampaignPhase.kt     # Fases de campaña (Barracks, Battle, Finished)
│   │   ├── Player.kt            # Datos del jugador
│   │   └── Campaign.kt          # Objeto de campaña
│   └── game/
│       ├── Scenario.kt          # Escenarios de batalla
│       ├── TurnLog.kt           # Registro por turno (VP, Glorious Deaths)
│       └── GameMatch.kt         # Estado de la partida
├── ui/
│   ├── theme/                   # Tema visual y paleta de colores oficial
│   └── screens/
│       └── home/
│           └── HomeScreen.kt    # Pantalla principal de entrada
└── MainActivity.kt              # Punto de entrada y contenedor de navegación
```

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Kotlin 2.2.10
- **UI**: Jetpack Compose con Material Design 3
- **Iconos**: Material Icons Extended
- **Construcción**: Gradle Kotlin DSL (`build.gradle.kts`)
- **Testing**: JUnit 4

---

## 🧪 Compilación y Pruebas

Para ejecutar la compilación del proyecto y verificar los test unitarios:

```bash
# Compilar la aplicación en modo Debug
./gradlew assembleDebug

# Ejecutar las pruebas unitarias
./gradlew testDebugUnitTest
```

---

## 📚 Fuentes y Documentación Adicional

Los reglamentos digitales en PDF, paquetes de escenarios, FAQs y especificaciones del proyecto se encuentran organizados dentro de la carpeta:
`app/sources/`

---

*Desarrollado con el apoyo de Asistencia de Inteligencia Artificial para Trench Crusade.*
