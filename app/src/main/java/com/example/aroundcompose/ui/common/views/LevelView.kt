package com.example.aroundcompose.ui.common.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun LevelView(level: Int, textColor: androidx.compose.ui.graphics.Color, modifier: Modifier) {
    Text(
        text = level.toString(),
        style = JetAroundTheme.typography.levelInformation,
        color = textColor,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}