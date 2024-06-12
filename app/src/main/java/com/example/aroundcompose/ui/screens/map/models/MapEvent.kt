package com.example.aroundcompose.ui.screens.map.models

import com.example.aroundcompose.ui.common.models.EventData
import com.mapbox.maps.CameraState

sealed class MapEvent {
    data object Init : MapEvent()
    data class EditSearchText(val text: String) : MapEvent()
    data object PlusZoomLevel : MapEvent()
    data object MinusZoomLevel : MapEvent()
    data class ShowEventSheet(val show: Boolean) : MapEvent()
    data class ShowEventInfoSheet(val show: Boolean, val event: EventData? = null) : MapEvent()
    data class UpdateCameraPosition(val cameraState: CameraState) : MapEvent()
}
