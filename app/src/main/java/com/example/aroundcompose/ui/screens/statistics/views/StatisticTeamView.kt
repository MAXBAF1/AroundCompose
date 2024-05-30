package com.example.aroundcompose.ui.screens.statistics.views

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.RoundedPolygonShape

class StatisticTeamView(
    private val currentPercent: Float = 0F,
    private val team: Teams,
    private val isLeader: Boolean = false,
) {
    private val shape = RoundedPolygonShape(
        RoundedPolygon(numVertices = 6, rounding = CornerRounding(radius = 0.14F))
    )

    @Composable
    fun Create() {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            when (team) {
                Teams.LIGHT_BLUE -> {
                    TeamHexagon(
                        backgroundColor = JetAroundTheme.colors.backgroundColorLightBlueTeam,
                        foregroundColor = JetAroundTheme.colors.lightBlue,
                        modifier = Modifier.offset(x = 72.dp, y = 43.dp)
                    )
                }

                Teams.YELLOW -> {
                    TeamHexagon(
                        backgroundColor = JetAroundTheme.colors.backgroundColorYellowTeam,
                        foregroundColor = JetAroundTheme.colors.yellow,
                        modifier = Modifier.offset(x = 189.dp, y = 43.dp)
                    )
                }

                Teams.PURPLE -> {
                    TeamHexagon(
                        backgroundColor = JetAroundTheme.colors.backgroundColorPurpleTeam,
                        foregroundColor = JetAroundTheme.colors.purple,
                        modifier = Modifier.offset(x = 13.dp, y = 146.dp)
                    )
                }

                Teams.BLUE -> {
                    TeamHexagon(
                        backgroundColor = JetAroundTheme.colors.backgroundColorBlueTeam,
                        foregroundColor = JetAroundTheme.colors.blue,
                        modifier = Modifier.offset(x = 131.dp, y = 146.dp)
                    )
                }

                else -> Log.d("TEAMS", "Unknown team")
            }
        }
    }

    @Composable
    fun TeamHexagon(
        backgroundColor: Color,
        foregroundColor: Color,
        modifier: Modifier,
    ) {
        val height = 126.dp * currentPercent / 100

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier
                .clip(shape)
                .size(126.dp)
                .background(backgroundColor)
        ) {
            if (isLeader) {
                Box(
                    modifier = Modifier
                        .width(126.dp)
                        .height(height)
                        .drawBehind {
                            drawContext.canvas.nativeCanvas.apply {
                                drawRoundRect(
                                    0f,
                                    0f,
                                    126.dp.toPx(),
                                    height.toPx(),
                                    0.dp.toPx(), // Box radius
                                    0.dp.toPx(), // Box radius
                                    Paint().apply {
                                        color = foregroundColor.toArgb() // Background color
                                        setShadowLayer(
                                            30.dp.toPx(), // Shadow radius
                                            0.dp.toPx(), 0.dp.toPx(),
                                            foregroundColor.toArgb() // Shadow color
                                        )
                                    }
                                )
                            }
                        }
                )
            } else {
                Box(
                    modifier = Modifier
                        .width(126.dp)
                        .height(height)
                        .background(foregroundColor)
                )
            }

            Text(
                text = "${currentPercent.toInt()}%",
                style = JetAroundTheme.typography.percent,
                color = JetAroundTheme.colors.primaryBackground,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}