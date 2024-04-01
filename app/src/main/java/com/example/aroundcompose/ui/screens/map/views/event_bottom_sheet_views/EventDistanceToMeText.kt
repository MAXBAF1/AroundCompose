package com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun EventDistanceToMeText(distanceToMe: Double) {
    Text(
        text = "$distanceToMe ${stringResource(id = R.string.km)}",
        style = JetAroundTheme.typography.eventCardKm,
        color = JetAroundTheme.colors.eventCardText
    )
}
