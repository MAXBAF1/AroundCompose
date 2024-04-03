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
    val textFieldHint: Color,
    val onFocusedColor: Color,
    val notActiveColor: Color,
    val textColor: Color,
    val blue: Color,
    val purple: Color,
    val yellow: Color,
    val lightBlue: Color,
    val errorColor: Color
)

data class JetAroundTypography(
    val appName: TextStyle,
    val heading: TextStyle,
    val headingLogin: TextStyle,
    val coin: TextStyle,
    val search: TextStyle,
    val textField: TextStyle,
    val textBtn: TextStyle,
    val textRegistration: TextStyle,
    val informationText: TextStyle,
    val scaleBar: TextStyle,
)

data class JetAroundShape(
    val textFieldShape: RoundedCornerShape,
    val mapElementsShape: RoundedCornerShape,
    val teamShape: RoundedCornerShape,
    val maxRoundedCornerShape: RoundedCornerShape
)

data class JetAroundShadow(
    val mapElementsShadow: Dp,
    val loginUsingShadow: Dp
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

internal val LocalJetAroundShadow = staticCompositionLocalOf<JetAroundShadow> {
    error("No shapes provided")
}