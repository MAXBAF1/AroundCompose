package com.example.aroundcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

@Composable
internal fun AroundComposeTheme(
    style: JetAroundStyle = JetAroundStyle.Base,
    corners: JetAroundCorners = JetAroundCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                JetAroundStyle.Base -> baseDarkPalette
            }
        }

        false -> {
            when (style) {
                JetAroundStyle.Base -> baseLightPalette
            }
        }
    }

    val shapes = JetAroundShape(
        padding = 16.dp, cornersStyle = when (corners) {
            JetAroundCorners.Flat -> RoundedCornerShape(0.dp)
            JetAroundCorners.Rounded -> RoundedCornerShape(8.dp)
        }
    )

    CompositionLocalProvider(
        LocalJetAroundColors provides colors,
        LocalJetAroundTypography provides typography,
        LocalJetAroundShape provides shapes,
        content = content
    )
}