package com.example.aroundcompose.ui.screens.map.models

import com.example.aroundcompose.ui.common.models.EventData
import com.mapbox.maps.CameraState

sealed class MapEvent {
    object Init : MapEvent()
    object SetupService : MapEvent()
    data class EditSearchText(val text: String) : MapEvent()
    object PlusZoomLevel : MapEvent()
    object MinusZoomLevel : MapEvent()
    data class ShowEventSheet(val show: Boolean) : MapEvent()
    data class ShowEventInfoSheet(val show: Boolean, val event: EventData? = null) : MapEvent()
    data class UpdateCameraPosition(val cameraState: CameraState) : MapEvent()
}
