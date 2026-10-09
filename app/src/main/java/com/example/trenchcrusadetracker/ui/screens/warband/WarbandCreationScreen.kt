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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.trenchcrusadetracker.domain.faction.providers.NewAntiochArmoryProvider
import com.example.trenchcrusadetracker.domain.faction.providers.NewAntiochFactionProvider
import com.example.trenchcrusadetracker.domain.model.equipment.Equipment
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
    val factionProvider = remember { NewAntiochFactionProvider() }
    val masterEquipmentRepository = remember { EquipmentRepositoryImpl() }
    val armoryProvider = remember { NewAntiochArmoryProvider(masterEquipmentRepository) }

    var warbandName by remember { mutableStateOf("") }
    var playerName by remember { mutableStateOf("") }
    var members by remember { mutableStateOf(listOf<WarbandMember>()) }
    var selectedTabRole by remember { mutableIntStateOf(0) }

    // Diálogos
    var showAddUnitDialog by remember { mutableStateOf(false) }
    var memberToEquip by remember { mutableStateOf<WarbandMember?>(null) }

    val roles = listOf(
        MemberRole.LEADER to "Líder (Capitán)",
        MemberRole.ELITE to "Élites (Máx 6)",
        MemberRole.TROOPER to "Tropas",
        MemberRole.MERCENARY to "Mercenarios"
    )

    val currentFaction = factionProvider.faction
    val totalCostDucats = members.sumOf { it.calculateTotalCostInDucats() }
    val totalCostGlory = members.sumOf { it.calculateTotalCostInGloryPoints() }
    val remainingDucats = currentFaction.defaultStartingDucats - totalCostDucats

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Encabezado de la Facción
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldAccent, RoundedCornerShape(8.dp)),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "FACCIÓN: ${currentFaction.name.uppercase()}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryCrimson
                    )
                    Text(
                        text = "Facción activa para depuración inicial",
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldAccent
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
                    text = "Ducados: $totalCostDucats / ${currentFaction.defaultStartingDucats} (Restante: $remainingDucats)",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (remainingDucats >= 0) ParchmentText else PrimaryCrimson
                )
                Text(
                    text = "Gloria: $totalCostGlory pts",
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
                        onEquipClick = { memberToEquip = member },
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

    // Diálogo para Equipar Arsenal del Mercado/Armería
    memberToEquip?.let { targetMember ->
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
                memberToEquip = null
            },
            onDismiss = { memberToEquip = null }
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

@Composable
private fun EquipArsenalDialog(
    member: WarbandMember,
    availableEquipment: List<Equipment>,
    onEquipmentSelected: (Equipment) -> Unit,
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
                    text = "Arsenal para ${member.customName}",
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryCrimson,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(300.dp)
                ) {
                    items(availableEquipment) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, GoldAccent.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                            colors = CardDefaults.cardColors(containerColor = DarkBackground),
                            onClick = { onEquipmentSelected(item) }
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(item.name, color = ParchmentText, fontWeight = FontWeight.Bold)
                                    val costText = if (item.cost.currency == com.example.trenchcrusadetracker.domain.model.equipment.CurrencyType.DUCATS) {
                                        "${item.cost.amount} Ducados"
                                    } else {
                                        "${item.cost.amount} Pts Gloria"
                                    }
                                    Text(costText, color = GoldAccent, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(item.description, color = ParchmentText.copy(alpha = 0.8f), fontSize = 11.sp)
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
