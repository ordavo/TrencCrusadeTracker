package com.example.trenchcrusadetracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryCrimson,
    onPrimary = ParchmentText,
    secondary = GoldAccent,
    onSecondary = DarkBackground,
    background = DarkBackground,
    onBackground = ParchmentText,
    surface = SurfaceDark,
    onSurface = ParchmentText,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = ParchmentText
)

@Composable
fun TrenchCrusadeTrackerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
