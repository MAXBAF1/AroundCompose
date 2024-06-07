package com.example.aroundcompose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.aroundcompose.R

val typography = JetAroundTypography(
    appName = TextStyle(fontSize = 64.sp, fontFamily = FontFamily(Font(R.font.involve_regular))),
    headingLogin = TextStyle(fontSize = 32.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    bold24 = TextStyle(fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    bigMedium = TextStyle(fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    title = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    coin = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    percent = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    bold16 = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    semiBold16 = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    sixteenMedium = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    mediumRegular = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_regular))),
    mediumBold = TextStyle(fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    regular14 = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.involve_regular))),
    fourteenMedium = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    textBtn = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    textRegistration = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    smallMedium = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    eventCardTitle = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    semiBold12 = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    eventCardPlaceAndBtn = TextStyle(fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    levelInformation = TextStyle(fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
)