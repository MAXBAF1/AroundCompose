package com.example.aroundcompose.ui.screens.settings.models

import com.example.aroundcompose.R

enum class ThemeTabs(val text: Int? = null, val icon: Int? = null) {
    Auto(text = R.string.auto),
    Sun(icon = R.drawable.ic_sun),
    Moon(icon = R.drawable.ic_moon)
}
