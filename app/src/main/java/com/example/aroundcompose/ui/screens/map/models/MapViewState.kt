package com.example.aroundcompose.ui.screens.map.models

import com.example.aroundcompose.data.models.CellDTO
import com.example.aroundcompose.data.models.EventDTO
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.ui.common.models.EventData
import com.mapbox.geojson.Point

data class MapViewState(
    val lastLocation: Point? = null,
    val searchText: String = "",
    val coins: Int = 0,
    val paintedCells: List<CellDTO> = listOf(),
    val zoomLevel: Double = 0.0,
    val isEventsSheetShowed: Boolean = false,
    val isEventInfoSheetShowed: Boolean = false,
    val chosenEvent: EventData? = null,

    val events: List<EventDTO> = listOf(),
    val myInfo: UserDTO? = null
)