package com.dotcross_app.dotcross.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
//    Default theme colours:
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
    surface = WhiteDotCross
)

private val LightColorPalette = lightColors(
//    Default theme colours:
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200,
    surface = WhiteDotCross,
    onSurface = BlackDotCross

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun DotCrossTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}