package com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun EventTitleText(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        style = JetAroundTheme.typography.eventCardTitle,
        color = JetAroundTheme.colors.textColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}