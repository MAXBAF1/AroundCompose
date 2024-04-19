package com.example.aroundcompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class JetAroundColors(
    val primaryBackground: Color,
    val lightTint: Color,
    val mapBtnBg: Color,
    val mapElements: Color,
    val searchHint: Color,
    val textFieldHint: Color,
    val onFocusedColor: Color,
    val notActiveColor: Color,
    val textColor: Color,
    val textColorInverse: Color,
    val blue: Color,
    val gray: Color,
    val purple: Color,
    val yellow: Color,
    val lightBlue: Color,
    val orange: Color,
    val errorColor: Color,
    val primary: Color,
    val eventCardText: Color
)

data class JetAroundTypography(
    val appName: TextStyle,
    val headingLogin: TextStyle,
    val heading: TextStyle,
    val menuItem: TextStyle,
    val coin: TextStyle,
    val search: TextStyle,
    val textField: TextStyle,
    val textBtn: TextStyle,
    val textRegistration: TextStyle,
    val informationText: TextStyle,
    val medium: TextStyle,
    val scaleBar: TextStyle,
    val title: TextStyle,
    val eventCardTitle: TextStyle,
    val eventCardPlaceAndBtn: TextStyle,
    val eventCardKm: TextStyle
)

data class JetAroundShape(
    val textFieldShape: RoundedCornerShape,
    val mapElementsShape: RoundedCornerShape,
    val teamShape: RoundedCornerShape,
    val maxRoundedCornerShape: RoundedCornerShape,
)

data class JetAroundShadow(
    val mapElementsShadow: Dp,
    val loginUsingShadow: Dp
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