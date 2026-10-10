package com.example.trenchcrusadetracker.ui.screens.warband

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.trenchcrusadetracker.domain.equipment.EquipmentRepositoryImpl
import com.example.trenchcrusadetracker.domain.equipment.providers.FactionArmoryRegistry
import com.example.trenchcrusadetracker.domain.faction.FactionRepositoryImpl
import com.example.trenchcrusadetracker.domain.model.equipment.BattlekitCategory
import com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
import com.example.trenchcrusadetracker.domain.model.faction.Faction
import com.example.trenchcrusadetracker.domain.model.faction.FactionRule
import com.example.trenchcrusadetracker.domain.model.warband.EliteMember
import com.example.trenchcrusadetracker.domain.model.warband.LeaderMember
import com.example.trenchcrusadetracker.domain.model.warband.MemberRole
import com.example.trenchcrusadetracker.domain.model.warband.TrooperMember
import com.example.trenchcrusadetracker.domain.model.warband.Warband
import com.example.trenchcrusadetracker.domain.model.warband.WarbandMember
import com.example.trenchcrusadetracker.ui.theme.DarkBackground
import com.example.trenchcrusadetracker.ui.theme.GoldAccent
import com.example.trenchcrusadetracker.ui.theme.ParchmentText
import com.example.trenchcrusadetracker.ui.theme.PrimaryCrimson
import com.example.trenchcrusadetracker.ui.theme.SurfaceDark

