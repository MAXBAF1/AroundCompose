package com.example.aroundcompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class JetAroundColors(
    val primaryBackground: Color,
    val mapSearchInactive: Color,
    val mapBtnBg: Color,
    val mapElements: Color,
    val searchHint: Color,
    val textColor: Color,
    val primary: Color
)

data class JetAroundTypography(
    val appName: TextStyle,
    val heading: TextStyle,
    val coin: TextStyle,
    val medium: TextStyle,
    val scaleBar: TextStyle,
    val title: TextStyle
)

data class JetAroundShape(
    val mapElementsShape: RoundedCornerShape,
    val maxRoundedCornerShape: RoundedCornerShape
)

data class JetAroundShadow(
    val mapElementsShadow: Dp,
)

data class JetAroundMargin(
    val mainMargin: Dp,
    val mapScreenMargin: Dp,
)

object JetAroundTheme {
    internal val colors: JetAroundColors
        @Composable get() = LocalJetAroundColors.current

    internal val typography: JetAroundTypography
        @Composable get() = LocalJetAroundTypography.current

    internal val shapes: JetAroundShape
        @Composable get() = LocalJetAroundShape.current

    internal val shadows: JetAroundShadow
        @Composable get() = LocalJetAroundShadow.current

    internal val margins: JetAroundMargin
        @Composable get() = LocalJetAroundMargin.current
}

enum class JetAroundStyle {
    Base
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

internal val LocalJetAroundShadow = staticCompositionLocalOf<JetAroundShadow> {
    error("No shadows provided")
}

internal val LocalJetAroundMargin = staticCompositionLocalOf<JetAroundMargin> {
    error("No margins provided")
}