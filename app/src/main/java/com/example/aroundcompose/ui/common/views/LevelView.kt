package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun LevelView(
    level: Int,
    textColor: Color,
    bgColor: Color = JetAroundTheme.colors.primary,
    modifier: Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(16.dp)
            .clip(CircleShape)
            .background(bgColor)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = level.toString(),
            style = JetAroundTheme.typography.levelInformation,
            color = textColor,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.padding(top = 2.dp) // Inner Padding
        )
    }
}