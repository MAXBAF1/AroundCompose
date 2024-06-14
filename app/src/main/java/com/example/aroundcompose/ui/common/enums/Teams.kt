package com.example.aroundcompose.ui.common.enums

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.aroundcompose.ui.theme.JetAroundTheme

enum class Teams(val value: Int) {
    NONE(-1), BLUE(1), PURPLE(2), YELLOW(3), LIGHT_BLUE(4);

    companion object {
        fun getById(id: Int): Teams {
            return when (id) {
                1 -> BLUE
                2 -> PURPLE
                3 -> YELLOW
                4 -> LIGHT_BLUE
                else -> NONE
            }
        }
    }
}

@Composable
fun Teams.getColor(): Color {
    return when (this) {
        Teams.NONE, Teams.BLUE -> JetAroundTheme.colors.blue
        Teams.PURPLE -> JetAroundTheme.colors.purple
        Teams.YELLOW -> JetAroundTheme.colors.yellow
        Teams.LIGHT_BLUE -> JetAroundTheme.colors.lightBlue
    }
}