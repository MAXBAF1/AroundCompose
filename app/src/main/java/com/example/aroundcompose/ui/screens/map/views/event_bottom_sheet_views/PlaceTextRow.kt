package com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun PlaceTextRow(place: String, modifier: Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = "location icon",
            modifier = Modifier.padding(end = 4.dp),
            tint = JetAroundTheme.colors.lightTint
        )
        Text(
            text = place,
            style = JetAroundTheme.typography.eventCardPlaceAndBtn,
            color = JetAroundTheme.colors.eventCardText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}