package com.example.aroundcompose.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme

class MenuScreen {
    @Composable
    fun Create() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            HexagonButton(
                text = stringResource(id = R.string.statistic),
                icon = painterResource(id = R.drawable.ic_statistic),
                color = JetAroundTheme.colors.blue,
                size = 208.dp
            )

        }
    }

    @Composable
    private fun HexagonButton(
        text: String,
        icon: Painter,
        color: Color,
        size: Dp,
        modifier: Modifier = Modifier,
    ) {
        Box(
            modifier = modifier
                .clip(JetAroundTheme.shapes.hexagonShape)
                .size(size)
                .clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = {})
                .background(color)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = "statistic icon",
                    tint = JetAroundTheme.colors.textColorInverse
                )
                Text(
                    text = text,
                    style = JetAroundTheme.typography.menuItem,
                    color = JetAroundTheme.colors.textColorInverse,
                )
            }
        }
    }
}