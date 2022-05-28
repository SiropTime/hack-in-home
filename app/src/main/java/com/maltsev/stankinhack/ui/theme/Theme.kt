package com.maltsev.stankinhack.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = PrimaryStankin,
    primaryVariant = PrimaryStakninVariant,
    secondary = SecondaryStankin,
    secondaryVariant = SecondaryStankinVariant,
    onSecondary = Color.White
)

private val LightColorPalette = lightColors(
    primary = PrimaryStankin,
    primaryVariant = PrimaryStakninVariant,
    secondary = SecondaryStankin,
    secondaryVariant = SecondaryStankinVariant,
    onSecondary = Color.White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,

    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun StankinHackTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()

    val colors: Colors
    if (darkTheme) {
        colors = DarkColorPalette
        systemUiController.setSystemBarsColor(
            color = DarkColorPalette.background
        )
    } else {
        colors = LightColorPalette
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}