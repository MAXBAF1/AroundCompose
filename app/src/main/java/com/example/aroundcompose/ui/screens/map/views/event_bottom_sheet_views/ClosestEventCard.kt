package com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.models.EventData
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun ClosestEventCard(eventData: EventData, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = JetAroundTheme.colors.primaryBackground),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = {})
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.event_image_example_2),
                contentDescription = "event image",
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1F)
            ) {
                EventTitleText(eventData.title, modifier = Modifier.padding(bottom = 4.dp))
                PlaceTextRow(eventData.place, modifier = Modifier.padding(bottom = 10.dp))
                EventDistanceToMeText(eventData.distanceToMe)
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_go_to),
                contentDescription = "icon",
                modifier = Modifier,
                tint = JetAroundTheme.colors.lightTint
            )
        }
    }
}

