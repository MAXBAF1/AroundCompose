package com.example.aroundcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography = JetAroundTypography(
    heading = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
    body = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
    toolbar = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    caption = TextStyle(fontSize = 12.sp)
)