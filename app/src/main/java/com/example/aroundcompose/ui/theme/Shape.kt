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
    teamsStatisticsShape = RoundedCornerShape(24.dp),
    maxRoundedCornerShape = RoundedCornerShape(100),
    friendShape = RoundedCornerShape(16.dp),
    skillShape = RoundedCornerShape(15.dp),
    upgradeShape = RoundedCornerShape(10.dp),
    buttonListShape = RoundedCornerShape(20.dp)
)