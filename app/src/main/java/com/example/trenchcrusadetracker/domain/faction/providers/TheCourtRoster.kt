package com.example.trenchcrusadetracker.domain.faction.providers

import com.example.trenchcrusadetracker.domain.model.warband.EliteMember
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.ModelStats
import com.example.trenchcrusadetracker.domain.model.warband.MovementType
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember

/**
 * Rosters base compartidos para "The Court of the Seven-Headed Serpent" y sus variantes.
 * Fichas verificadas contra "October-Rules-Review-2026.pdf" (reglas actualizadas con SIN MARKERS)
 * y el libro maestro "Warbands of Trench Crusade" (páginas 145-167).
 */
internal object TheCourtRoster {

    fun praetor(): LeaderMember = LeaderMember(
        id = "court_praetor",
        customName = "Praetor",
        baseStats = ModelStats(
            movement = 8,
            movementType = MovementType.FLYING,
            melee = 3,
            ranged = 3,
            armor = 0,
            maxWounds = 1
        ),
        baseCostInDucats = 115,
        keywords = listOf("THE COURT", "DEMONIC", "ELITE", "FEAR", "FLYING", "LEADER", "STRONG", "TOUGH"),
        specialRules = listOf("Beacon of Corruption", "Goetic Powers (2)")
    )

    fun sorcerer(): EliteMember = EliteMember(
        id = "court_sorcerer",
        customName = "Sorcerer",
        baseStats = ModelStats(
            movement = 6,
            movementType = MovementType.FLYING,
            melee = 1,
            ranged = 1,
            armor = 0,
            maxWounds = 1
        ),
        baseCostInDucats = 85,
        keywords = listOf("THE COURT", "DEMONIC", "ELITE", "FEAR", "FLYING"),
        specialSkills = listOf("Burning Inferno", "Goetic Powers (3)")
    )

    fun hellKnight(): EliteMember = EliteMember(
        id = "court_hell_knight",
        customName = "Hell Knight",
        baseStats = ModelStats(
            movement = 6,
            movementType = MovementType.INFANTRY,
            melee = 2,
            ranged = 0,
            armor = -2,
            maxWounds = 1
        ),
        baseCostInDucats = 100,
        keywords = listOf("THE COURT", "DEMONIC", "ELITE", "STRONG"),
        specialSkills = listOf("Infernal Iron Armour", "Blood Magic", "Goetic Powers (1)")
    )

    fun hunter(): EliteMember = EliteMember(
        id = "court_hunter",
        customName = "Hunter of the Left-Hand Path",
        baseStats = ModelStats(
            movement = 6,
            movementType = MovementType.INFANTRY,
            melee = 1,
            ranged = 2,
            armor = 0,
            maxWounds = 1
        ),
        baseCostInDucats = 110,
        keywords = listOf("THE COURT", "DEMONIC", "ELITE", "INFILTRATOR"),
        specialSkills = listOf("Bow of Lethe", "Left-Hand Path", "Oracle Beast Cloak", "Shadow-Walker")
    )

    fun wretched(): TrooperMember = TrooperMember(
        id = "court_wretched",
        customName = "Wretched of the Court",
        baseStats = ModelStats(
            movement = 6,
            movementType = MovementType.INFANTRY,
            melee = -1,
            ranged = -1,
            armor = 0,
            maxWounds = 1
        ),
        baseCostInDucats = 20,
        keywords = listOf("THE COURT", "UNPROMOTABLE"),
        specialRules = listOf("Goaded Forth", "Law of Hell")
    )

    fun pitLocust(): TrooperMember = TrooperMember(
        id = "court_pit_locust",
        customName = "Pit Locust",
        baseStats = ModelStats(
            movement = 8,
            movementType = MovementType.FLYING,
            melee = 2,
            ranged = 0,
            armor = -2,
            maxWounds = 1
        ),
        baseCostInDucats = 90,
        keywords = listOf("THE COURT", "DEMONIC", "FEAR", "FLYING", "LIMITED POTENTIAL"),
        specialRules = listOf("Insectile Stingers")
    )

    fun yokeFiend(): TrooperMember = TrooperMember(
        id = "court_yoke_fiend",
        customName = "Yoke Fiend",
        baseStats = ModelStats(
            movement = 6,
            movementType = MovementType.INFANTRY,
            melee = 1,
            ranged = 0,
            armor = 0,
            maxWounds = 1
        ),
        baseCostInDucats = 35,
        keywords = listOf("THE COURT", "DEMONIC", "UNPROMOTABLE"),
        specialRules = listOf("Glory Lost")
    )

    fun desecratedSaint(): TrooperMember = TrooperMember(
        id = "court_desecrated_saint",
        customName = "Desecrated Saint",
        baseStats = ModelStats(
            movement = 6,
            movementType = MovementType.INFANTRY,
            melee = 2,
            ranged = 0,
            armor = -3,
            maxWounds = 1
        ),
        baseCostInDucats = 140,
        keywords = listOf(
            "THE COURT", "DEMONIC", "FEAR", "IGNORE OFF-HAND WEAPON", "LIMITED POTENTIAL",
            "NEGATE CUMBERSOME", "NEGATE DIFFICULT TERRAIN", "STRONG", "TOUGH"
        ),
        specialRules = listOf("Annihilator", "Aura of Sin", "Relentless", "Totemic")
    )
}