/**
 * Pantalla para la creación y edición de Warbands.
 * Inicialmente bloqueada a "Principality of New Antioch" para depuración.
 * Separa claramente los miembros en 4 pestañas de rol: Líder, Élites (máx 6), Tropas y Mercenarios.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarbandCreationScreen(
    onWarbandSaved: (Warband) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    val factionRepository = remember { FactionRepositoryImpl() }
    val masterEquipmentRepository = remember { EquipmentRepositoryImpl() }
    val factions: List<Faction> = factionRepository.getAllFactions()

    var selectedFactionId by remember { mutableStateOf(factions.first().id) }
    var selectedVariantId by remember { mutableStateOf<String?>(null) }
    val factionProvider = remember(selectedFactionId) {
        factionRepository.getProviderByFactionId(selectedFactionId)
            ?: error("No se encontró un proveedor para la facción '$selectedFactionId'")
    }
    val armoryProvider = remember(selectedFactionId) {
        FactionArmoryRegistry.forFaction(selectedFactionId, masterEquipmentRepository)
    }

    var warbandName by remember { mutableStateOf("") }
    var playerName by remember { mutableStateOf("") }
    var members by remember { mutableStateOf(listOf<WarbandMember>()) }
    var selectedTabRole by remember { mutableIntStateOf(0) }

    // Diálogos
    var showAddUnitDialog by remember { mutableStateOf(false) }
    var showFactionDialog by remember { mutableStateOf(false) }
    var variantPickerFaction by remember { mutableStateOf<Faction?>(null) }
    var pendingSelection by remember { mutableStateOf<FactionSelection?>(null) }
    var memberToEquipId by remember { mutableStateOf<String?>(null) }

    val roles = listOf(
        MemberRole.LEADER to "Líder (Capitán)",
        MemberRole.ELITE to "Élites (Máx 6)",
        MemberRole.TROOPER to "Tropas",
        MemberRole.MERCENARY to "Mercenarios"
    )

    val currentFaction = factionProvider.faction
    val activeVariant = currentFaction.variants.find { it.id == selectedVariantId }
    val startingDucats = activeVariant?.defaultStartingDucats ?: currentFaction.defaultStartingDucats
    val startingGlory = activeVariant?.defaultStartingGloryPoints ?: currentFaction.defaultStartingGloryPoints
    val totalCostDucats = members.sumOf { it.calculateTotalCostInDucats() }
    val totalCostGlory = members.sumOf { it.calculateTotalCostInGloryPoints() }
    val remainingDucats = startingDucats - totalCostDucats

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Encabezado de la Facción (seleccionable)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldAccent, RoundedCornerShape(8.dp)),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                onClick = { showFactionDialog = true }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "FACCIÓN: ${currentFaction.name.uppercase()}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryCrimson
                        )
                        if (activeVariant != null) {
                            Text(
                                text = "SUBFACCIÓN: ${activeVariant.name.uppercase()}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryCrimson.copy(alpha = 0.85f)
                            )
                        }
                        Text(
                            text = activeVariant?.description ?: currentFaction.description,
                            style = MaterialTheme.typography.labelSmall,
                            color = GoldAccent
                        )
                        val gloryLabel = if (startingGlory > 0) " · Gloria inicial: $startingGlory" else ""
                        Text(
                            text = "Ducados iniciales: $startingDucats$gloryLabel · Toca para cambiar",
                            style = MaterialTheme.typography.labelSmall,
                            color = ParchmentText.copy(alpha = 0.7f)
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Cambiar facción",
                        tint = GoldAccent
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Inputs de Nombre de Warband y Jugador
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = warbandName,
                    onValueChange = { warbandName = it },
                    label = { Text("Nombre de la Banda", color = ParchmentText) },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryCrimson,
                        unfocusedBorderColor = GoldAccent.copy(alpha = 0.5f),
                        focusedTextColor = ParchmentText,
                        unfocusedTextColor = ParchmentText
                    )
                )

                OutlinedTextField(
                    value = playerName,
                    onValueChange = { playerName = it },
                    label = { Text("Nombre del Jugador", color = ParchmentText) },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryCrimson,
                        unfocusedBorderColor = GoldAccent.copy(alpha = 0.5f),
                        focusedTextColor = ParchmentText,
                        unfocusedTextColor = ParchmentText
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Barra de Recursos (Ducados y Gloria)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceDark, RoundedCornerShape(8.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ducados: $totalCostDucats / $startingDucats (Restante: $remainingDucats)",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (remainingDucats >= 0) ParchmentText else PrimaryCrimson
                )
                Text(
                    text = if (startingGlory > 0) "Gloria: $totalCostGlory / $startingGlory pts" else "Gloria: $totalCostGlory pts",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = GoldAccent
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Pestañas por Rol (Líder, Élites, Tropas, Mercenarios)
            TabRow(
                selectedTabIndex = selectedTabRole,
                containerColor = SurfaceDark,
                contentColor = PrimaryCrimson,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabRole]),
                        color = PrimaryCrimson
                    )
                }
            ) {
                roles.forEachIndexed { index, (role, title) ->
                    val count = members.count { it.role == role }
                    Tab(
                        selected = selectedTabRole == index,
                        onClick = { selectedTabRole = index },
                        text = {
                            Text(
                                text = "$title ($count)",
                                color = if (selectedTabRole == index) PrimaryCrimson else ParchmentText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val currentRole = roles[selectedTabRole].first
            val filteredMembers = members.filter { it.role == currentRole }

            // Botón para agregar unidad del rol seleccionado
            Button(
                onClick = { showAddUnitDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryCrimson),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = ParchmentText)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Añadir ${roles[selectedTabRole].second}", color = ParchmentText)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Lista de miembros del rol seleccionado
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(filteredMembers) { member ->
                    MemberCard(
                        member = member,
                        onEquipClick = { memberToEquipId = member.id },
                        onRemoveClick = { members = members.filter { it.id != member.id } }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón Finalizar / Guardar
            Button(
                onClick = {
                    if (warbandName.isNotBlank() && playerName.isNotBlank()) {
                        val newWarband = Warband(
                            id = "wb_${System.currentTimeMillis()}",
                            name = warbandName,
                            playerId = playerName,
                            faction = currentFaction,
                            ducats = remainingDucats,
                            gloryPoints = totalCostGlory,
                            members = members
                        )
                        onWarbandSaved(newWarband)
                    }
                },
                enabled = warbandName.isNotBlank() && playerName.isNotBlank() && remainingDucats >= 0,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryCrimson),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Warband", color = ParchmentText, fontWeight = FontWeight.Bold)
            }
        }
    }

    // Diálogo para Agregar Unidad
    if (showAddUnitDialog) {
        AddUnitDialog(
            availableUnits = factionProvider.getAvailableUnits().filter { it.role == roles[selectedTabRole].first },
            onUnitSelected = { unitTemplate ->
                val newId = "m_${System.currentTimeMillis()}"
                val newMember = when (unitTemplate) {
                    is LeaderMember -> unitTemplate.copy(id = newId)
                    is EliteMember -> unitTemplate.copy(id = newId)
                    is TrooperMember -> unitTemplate.copy(id = newId)
                    else -> unitTemplate
                }
                members = members + newMember
                showAddUnitDialog = false
            },
            onDismiss = { showAddUnitDialog = false }
        )
    }

    // Selección de facción base. Al elegir una, se abre el selector de subfacción.
    if (showFactionDialog) {
        FactionSelectorDialog(
            factions = factions,
            selectedFactionId = selectedFactionId,
            onFactionSelected = { factionId ->
                showFactionDialog = false
                variantPickerFaction = factions.find { it.id == factionId }
            },
            onDismiss = { showFactionDialog = false }
        )
    }

    // Selección de subfacción (facción base o variante) para la facción elegida.
    variantPickerFaction?.let { faction ->
        VariantSelectorDialog(
            faction = faction,
            selectedVariantId = if (faction.id == selectedFactionId) selectedVariantId else null,
            onVariantSelected = { variantId ->
                variantPickerFaction = null
                if (faction.id != selectedFactionId || variantId != selectedVariantId) {
                    // Cambiar de facción o subfacción reemplaza las unidades actuales.
                    if (members.isEmpty()) {
                        selectedFactionId = faction.id
                        selectedVariantId = variantId
                        selectedTabRole = 0
                    } else {
                        pendingSelection = FactionSelection(faction.id, variantId)
                    }
                }
            },
            onDismiss = { variantPickerFaction = null }
        )
    }

    // Confirmación antes de cambiar de facción/subfacción y descartar las unidades actuales
    pendingSelection?.let { selection ->
        val targetFaction = factions.find { it.id == selection.factionId }
        val targetName = selection.variantId
            ?.let { vid -> targetFaction?.variants?.find { it.id == vid }?.name }
            ?: targetFaction?.name
            ?: selection.factionId
        ConfirmFactionChangeDialog(
            factionName = targetName,
            unitCount = members.size,
            onConfirm = {
                selectedFactionId = selection.factionId
                selectedVariantId = selection.variantId
                members = emptyList()
                selectedTabRole = 0
                pendingSelection = null
            },
            onDismiss = { pendingSelection = null }
        )
    }

    // Diálogo para Equipar Arsenal del Mercado/Armería, agrupado por secciones
    val targetMember = members.find { it.id == memberToEquipId }
    if (targetMember != null) {
        EquipArsenalDialog(
            member = targetMember,
            availableEquipment = armoryProvider.getAvailableEquipmentForRole(targetMember.role),
            onEquipmentSelected = { selectedEquipment ->
                val updatedBattlekit = targetMember.battlekit.addEquipment(selectedEquipment)
                val updatedMember = when (targetMember) {
                    is LeaderMember -> targetMember.copy(battlekit = updatedBattlekit)
                    is EliteMember -> targetMember.copy(battlekit = updatedBattlekit)
                    is TrooperMember -> targetMember.copy(battlekit = updatedBattlekit)
                    else -> targetMember
                }
                members = members.map { if (it.id == targetMember.id) updatedMember else it }
            },
            onDismiss = { memberToEquipId = null }
        )
    }
}

@Composable
private fun MemberCard(
    member: WarbandMember,
    onEquipClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, PrimaryCrimson.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = member.customName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = ParchmentText
                )
                Text(
                    text = "${member.calculateTotalCostInDucats()} Ducados",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GoldAccent,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Atributos Base
            val stats = member.baseStats
            Text(
                text = "MOV: ${stats.movement}\" | MELEE: +${stats.melee} | RANGED: +${stats.ranged} | ARMOR: ${stats.armor} | WOUNDS: ${stats.maxWounds}",
                style = MaterialTheme.typography.bodySmall,
                color = ParchmentText.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Equipamiento Equipado
            val equipList = member.battlekit.allEquipment()
            if (equipList.isNotEmpty()) {
                Text(
                    text = "Equipamiento: ${equipList.joinToString { it.name }}",
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldAccent
                )
            } else {
                Text(
                    text = "Sin equipamiento asignado",
                    style = MaterialTheme.typography.labelSmall,
                    color = ParchmentText.copy(alpha = 0.5f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onEquipClick) {
                    Icon(Icons.Default.Build, contentDescription = "Equipar Arsenal", tint = GoldAccent)
                }
                IconButton(onClick = onRemoveClick) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = PrimaryCrimson)
                }
            }
        }
    }
}

@Composable
private fun AddUnitDialog(
    availableUnits: List<WarbandMember>,
    onUnitSelected: (WarbandMember) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = SurfaceDark,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Seleccionar Unidad",
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryCrimson,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                if (availableUnits.isEmpty()) {
                    Text("No hay unidades disponibles para este rol.", color = ParchmentText)
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(availableUnits) { unit ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, PrimaryCrimson.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                                colors = CardDefaults.cardColors(containerColor = DarkBackground),
                                onClick = { onUnitSelected(unit) }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(unit.customName, color = ParchmentText, fontWeight = FontWeight.Bold)
                                        Text("${unit.baseCostInDucats} Ducados Base", color = GoldAccent, fontSize = 12.sp)
                                    }
                                    Icon(Icons.Default.Add, contentDescription = null, tint = PrimaryCrimson)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = onDismiss, modifier = Modifier.align(Alignment.End)) {
                    Text("Cancelar", color = ParchmentText)
                }
            }
        }
    }
}

/**
 * Diálogo para elegir la facción de la Warband. Lista todas las facciones registradas
 * en el repositorio y resalta la seleccionada actualmente.
 */
