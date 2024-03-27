package com.example.aroundcompose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.aroundcompose.R

val typography = JetAroundTypography(
    appName = TextStyle(fontSize = 64.sp, fontFamily = FontFamily(Font(R.font.involve_regular))),
    heading = TextStyle(fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
    coin = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    medium = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_medium))),
    scaleBar = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.involve_semi_bold))),
    title = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.involve_bold))),
)