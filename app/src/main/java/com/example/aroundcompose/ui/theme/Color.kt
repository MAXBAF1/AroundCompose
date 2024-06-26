package com.example.aroundcompose.ui.theme

import androidx.compose.ui.graphics.Color

internal val baseLightPalette = JetAroundColors(
    primary = Color(0xFF9E9FAC),
    primaryBackground = Color(0xFFFCFCFC),
    darkGray = Color(0xFF5C5C5C),
    mapBtnBg = Color(1f, 1f, 1f, 1f),
    lightTint = Color(0xFFBDBDBD),
    searchHint = Color(0xFF929292),
    textFieldHint = Color(0xFFB4B4B4),
    notActiveColor = Color(0xFF9E9FAC),
    textColor = Color(0xFF1D1D1D),
    titleColor = Color(0xFF1E1E1E),
    secondaryBackground = Color(0xFFFFFFFF),
    blue = Color(0xFF555EF6),
    gray = Color(0xFFD9D9D9),
    purple = Color(0xFFB26BE9),
    yellow = Color(0xFFFEB700),
    lightBlue = Color(0xFF88DDFF),
    orange = Color(0xFFFF7629),
    errorColor = Color(0xFFF54953),
    eventCardText = Color(0xFF828282),
    veryLightGray = Color(0xFFE8E8E8),
    informationText = Color(0xFF737373),
    backgroundColorLightBlueTeam = Color(0xFF2E596A),
    backgroundColorYellowTeam = Color(0xFF5F4E26),
    backgroundColorPurpleTeam = Color(0xFF49345A),
    backgroundColorBlueTeam = Color(0xFF2D2F54),
    userCard = Color(0xFFF2F4F3),
    imageColor = Color(0xFF59CCF9),
)

internal val baseDarkPalette = JetAroundColors(
    primary = Color(0xFF9E9FAC),
    primaryBackground = Color(0xFFFFFFFF),
    lightTint = Color(0xFFBDBDBD),
    mapBtnBg = Color(1f, 1f, 1f, 1f),
    darkGray = Color(0xFF5C5C5C),
    searchHint = Color(0xFF929292),
    textFieldHint = Color(0xFFB4B4B4),
    notActiveColor = Color(0xFF9E9FAC),
    textColor = Color(0xFF1D1D1D),
    titleColor = Color(0xFF1E1E1E),
    secondaryBackground = Color(0xFF000000),
    gray = Color(0xFFD9D9D9),
    blue = Color(0xFF555EF6),
    purple = Color(0xFFB26BE9),
    yellow = Color(0xFFFEB700),
    lightBlue = Color(0xFF88DDFF),
    orange = Color(0xFFFF7629),
    errorColor = Color(0xFFF54953),
    eventCardText = Color(0xFF828282),
    veryLightGray = Color(0xFFE8E8E8),
    informationText = Color(0xFF737373),
    backgroundColorLightBlueTeam = Color(0xFF2E596A),
    backgroundColorYellowTeam = Color(0xFF5F4E26),
    backgroundColorPurpleTeam = Color(0xFF49345A),
    backgroundColorBlueTeam = Color(0xFF2D2F54),
    userCard = Color(0xFFF2F4F3),
    imageColor = Color(0xFF59CCF9),
)

val blueLightPalette = baseLightPalette.copy(primary = baseLightPalette.blue)
val blueDarkPalette = baseDarkPalette.copy(primary = baseDarkPalette.blue)

val purpleLightPalette = baseLightPalette.copy(primary = baseLightPalette.purple)
val purpleDarkPalette = baseDarkPalette.copy(primary = baseDarkPalette.purple)

val yellowLightPalette = baseLightPalette.copy(primary = baseLightPalette.yellow)
val yellowDarkPalette = baseDarkPalette.copy(primary = baseDarkPalette.yellow)

val lightBlueLightPalette = baseLightPalette.copy(primary = baseLightPalette.lightBlue)
val lightBlueDarkPalette = baseDarkPalette.copy(primary = baseDarkPalette.lightBlue)