package com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.models.EventData
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun PopularEventCard(eventData: EventData, modifier: Modifier = Modifier) {
    Card(modifier = modifier.size(180.dp, 106.dp), shape = RoundedCornerShape(16.dp)) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.event_image_example),
                contentDescription = "event image",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(JetAroundTheme.colors.primaryBackground)
                    .padding(8.dp)
            ) {
                EventTitleText(title = eventData.title, modifier = Modifier.padding(bottom = 4.dp))
                PlaceTextRow(eventData.place, modifier = Modifier.padding(bottom = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    EventDistanceToMeText(eventData.distanceToMe)
                    ReadButton { }
                }
            }
        }
    }
}


@Composable
private fun ReadButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = JetAroundTheme.colors.primary)
    ) {
        Text(
            text = stringResource(id = R.string.read),
            style = JetAroundTheme.typography.eventCardPlaceAndBtn,
            color = JetAroundTheme.colors.primaryBackground
        )
    }
}