@Composable
private fun FactionSelectorDialog(
    factions: List<Faction>,
    selectedFactionId: String,
    onFactionSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = SurfaceDark,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Seleccionar Facción",
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryCrimson,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(400.dp)
                ) {
                    items(factions, key = { it.id }) { faction ->
                        val isSelected = faction.id == selectedFactionId
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) PrimaryCrimson else GoldAccent.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            colors = CardDefaults.cardColors(containerColor = DarkBackground),
                            onClick = { onFactionSelected(faction.id) }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = faction.name,
                                        color = ParchmentText,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = faction.description,
                                        color = ParchmentText.copy(alpha = 0.8f),
                                        fontSize = 11.sp
                                    )
                                    Text(
                                        text = "${faction.defaultStartingDucats} Ducados iniciales",
                                        color = GoldAccent,
                                        fontSize = 11.sp
                                    )
                                }
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Facción seleccionada",
                                        tint = PrimaryCrimson
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = onDismiss, modifier = Modifier.align(Alignment.End)) {
                    Text("Cancelar", color = ParchmentText)
                }
            }
        }
    }
}

/**
 * Diálogo para elegir la subfacción de una facción: la facción base (sin variante)
 * o una de sus variantes registradas en [Faction.variants].
 */
@Composable
private fun VariantSelectorDialog(
    faction: Faction,
    selectedVariantId: String?,
    onVariantSelected: (String?) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = SurfaceDark,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Subfacción de ${faction.name}",
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryCrimson,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Elige la facción base o una de sus variantes.",
                    style = MaterialTheme.typography.labelMedium,
                    color = ParchmentText.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(420.dp)
                ) {
                    item(key = "base") {
                        SubfactionOptionCard(
                            name = "${faction.name} (Base)",
                            description = faction.description,
                            ducats = faction.defaultStartingDucats,
                            glory = faction.defaultStartingGloryPoints,
                            rules = faction.specialRules,
                            isSelected = selectedVariantId == null,
                            onClick = { onVariantSelected(null) }
                        )
                    }
                    items(faction.variants, key = { it.id }) { variant ->
                        SubfactionOptionCard(
                            name = variant.name,
                            description = variant.description,
                            ducats = variant.defaultStartingDucats,
                            glory = variant.defaultStartingGloryPoints,
                            rules = variant.specialRules,
                            isSelected = variant.id == selectedVariantId,
                            onClick = { onVariantSelected(variant.id) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = onDismiss, modifier = Modifier.align(Alignment.End)) {
                    Text("Cancelar", color = ParchmentText)
                }
            }
        }
    }
}

