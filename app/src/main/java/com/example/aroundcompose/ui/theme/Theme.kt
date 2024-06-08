package com.example.aroundcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
internal fun AroundComposeTheme(
    style: JetAroundStyle = JetAroundStyle.Blue,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                JetAroundStyle.Base -> baseDarkPalette
                JetAroundStyle.Blue -> blueDarkPalette
                JetAroundStyle.Purple -> purpleDarkPalette
                JetAroundStyle.Yellow -> yellowDarkPalette
                JetAroundStyle.LightBlue -> lightBlueDarkPalette
            }
        }

        false -> {
            when (style) {
                JetAroundStyle.Base -> baseLightPalette
                JetAroundStyle.Blue -> blueLightPalette
                JetAroundStyle.Purple -> purpleLightPalette
                JetAroundStyle.Yellow -> yellowLightPalette
                JetAroundStyle.LightBlue -> lightBlueLightPalette
            }
        }
    }

    CompositionLocalProvider(
        LocalJetAroundColors provides colors,
        LocalJetAroundTypography provides typography,
        LocalJetAroundShape provides shape,
        LocalJetAroundShadow provides shadow,
        LocalJetAroundMargin provides margin,
        content = content
    )
}