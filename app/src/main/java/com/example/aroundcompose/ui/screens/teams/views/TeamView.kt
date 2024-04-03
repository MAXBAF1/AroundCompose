package com.example.aroundcompose.ui.screens.teams.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun TeamView(containerColor: Color, isEnable: Boolean, onClick: () -> Unit) {
    Card(
        shape = JetAroundTheme.shapes.teamShape,
        border = BorderStroke(
            width = if (isEnable) 5.dp else 2.dp,
            color = JetAroundTheme.colors.textColor
        ),
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Color.Transparent,
            disabledContainerColor = containerColor,
            disabledContentColor = Color.Transparent
        ),
        modifier = Modifier
            .size(159.dp)
            .clip(shape = JetAroundTheme.shapes.teamShape)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = JetAroundTheme.colors.textColor)
            )
    ) {}
}