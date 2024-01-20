package com.example.aroundcompose.screens.map.models

import com.mapbox.maps.MapView

sealed class MapEvent {
    data class Init(val mapView: MapView?) : MapEvent()
    object CompassClick : MapEvent()
    object MoveMap : MapEvent()
    object DoubleTap : MapEvent()
}
