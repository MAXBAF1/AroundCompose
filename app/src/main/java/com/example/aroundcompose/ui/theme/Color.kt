package com.example.aroundcompose.ui.theme

import androidx.compose.ui.graphics.Color
import com.mapbox.maps.extension.style.expressions.dsl.generated.rgba

internal val baseLightPalette = JetAroundColors(
    primaryBackground = Color(0xFFFFFFFF),
    primaryText = Color(0xFF3D454C),
    secondaryBackground = Color(0xFFF3F4F5),
    secondaryText = Color(0xCC7A8A99),
    tintColor = Color(1f, 1f, 1f, 0.9f),
    controlColor = Color(0xFF7A8A99),
    errorColor = Color(0xFFFF3377)
)

internal val baseDarkPalette = JetAroundColors(
    primaryBackground = Color(0xFF23282D),
    primaryText = Color(0xFFF2F4F5),
    secondaryBackground = Color(0xFF191E23),
    secondaryText = Color(0xCC7A8A99),
    tintColor = Color.Magenta,
    controlColor = Color(0xFF7A8A99),
    errorColor = Color(0xFFFF6699)
)