package com.example.aroundcompose.ui.screens.skills.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.views.LevelView
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.ui.theme.JetAroundTheme

class CardView(
    private val skillData: SkillDTO,
    private val onCardClick: () -> Unit,
    private val onUpgradeClick: () -> Unit,
    private val isUpgradable: Boolean,
    private val isCardClicked: Boolean,
    private val modifier: Modifier,
) {
    @Composable
    fun Create() {
        Column(modifier = modifier.fillMaxWidth()) {
            SkillCard()

            AnimatedVisibility(visible = isCardClicked) {
                DescriptionCard(
                    imageId = R.drawable.avatar_example, // FIXME добавить сериализацию
                    description = skillData.description
                )
            }
        }
    }

    @Composable
    private fun SkillCard() {
        Card(
            shape = JetAroundTheme.shapes.skillShape,
            border = BorderStroke(2.dp, JetAroundTheme.colors.textColor),
            colors = CardColors(
                contentColor = JetAroundTheme.colors.textColor,
                containerColor = JetAroundTheme.colors.primaryBackground,
                disabledContainerColor = JetAroundTheme.colors.primaryBackground,
                disabledContentColor = JetAroundTheme.colors.textColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = JetAroundTheme.shapes.skillShape)
                .clickable(
                    onClick = onCardClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = JetAroundTheme.colors.textColor)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SkillIcon()
                    TextContainer()
                }
                if (isUpgradable) {
                    if (skillData.currentLevel != skillData.maxLevel) {
                        UpgradeButton()
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.max_level),
                            contentDescription = "max level"
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun SkillIcon() {
        Box {
            Icon(
                painter = painterResource(id = R.drawable.avatar_example), // FIXME обработка URL
                contentDescription = "skill icon",
                modifier = Modifier
                    .clip(JetAroundTheme.shapes.upgradeShape)
                    .background(JetAroundTheme.colors.veryLightGray)
                    .padding(8.dp)
            )
            LevelView(
                level = skillData.currentLevel,
                textColor = JetAroundTheme.colors.primaryBackground,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 26.dp, start = 26.dp) // Position
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(JetAroundTheme.colors.primary)
                    .padding(top = 4.dp) // Inner padding
            )
        }
    }

    @Composable
    private fun TextContainer() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = skillData.name,
                style = JetAroundTheme.typography.sixteenMedium,
                color = JetAroundTheme.colors.textColor
            )
            TextPrice(Modifier.padding(top = 2.dp))
        }
    }

    @Composable
    private fun TextPrice(modifier: Modifier) {
        val priceText = if (skillData.currentLevel != skillData.maxLevel) {
            "${stringResource(id = R.string.price)}: ${skillData.cost[skillData.currentLevel + 1]}"
        } else ""

        Row {
            Text(
                text = priceText,
                style = JetAroundTheme.typography.smallMedium,
                color = JetAroundTheme.colors.informationText,
                modifier = modifier
            )

            if (priceText != "") {
                Icon(
                    painter = painterResource(id = R.drawable.ic_coin),
                    contentDescription = "currency icon",
                    tint = JetAroundTheme.colors.informationText,
                    modifier = Modifier
                        .size(14.dp)
                        .padding(start = 2.dp)
                        .align(Alignment.Bottom)
                )
            }
        }
    }

    @Composable
    private fun UpgradeButton() {
        Icon(
            painter = painterResource(id = R.drawable.ic_upgrade),
            contentDescription = "upgrade button",
            tint = JetAroundTheme.colors.primaryBackground,
            modifier = Modifier
                .clip(shape = JetAroundTheme.shapes.upgradeShape)
                .clickable(
                    onClick = onUpgradeClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = JetAroundTheme.colors.textColor)
                )
                .background(JetAroundTheme.colors.primary)
                .padding(horizontal = 11.dp, vertical = 8.dp)
        )
    }

    @Composable
    private fun DescriptionCard(imageId: Int, description: String) {
        Card(
            shape = JetAroundTheme.shapes.upgradeShape,
            border = BorderStroke(2.dp, JetAroundTheme.colors.textColor),
            colors = CardColors(
                contentColor = JetAroundTheme.colors.textColor,
                containerColor = JetAroundTheme.colors.primaryBackground,
                disabledContainerColor = JetAroundTheme.colors.primaryBackground,
                disabledContentColor = JetAroundTheme.colors.textColor
            ),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp, vertical = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "skill image",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Column(
                    modifier = Modifier.padding(top = 14.dp, start = 5.dp, end = 5.dp)
                ) {
                    Text(
                        text = description,
                        style = JetAroundTheme.typography.regular14,
                        color = JetAroundTheme.colors.textColor,
                    )

                    TextPrice(Modifier.padding(top = 10.dp))
                }
            }
        }
    }
}
