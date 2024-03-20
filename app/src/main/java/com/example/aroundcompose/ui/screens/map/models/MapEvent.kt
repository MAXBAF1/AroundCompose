package com.example.aroundcompose.ui.screens.map.models

import com.mapbox.maps.CameraState

sealed class MapEvent {
    object Init : MapEvent()
    object StartService : MapEvent()
    data class EditSearchText(val text: String) : MapEvent()
    object ZoomLevelPlus : MapEvent()
    object ZoomLevelMinus : MapEvent()
    data class UpdateCameraPosition(val cameraState: CameraState) : MapEvent()
}
