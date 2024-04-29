package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.RoundedPolygonShape

@Composable
fun HexagonImage(imageId: Int, team: Teams = Teams.NONE, border: Dp, modifier: Modifier) {
    val shape = RoundedPolygonShape(
        RoundedPolygon(numVertices = 6, rounding = CornerRounding(radius = 0.14F))
    )

    val backgroundColor = when (team) {
        Teams.LIGHT_BLUE -> JetAroundTheme.colors.lightBlue
        Teams.YELLOW -> JetAroundTheme.colors.yellow
        Teams.PURPLE -> JetAroundTheme.colors.purple
        Teams.BLUE -> JetAroundTheme.colors.blue
        Teams.NONE -> JetAroundTheme.colors.gray
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape)
            .border(BorderStroke(border, backgroundColor), shape)
            .size(20.dp)
            .background(backgroundColor)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "user image",
            contentScale = ContentScale.Crop
        )
    }
}