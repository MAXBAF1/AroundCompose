package com.example.aroundcompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class JetAroundColors(
    val primaryBackground: Color,
    val lightTint: Color,
    val mapBtnBg: Color,
    val darkGray: Color,
    val searchHint: Color,
    val textFieldHint: Color,
    val notActiveColor: Color,
    val textColor: Color,
    val titleColor: Color,
    val secondaryBackground: Color,
    val blue: Color,
    val gray: Color,
    val purple: Color,
    val yellow: Color,
    val lightBlue: Color,
    val orange: Color,
    val errorColor: Color,
    val primary: Color,
    val eventCardText: Color,
    val veryLightGray: Color,
    val informationText: Color,
    val backgroundColorLightBlueTeam: Color,
    val backgroundColorYellowTeam : Color,
    val backgroundColorPurpleTeam: Color,
    val backgroundColorBlueTeam: Color,
    val userCard: Color,
    val imageColor: Color,
)

data class JetAroundTypography(
    val appName: TextStyle,
    val headingLogin: TextStyle,
    val bold24: TextStyle,
    val bigMedium: TextStyle,
    val coin: TextStyle,
    val percent: TextStyle,
    val fourteenMedium: TextStyle,
    val textBtn: TextStyle,
    val textRegistration: TextStyle,
    val smallMedium: TextStyle,
    val medium16: TextStyle,
    val mediumRegular: TextStyle,
    val bold16: TextStyle,
    val semiBold16: TextStyle,
    val mediumBold: TextStyle,
    val regular14: TextStyle,
    val title: TextStyle,
    val eventCardTitle: TextStyle,
    val eventCardPlaceAndBtn: TextStyle,
    val semiBold12: TextStyle,
    val levelInformation: TextStyle,
)

data class JetAroundShape(
    val textFieldShape: RoundedCornerShape,
    val mapElementsShape: RoundedCornerShape,
    val teamShape: RoundedCornerShape,
    val teamsStatisticsShape: RoundedCornerShape,
    val maxRoundedCornerShape: RoundedCornerShape,
    val friendShape: RoundedCornerShape,
    val skillShape: RoundedCornerShape,
    val upgradeShape: RoundedCornerShape,
    val buttonListShape: RoundedCornerShape,
)

data class JetAroundShadow(
    val mapElementsShadow: Dp,
    val loginUsingShadow: Dp,
)

data class JetAroundMargin(
    val mainMargin: Dp,
    val mapScreenMargin: Dp,
)

enum class JetAroundStyle {
    Base, Blue, Purple, Yellow, LightBlue
}

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