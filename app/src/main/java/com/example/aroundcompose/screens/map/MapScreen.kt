package com.example.aroundcompose.screens.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.aroundcompose.screens.map.models.MapAction
import com.example.aroundcompose.screens.map.models.MapEvent
import com.example.aroundcompose.screens.map.views.MyMapboxMap
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.locationcomponent.location

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(viewModel: MapViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    val viewAction by viewModel.viewAction.collectAsState()

    val viewportState = rememberMapViewportState {
        setCameraOptions {
            center(viewState?.currentLocation)
            zoom(ZOOM_LEVEL)
            bearing(0.0)
        }
    }
    MyMapboxMap(viewportState)


    when (viewAction) {
        MapAction.Init -> {
            viewModel.obtainEvent(viewEvent = MapEvent.Init)
        }
    }
}

const val ZOOM_LEVEL = 12.0