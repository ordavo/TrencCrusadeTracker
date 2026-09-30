# ⚔️ Trench Crusade Tracker

> **Nota:** Este es mi primer proyecto desarrollado con la asistencia de Inteligencia Artificial (IA), diseñado para servir como herramienta de acompañamiento, gestión de bandas de guerra (*Warbands*) y seguimiento de partidas en vivo para el juego de mesa y miniaturas **Trench Crusade**.

---

## 📜 Descripción General

**Trench Crusade Tracker** es una aplicación móvil nativa para Android desarrollada en Kotlin utilizando **Jetpack Compose**. La aplicación permite a los jugadores crear y administrar sus huestes de guerra, llevar un registro detallado del equipamiento de cada miniatura, gestionar campañas por fases (Cuartel y Batalla) y anotar en tiempo real el transcurso de los enfrentamientos.

---

## 🚀 Características Principales

### 1. 🛡️ Gestión de Huestes de Guerra (*Warbands*)
Soporte para las 9 facciones y bandas oficiales encontradas en los recursos:
1. **Principality of New Antioch** (Nueva Antioquía)
2. **Heretic Legion** (Legión Herética)
3. **Trench Pilgrims** (Peregrinos de las Trincheras)
4. **Iron Sultanate** (Sultanato de Hierro)
5. **Procession of the Sacred Affliction** (Procesión de la Afligida Sagrada)
6. **Prussian Stosstruppen** (Stosstruppen Prusianos)
7. **Heretic Naval Raiders** (Invasores Navales Heréticos)
8. **The Red Brigade** (La Brigada Roja)
9. **The Great Hunger** (El Gran Hambre)

Gestión de integrantes por roles: **Líderes**, **Tropas**, **Élite** y **Mercenarios**. Cálculo automático de costos totales en **Ducados** y **Puntos de Gloria**.

### 2. 🎒 Battlekit Estructurado en 7 Categorías
El equipamiento (*Battlekit*) de cada modelo se divide en las 7 categorías oficiales del libro de reglas:
1. **Ranged Weapons** (*Armas a distancia*)
2. **Grenades** (*Granadas*)
3. **Melee Weapons** (*Armas cuerpo a cuerpo*)
4. **Armour** (*Armaduras*)
5. **Shields** (*Escudos*)
6. **Equipment** (*Equipo general*)
7. **Special** (*Objetos especiales, reliquias y habilidades*)

### 3. 💰 Monedas y Fuentes de Equipamiento
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

---

## 🎨 Paleta de Colores y Estilo Visual

La interfaz sigue una estética *grimdark* adecuada para el universo de Trench Crusade:

- **Fondo Principal**: `#1A0B2E` (Morado Oscuro)
- **Barras de Navegación y Botones**: `#960018` (Rojo Carmesí)
- **Texto Principal**: `#E5D8C8` (Blanco Pergamino)
- **Acentos**: `#C8A762` (Dorado Antiguo)

---

## 🏛️ Aplicación de Principios SOLID

El diseño de las facciones y modelos sigue estrictamente los principios SOLID:

- **Single Responsibility Principle (SRP)**: Cada proveedor de facción (`FactionProvider`) encapsula únicamente la información, reglas y catálogo de unidades de su facción.
- **Open/Closed Principle (OCP)**: Nuevas bandas de guerra o expansiones de libros pueden agregarse creando un nuevo `FactionProvider` sin necesidad de modificar el código existente.
- **Liskov Substitution Principle (LSP)**: Cualquier implementación de `FactionProvider` es intercambiable por el repositorio de facciones.
- **Interface Segregation Principle (ISP)**: Interfaces segregadas (`FactionRuleProvider` y `UnitRosterProvider`) para desacoplar las reglas de los catálogos de tropas.
- **Dependency Inversion Principle (DIP)**: Los administradores de campañas y bandas dependen de la interfaz abstracta `FactionRepository`.

---

## 🏗️ Arquitectura del Proyecto

```
app/src/main/java/com/example/trenchcrusadetracker/
├── domain/
│   ├── model/                   # Modelos de dominio
│   │   ├── equipment/
│   │   │   └── Equipment.kt     # Monedas, Fuentes, Categorías del Battlekit y Sealed Class
│   │   ├── warband/
│   │   │   ├── Battlekit.kt     # Kit de combate en 7 categorías
│   │   │   ├── WarbandMember.kt # Superclase abstracta de miniaturas (Leader, Trooper, etc.)
│   │   │   └── Warband.kt       # Agregado de la hueste de guerra
│   │   ├── faction/
│   │   │   └── Faction.kt       # Facciones y reglas especiales
│   │   ├── campaign/
│   │   └── game/
│   └── faction/                 # Módulo de facciones (SOLID)
│       ├── FactionRuleProvider.kt
│       ├── FactionRepository.kt
│       ├── FactionRepositoryImpl.kt
│       └── providers/           # Proveedores de las 9 facciones
├── ui/
│   ├── theme/                   # Tema visual y paleta oficial
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
