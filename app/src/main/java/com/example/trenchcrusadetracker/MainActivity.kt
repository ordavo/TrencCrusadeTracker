package com.example.trenchcrusadetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.trenchcrusadetracker.ui.screens.home.HomeScreen
import com.example.trenchcrusadetracker.ui.theme.DarkBackground
import com.example.trenchcrusadetracker.ui.theme.ParchmentText
import com.example.trenchcrusadetracker.ui.theme.PrimaryCrimson
import com.example.trenchcrusadetracker.ui.theme.TrenchCrusadeTrackerTheme

enum class Screen {
    HOME,
    WARBANDS,
    GAMES,
    RULES,
    CAMPAIGN,
    PROFILE
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrenchCrusadeTrackerTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkBackground,
        topBar = {
            if (currentScreen != Screen.HOME) {
                TopAppBar(
                    title = {
                        Text(
                            text = when (currentScreen) {
                                Screen.WARBANDS -> "Huestes de Guerra"
                                Screen.GAMES -> "Partidas en Vivo"
                                Screen.RULES -> "Reglamento y Lore"
                                Screen.CAMPAIGN -> "Modo Campaña"
                                Screen.PROFILE -> "Perfil de Jugador"
                                Screen.HOME -> ""
                            },
                            color = ParchmentText,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { currentScreen = Screen.HOME }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Regresar",
                                tint = ParchmentText
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PrimaryCrimson
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                Screen.HOME -> HomeScreen(
                    onNavigateToWarbands = { currentScreen = Screen.WARBANDS },
                    onNavigateToGames = { currentScreen = Screen.GAMES },
                    onNavigateToRules = { currentScreen = Screen.RULES },
                    onNavigateToCampaign = { currentScreen = Screen.CAMPAIGN },
                    onNavigateToProfile = { currentScreen = Screen.PROFILE }
                )
                Screen.WARBANDS -> PlaceholderScreen(
                    title = "Sección: Huestes de Guerra",
                    description = "Aquí podrás crear y gestionar tus bandas de guerra.",
                    onBack = { currentScreen = Screen.HOME }
                )
                Screen.GAMES -> PlaceholderScreen(
                    title = "Sección: Partidas en Vivo",
                    description = "Aquí podrás registrar los turnos, VP y Glorious Deaths.",
                    onBack = { currentScreen = Screen.HOME }
                )
                Screen.RULES -> PlaceholderScreen(
                    title = "Sección: Reglamento y Lore",
                    description = "Aquí podrás consultar las reglas oficiales y tablas.",
                    onBack = { currentScreen = Screen.HOME }
                )
                Screen.CAMPAIGN -> PlaceholderScreen(
                    title = "Sección: Modo Campaña",
                    description = "Aquí podrás administrar tus campañas y fases.",
                    onBack = { currentScreen = Screen.HOME }
                )
                Screen.PROFILE -> PlaceholderScreen(
                    title = "Sección: Perfil de Jugador",
                    description = "Aquí podrás editar tus datos y preferencias de comandante.",
                    onBack = { currentScreen = Screen.HOME }
                )
            }
        }
    }
}

@Composable
fun PlaceholderScreen(
    title: String,
    description: String,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = PrimaryCrimson,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = ParchmentText
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryCrimson,
                    contentColor = ParchmentText
                )
            ) {
                Text(text = "Volver al Menú Principal")
            }
        }
    }
}
