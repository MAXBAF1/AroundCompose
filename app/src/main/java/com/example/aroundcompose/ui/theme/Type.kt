package com.example.aroundcompose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.aroundcompose.R

val typography = JetAroundTypography(
    appName = TextStyle(fontSize = 64.sp, fontFamily = FontFamily(Font(R.font.involve_regular))),
    headingLogin = TextStyle(fontSize = 32.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    heading = TextStyle(fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    menuItem = TextStyle(fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    coin = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    search = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    textField = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    textBtn = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    textRegistration = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    informationText = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    medium = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    scaleBar = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    title = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    eventCardTitle = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    eventCardPlaceAndBtn = TextStyle(fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    eventCardKm = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
)