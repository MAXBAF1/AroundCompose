package com.example.aroundcompose.ui.screens.map.models

import com.mapbox.maps.CameraState

sealed class MapEvent {
    object Init : MapEvent()
    object SetupService : MapEvent()
    data class EditSearchText(val text: String) : MapEvent()
    object PlusZoomLevel : MapEvent()
    object MinusZoomLevel : MapEvent()
    data class ShowEventSheet(val show: Boolean) : MapEvent()
    data class UpdateCameraPosition(val cameraState: CameraState) : MapEvent()
}
