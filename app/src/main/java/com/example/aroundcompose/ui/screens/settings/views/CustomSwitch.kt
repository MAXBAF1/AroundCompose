package com.example.aroundcompose.ui.screens.settings.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Offset) -> Unit,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    thumbColor: Color = Color(0xFFFFFFFF),
    checkedTrackColor: Color = Color(0xFF35898F),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 2.dp
) {
    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge
    val animatePosition = animateFloatAsState(targetValue = with(LocalDensity.current) {
        if (checked) {
            (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx()
        } else (thumbRadius + gapBetweenThumbAndTrackEdge).toPx()
    }, label = "")

    Canvas(modifier = Modifier
        .size(width = width, height = height)
        .pointerInput(Unit) {
            detectTapGestures(onTap = onCheckedChange)
        }) {
        drawRoundRect(
            color = if (checked) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
        )
        drawCircle(
            color = thumbColor, radius = thumbRadius.toPx(), center = Offset(
                x = animatePosition.value, y = size.height / 2
            )
        )
    }
}