/**
 * Tarjeta seleccionable que muestra una subfacción, sus recursos iniciales y reglas especiales.
 */
@Composable
private fun SubfactionOptionCard(
    name: String,
    description: String,
    ducats: Int,
    glory: Int,
    rules: List<FactionRule>,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) PrimaryCrimson else GoldAccent.copy(alpha = 0.3f),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = DarkBackground),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    color = ParchmentText,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                if (isSelected) {
                    Icon(Icons.Default.Check, contentDescription = "Seleccionada", tint = PrimaryCrimson)
                }
            }
            Text(description, color = ParchmentText.copy(alpha = 0.8f), fontSize = 11.sp)
            val gloryLabel = if (glory > 0) " · $glory Gloria" else ""
            Text("$ducats Ducados iniciales$gloryLabel", color = GoldAccent, fontSize = 11.sp)
            if (rules.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                rules.forEach { rule ->
                    Text(
                        text = "• ${rule.name}: ${rule.effectDescription}",
                        color = ParchmentText.copy(alpha = 0.7f),
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

/**
 * Diálogo de confirmación al cambiar de facción cuando ya hay unidades reclutadas.
 * Cada facción tiene sus propias unidades, límites y restricciones, por lo que las
 * unidades actuales deben descartarse antes de continuar.
 */
@Composable
private fun ConfirmFactionChangeDialog(
    factionName: String,
    unitCount: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = SurfaceDark,
        title = {
            Text(
                text = "Cambiar de facción",
                color = PrimaryCrimson,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = "Ya tienes $unitCount unidad(es) reclutada(s). Cada facción tiene " +
                    "sus propias unidades, límites y restricciones, por lo que al cambiar " +
                    "a \"$factionName\" se borrarán todas las unidades actuales. " +
                    "¿Deseas continuar?",
                color = ParchmentText
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Sí, cambiar", color = PrimaryCrimson)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = ParchmentText)
            }
        }
    )
}

/**
 * Diálogo que muestra el equipamiento del modelo agrupado por secciones del Battlekit
 * (Armas a Distancia, Granadas, Cuerpo a Cuerpo, Armaduras, Escudos, Equipo y Especiales).
 * Cada sección tiene un botón "+" para agregar piezas de esa categoría.
 */
@Composable
private fun EquipArsenalDialog(
    member: WarbandMember,
    availableEquipment: List<Equipment>,
    onEquipmentSelected: (Equipment) -> Unit,
    onDismiss: () -> Unit
) {
    var pickingCategory by remember { mutableStateOf<BattlekitCategory?>(null) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = SurfaceDark,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Equipamiento de ${member.customName}",
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryCrimson,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                val selectedCategory = pickingCategory
                if (selectedCategory == null) {
                    // Vista por secciones del Battlekit
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.height(360.dp)
                    ) {
                        BattlekitCategory.values().forEach { category ->
                            val equipped = member.battlekit.itemsInCategory(category)
                            item(key = "header_${category.name}") {
                                BattlekitSectionHeader(
                                    title = categoryLabel(category),
                                    canAdd = canAddToCategory(member, category),
                                    onAddClick = { pickingCategory = category }
                                )
                            }
                            if (equipped.isEmpty()) {
                                item(key = "empty_${category.name}") {
                                    Text(
                                        text = "Sin asignar",
                                        color = ParchmentText.copy(alpha = 0.4f),
                                        fontSize = 11.sp,
                                        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                    )
                                }
                            } else {
                                items(equipped, key = { "${category.name}_${it.id}" }) { item ->
                                    EquippedItemRow(item = item)
                                }
                            }
                            item(key = "divider_${category.name}") {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(GoldAccent.copy(alpha = 0.15f))
                                )
                            }
                        }
                    }
                } else {
                    // Vista de selección de la categoría elegida
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextButton(onClick = { pickingCategory = null }) {
                            Text("‹ Volver", color = GoldAccent)
                        }
                        Text(
                            text = categoryLabel(selectedCategory),
                            style = MaterialTheme.typography.titleMedium,
                            color = PrimaryCrimson,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    val options = availableEquipment.filter { it.category == selectedCategory }
                    if (options.isEmpty()) {
                        Text("No hay equipamiento disponible en esta sección.", color = ParchmentText)
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.height(320.dp)
                        ) {
                            items(options, key = { it.id }) { option ->
                                EquipmentOptionCard(
                                    item = option,
                                    onClick = { onEquipmentSelected(option) }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = onDismiss, modifier = Modifier.align(Alignment.End)) {
                    Text("Cerrar", color = ParchmentText)
                }
            }
        }
    }
}

/**
 * Encabezado de sección con el botón "+" para agregar equipamiento de la categoría.
 */
@Composable
private fun BattlekitSectionHeader(
    title: String,
    canAdd: Boolean,
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = PrimaryCrimson,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = onAddClick, enabled = canAdd) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar a $title",
                tint = if (canAdd) GoldAccent else ParchmentText.copy(alpha = 0.3f)
            )
        }
    }
}

