package com.michael.trocellier.pokefit.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// 1. ========= PALETA PARA MODO OSCURO =========
// Aquí defines qué colores usar cuando el teléfono está en modo oscuro.
// Usas los colores que creaste en el archivo Color.kt
private val DarkColorScheme = darkColorScheme(
    primary = ButtonGray,       // Color principal para elementos interactivos
    secondary = PurpleGradient, // Color secundario
    tertiary = TextDark,        // Color terciario
    background = DarkBlue,      // Color de fondo de las pantallas
    surface = DarkBlue,         // Color de la superficie de "tarjetas", "menus", etc.
    onPrimary = TextDark,       // Color del texto que va SOBRE un fondo primario
    onBackground = TextWhite,   // Color del texto que va SOBRE un fondo normal
    onSurface = TextWhite       // Color del texto que va SOBRE una superficie
    // ... puedes definir más colores
)

// 2. ========= PALETA PARA MODO CLARO =========
// Lo mismo, pero para cuando el teléfono está en modo claro.
// Para este ejemplo, podemos usar la misma paleta si tu app solo será oscura.
private val LightColorScheme = lightColorScheme(
    primary = ButtonGray,
    secondary = PurpleGradient,
    tertiary = TextDark,
    background = DarkBlue,
    surface = DarkBlue,
    onPrimary = TextDark,
    onBackground = TextWhite,
    onSurface = TextWhite
)


@Composable
fun PokeFitTheme(
    // Este parámetro detecta automáticamente si el sistema está en modo oscuro.
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Aquí es donde se pasará tu propia UI (ej. PokeFitSurveyScreen).
    content: @Composable () -> Unit
) {
    // Elige la paleta de colores correcta (clara u oscura) basada en el sistema.
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Este bloque de código se asegura de que la barra de estado de Android (arriba)
    // coincida con los colores de tu tema.
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    // 4. ========= EL PROVEEDOR DE ESTILOS =========
    // MaterialTheme es el componente que realmente "sirve" o "provee" los colores
    // y la tipografía a todos los componentes hijos que están en `content`.
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // 'Typography' viene del archivo Type.kt
        content = content
    )
}