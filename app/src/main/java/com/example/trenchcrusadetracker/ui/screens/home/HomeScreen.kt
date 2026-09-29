package com.example.trenchcrusadetracker.ui.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trenchcrusadetracker.ui.theme.GoldAccent
import com.example.trenchcrusadetracker.ui.theme.ParchmentText
import com.example.trenchcrusadetracker.ui.theme.PrimaryCrimson
import com.example.trenchcrusadetracker.ui.theme.SurfaceDark

data class HomeOption(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector
)

/**
 * Pantalla principal de entrada que permite navegar a las 5 secciones principales:
 * Warbands, Juegos, Reglas, Campaña y Perfil.
 */
@Composable
fun HomeScreen(
    onNavigateToWarbands: () -> Unit = {},
    onNavigateToGames: () -> Unit = {},
    onNavigateToRules: () -> Unit = {},
    onNavigateToCampaign: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val options = listOf(
        HomeOption(
            id = "warbands",
            title = "Huestes de Guerra (Warbands)",
            description = "Crea y gestiona tus bandas de guerra, tropas y equipamiento.",
            icon = Icons.Default.Shield
        ),
        HomeOption(
            id = "games",
            title = "Partidas en Vivo (Juegos)",
            description = "Registro de turnos, puntos de victoria y Glorious Deaths.",
            icon = Icons.Default.PlayArrow
        ),
        HomeOption(
            id = "rules",
            title = "Reglamento y Lore (Reglas)",
            description = "Consulta las reglas oficiales, facciones y tablas del juego.",
            icon = Icons.Default.Book
        ),
        HomeOption(
            id = "campaign",
            title = "Modo Campaña",
            description = "Gestiona fases de Cuartel y Batalla, y tabla de clasificación.",
            icon = Icons.Default.Flag
        ),
        HomeOption(
            id = "profile",
            title = "Perfil de Jugador",
            description = "Configura tu perfil de comandante y preferencias.",
            icon = Icons.Default.Person
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Encabezado Principal
            Text(
                text = "TRENCH CRUSADE",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                color = PrimaryCrimson
            )
            Text(
                text = "TRACKER OF THE FAITHFUL AND THE DAMNED",
                style = MaterialTheme.typography.labelMedium,
                color = GoldAccent
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Lista de Opciones de Navegación
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(options) { option ->
                    HomeOptionCard(
                        option = option,
                        onClick = {
                            when (option.id) {
                                "warbands" -> onNavigateToWarbands()
                                "games" -> onNavigateToGames()
                                "rules" -> onNavigateToRules()
                                "campaign" -> onNavigateToCampaign()
                                "profile" -> onNavigateToProfile()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeOptionCard(
    option: HomeOption,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, PrimaryCrimson.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = SurfaceDark
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = option.icon,
                contentDescription = option.title,
                tint = PrimaryCrimson,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = option.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = ParchmentText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = option.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = ParchmentText.copy(alpha = 0.8f)
                )
            }
        }
    }
}