/**
 * Fila que muestra una pieza de equipamiento ya asignada al modelo.
 */
@Composable
private fun EquippedItemRow(item: Equipment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(item.name, color = ParchmentText, fontSize = 13.sp)
        Text(
            text = itemCostLabel(item),
            color = GoldAccent,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Tarjeta seleccionable con la información de una pieza de equipamiento disponible.
 */
@Composable
private fun EquipmentOptionCard(
    item: Equipment,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, GoldAccent.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkBackground),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(item.name, color = ParchmentText, fontWeight = FontWeight.Bold)
                Text(
                    text = itemCostLabel(item),
                    color = GoldAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(item.description, color = ParchmentText.copy(alpha = 0.8f), fontSize = 11.sp)
            if (!item.stipulations.isEmpty) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.stipulations.display(),
                    color = GoldAccent.copy(alpha = 0.85f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * Indica si la sección admite agregar más piezas.
 * Armaduras, Granadas y Escudos están limitadas a una pieza por el reglamento.
 */
private fun canAddToCategory(member: WarbandMember, category: BattlekitCategory): Boolean {
    val singleSlot = category == BattlekitCategory.ARMOUR ||
        category == BattlekitCategory.GRENADES ||
        category == BattlekitCategory.SHIELDS
    return !(singleSlot && member.battlekit.itemsInCategory(category).isNotEmpty())
}

/**
 * Etiqueta en español de cada categoría del Battlekit.
 */
private fun categoryLabel(category: BattlekitCategory): String = when (category) {
    BattlekitCategory.RANGED_WEAPONS -> "Armas a Distancia"
    BattlekitCategory.GRENADES -> "Granadas"
    BattlekitCategory.MELEE_WEAPONS -> "Armas Cuerpo a Cuerpo"
    BattlekitCategory.ARMOUR -> "Armaduras"
    BattlekitCategory.SHIELDS -> "Escudos"
    BattlekitCategory.EQUIPMENT -> "Equipo"
    BattlekitCategory.SPECIAL -> "Objetos Especiales"
}

/**
 * Texto de costo de una pieza de equipamiento según su moneda.
 */
private fun itemCostLabel(item: Equipment): String =
    if (item.cost.currency == CurrencyType.DUCATS) {
        "${item.cost.amount} Ducados"
    } else {
        "${item.cost.amount} Pts Gloria"
    }

/**
 * Selección pendiente de facción y subfacción a aplicar tras la confirmación del usuario.
 * Un [variantId] nulo representa la facción base (sin variante).
 */
private data class FactionSelection(
    val factionId: String,
    val variantId: String?
)
