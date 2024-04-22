package com.example.aroundcompose.ui.screens.statistics.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.RoundedPolygonShape

@Composable
fun HexagonView(modifier: Modifier) {
    val shape = RoundedPolygonShape(
        RoundedPolygon(numVertices = 6, rounding = CornerRounding(radius = 0.14F))
    )
    Box(
        modifier = modifier
            .clip(shape)
            .size(126.dp)
            .background(JetAroundTheme.colors.gray.copy(alpha = 0.14F))
    )
}