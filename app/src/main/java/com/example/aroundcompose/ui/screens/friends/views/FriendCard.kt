package com.example.aroundcompose.ui.screens.friends.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.views.HexagonImage
import com.example.aroundcompose.ui.common.views.LevelView
import com.example.aroundcompose.ui.screens.friends.models.FriendData
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.RoundedPolygonShape


class FriendCard(
    private val friendData: FriendData,
    private val onMoreInfoClick: () -> Unit,
) {
// Кликалось на всю
    @Composable
    fun Create(modifier: Modifier) {
        val shape = RoundedPolygonShape(
            RoundedPolygon(numVertices = 6, rounding = CornerRounding(radius = 0.14F))
        )

        Card(
            shape = JetAroundTheme.shapes.friendShape,
            elevation = CardDefaults.cardElevation(JetAroundTheme.shadows.mapElementsShadow),
            colors = CardColors(
                containerColor = JetAroundTheme.colors.primaryBackground,
                contentColor = JetAroundTheme.colors.titleColor,
                disabledContainerColor = JetAroundTheme.colors.primaryBackground,
                disabledContentColor = JetAroundTheme.colors.titleColor
            ),
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 18.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        text = "${friendData.position}.",
                        style = JetAroundTheme.typography.semiBold,
                        color = JetAroundTheme.colors.titleColor
                    )
                    HexagonImage(
                        imageId = friendData.imageId,
                        team = friendData.team,
                        border = 2.dp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(50.dp)
                    )

                    TextInfo(
                        name = friendData.name,
                        level = friendData.level,
                        score = friendData.score
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.ic_more_info),
                    contentDescription = "more info",
                    tint = JetAroundTheme.colors.lightTint,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .clickable(
                            onClick = onMoreInfoClick,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = JetAroundTheme.colors.textColor)
                        )
                        .padding(vertical = 4.dp, horizontal = 7.dp)
                )
            }
        }
    }

    @Composable
    private fun TextInfo(name: String, level: Int, score: Int) {
        val color = JetAroundTheme.colors.titleColor

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = JetAroundTheme.typography.mediumBold,
                    color = color
                )

                LevelView(
                    level = level,
                    textColor = JetAroundTheme.colors.titleColor,
                    modifier = Modifier
                        .padding(start = 2.dp) // Position
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(JetAroundTheme.colors.gray)
                        .padding(top = 4.dp) // Inner padding
                )
            }

            Text(
                text = "захвачено: $score клеток",
                style = JetAroundTheme.typography.textDesc,
                color = color,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
