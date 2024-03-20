package com.example.aroundcompose.ui.screens.map.models

import com.mapbox.geojson.Point

sealed class MapViewState {
    data class Init(
        val lastLocation: Point? = null,
        val searchText: String,
        val coins: Int,
    ) : MapViewState()

    data class CellsCaptured(val paintedCells: List<String> = listOf()) : MapViewState()
    data class ZoomLevelUpdated(val zoomLevel: Double) : MapViewState()
}