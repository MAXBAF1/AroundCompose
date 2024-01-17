package com.example.aroundcompose.screens.map.models

import android.location.Location
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState


data class MapViewState(
    val currentLocation: Point? = null,
)

