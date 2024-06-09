package com.example.aroundcompose.ui.screens.menu

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.RoundedPolygonShape

class MenuScreen(
    private val toSettingsScreen: () -> Unit,
    private val toAccountScreen: () -> Unit,
    private val toEventsScreen: () -> Unit,
    private val toMoneysScreen: () -> Unit,
    private val toStatisticScreen: () -> Unit,
    private val toFriendsScreen: () -> Unit,
) {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Create() {
        val main = 370.dp
        Surface(color = JetAroundTheme.colors.primaryBackground) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                HexagonalButton(
                    onClick = toSettingsScreen,
                    icon = painterResource(id = R.drawable.ic_settings),
                    color = JetAroundTheme.colors.gray,
                    size = 70.dp,
                    modifier = Modifier.offset(x = 130.dp, y = 20.dp),
                    iconTint = JetAroundTheme.colors.textColor
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                HexagonalButton(
                    onClick = toAccountScreen,
                    text = stringResource(id = R.string.account),
                    icon = painterResource(id = R.drawable.ic_account),
                    color = JetAroundTheme.colors.yellow,
                    size = 160.dp,
                    modifier = Modifier.offset(x = 0.dp, y = main - 267.dp),
                    orientation = Orientation.Horizontal
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                HexagonalButton(
                    onClick = toEventsScreen,
                    text = stringResource(id = R.string.events),
                    icon = painterResource(id = R.drawable.ic_events),
                    color = JetAroundTheme.colors.purple,
                    size = 180.dp,
                    modifier = Modifier.offset(x = (-83).dp, y = main - 144.dp),
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                HexagonalButton(
                    onClick = toMoneysScreen,
                    text = stringResource(id = R.string.moneys),
                    icon = painterResource(id = R.drawable.ic_menu_coin),
                    color = JetAroundTheme.colors.orange,
                    size = 180.dp,
                    modifier = Modifier.offset(x = 83.dp, y = main - 144.dp),
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                HexagonalButton(
                    onClick = toStatisticScreen,
                    text = stringResource(id = R.string.statistic),
                    icon = painterResource(id = R.drawable.ic_statistic),
                    color = JetAroundTheme.colors.blue,
                    size = 210.dp,
                    modifier = Modifier.offset(x = 0.dp, y = main),
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                HexagonalButton(
                    onClick = toFriendsScreen,
                    text = stringResource(id = R.string.friends),
                    icon = painterResource(id = R.drawable.ic_friends),
                    color = JetAroundTheme.colors.lightBlue,
                    size = 160.dp,
                    modifier = Modifier.offset(x = 85.dp, y = main + 173.dp),
                    orientation = Orientation.Horizontal
                )
            }
        }

    }

    @Composable
    private fun HexagonalButton(
        onClick: () -> Unit,
        icon: Painter,
        color: Color,
        size: Dp,
        modifier: Modifier = Modifier,
        text: String? = null,
        orientation: Orientation = Orientation.Vertical,
        iconTint: Color = JetAroundTheme.colors.primaryBackground
    ) {
        val shape = RoundedPolygonShape(
            RoundedPolygon(numVertices = 6, rounding = CornerRounding(radius = 0.14F))
        )

        Box(
            modifier = modifier
                .clip(shape)
                .size(size)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = onClick
                )
                .background(color)
        ) {
            if (orientation == Orientation.Vertical) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = "$text icon",
                        tint = iconTint
                    )
                    if (text != null) {
                        Text(
                            text = text,
                            style = JetAroundTheme.typography.bigMedium,
                            color = JetAroundTheme.colors.primaryBackground,
                        )
                    }
                }
            } else if (orientation == Orientation.Horizontal) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (text != null) {
                        Text(
                            text = text,
                            style = JetAroundTheme.typography.bigMedium,
                            color = JetAroundTheme.colors.primaryBackground,
                        )
                    }
                    Icon(
                        painter = icon,
                        contentDescription = "$text icon",
                        tint = iconTint
                    )
                }
            }
        }
    }
}