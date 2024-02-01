package com.example.aroundcompose.screens.map.models

import com.mapbox.geojson.Point

sealed class MapViewState {
    data class Init(val lastLocation: Point? = null) : MapViewState()
    data class CellsCaptured(val paintedCells: List<String> = listOf()) : MapViewState()
}