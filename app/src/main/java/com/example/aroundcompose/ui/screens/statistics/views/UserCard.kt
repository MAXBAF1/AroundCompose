package com.example.aroundcompose.ui.screens.statistics.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.views.HexagonImage
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun UserCard(
    position: Int,
    imageId: Int,
    name: String, score: Int,
    team: Teams = Teams.NONE,
    isCurrentUser: Boolean = false,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Card(
        colors = CardColors(
            containerColor = if (isCurrentUser) {
                JetAroundTheme.colors.userCard
            } else JetAroundTheme.colors.userCard.copy(alpha = 0.30F),
            contentColor = if (isCurrentUser) {
                JetAroundTheme.colors.textColor
            } else JetAroundTheme.colors.userCard,
            disabledContainerColor = JetAroundTheme.colors.primaryBackground,
            disabledContentColor = JetAroundTheme.colors.userCard.copy(alpha = 0.30F)
        ),
        shape = JetAroundTheme.shapes.upgradeShape,
        modifier = if (isCurrentUser) {
            modifier.fillMaxWidth()
        } else {
            modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = JetAroundTheme.colors.textColor)
                )
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            val style = JetAroundTheme.typography.mediumRegular
            val color = if (isCurrentUser) {
                JetAroundTheme.colors.textColor
            } else JetAroundTheme.colors.userCard

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$position.",
                    style = style,
                    color = color
                )

                HexagonImage(
                    imageId = imageId,
                    team = team,
                    border = 1.dp,
                    modifier = Modifier.padding(start = 8.dp)
                )

                Text(
                    text = if (isCurrentUser) {
                        stringResource(R.string.currentUser)
                    } else name,
                    style = style,
                    color = color,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Text(
                text = "$score",
                style = style,
                color = color
            )
        }
    }
}