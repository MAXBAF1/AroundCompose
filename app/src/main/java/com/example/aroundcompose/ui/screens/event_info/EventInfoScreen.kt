package com.example.aroundcompose.ui.screens.event_info

import androidx.compose.runtime.Composable
import com.example.aroundcompose.ui.common.models.EventData
import com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views.PopularEventCard

class EventInfoScreen(private val eventData: EventData) {

    @Composable
    fun Create() {
        PopularEventCard(eventData = eventData, onClick = { /*TODO*/ })
    }
}