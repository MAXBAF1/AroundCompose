package com.example.aroundcompose.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
internal fun AroundComposeTheme(
    style: JetAroundStyle = JetAroundStyle.Base,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
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

    CompositionLocalProvider(
        LocalJetAroundColors provides colors,
        LocalJetAroundTypography provides typography,
        LocalJetAroundShape provides shape,
        LocalJetAroundShadow provides shadow,
        LocalJetAroundMargin provides margin,
        content = content
    )
}