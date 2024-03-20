package com.example.aroundcompose.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class JetAroundColors(
    val primaryBackground: Color,
    val mapSearchInactive: Color,
    val mapBtnBg: Color,
    val mapElements: Color,
    val searchHint: Color,
)

data class JetAroundTypography(
    val appName: TextStyle,
    val heading: TextStyle,
    val coin: TextStyle,
    val search: TextStyle,
)

data class JetAroundShape(
    val padding: Dp,
    val cornersStyle: Shape
)

object JetAroundTheme {
    internal val colors: JetAroundColors
        @Composable get() = LocalJetAroundColors.current

    internal val typography: JetAroundTypography
        @Composable get() = LocalJetAroundTypography.current

    internal val shapes: JetAroundShape
        @Composable get() = LocalJetAroundShape.current
}

enum class JetAroundStyle {
    Base
}

enum class JetAroundCorners {
    Flat, Rounded
}

internal val LocalJetAroundColors = staticCompositionLocalOf<JetAroundColors> {
    error("No colors provided")
}

internal val LocalJetAroundTypography = staticCompositionLocalOf<JetAroundTypography> {
    error("No font provided")
}

internal val LocalJetAroundShape = staticCompositionLocalOf<JetAroundShape> {
    error("No shapes provided")
}