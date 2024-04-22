package com.example.aroundcompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.aroundcompose.utils.RoundedPolygonShape

val shape = JetAroundShape(
    textFieldShape = RoundedCornerShape(14.dp),
    mapElementsShape = RoundedCornerShape(12.dp),
    teamShape = RoundedCornerShape(21.dp),
    maxRoundedCornerShape = RoundedCornerShape(100),
    skillShape = RoundedCornerShape(15.dp),
    upgradeShape = RoundedCornerShape(10.dp)
)