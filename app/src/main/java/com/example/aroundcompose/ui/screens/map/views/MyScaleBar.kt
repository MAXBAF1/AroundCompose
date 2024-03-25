package com.example.aroundcompose.ui.screens.map.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun MyScaleBar(value: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.background(
                JetAroundTheme.colors.mapElements,
                shape = JetAroundTheme.shapes.maxRoundedCornerShape
            )
        ) {
            Spacer(modifier = Modifier.size(2.dp, 70.dp))
        }
        Text(
            text = "$value м",
            style = JetAroundTheme.typography.scaleBar,
            color = JetAroundTheme.colors.mapElements
        )
    }
}