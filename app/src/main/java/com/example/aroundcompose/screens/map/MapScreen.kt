package com.example.aroundcompose.screens.map

import android.app.Service
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.aroundcompose.screens.map.locationService.LocationService
import com.example.aroundcompose.screens.map.models.MapAction
import com.example.aroundcompose.screens.map.models.MapEvent
import com.example.aroundcompose.screens.map.views.MyMapboxMap
import com.example.aroundcompose.utils.toPoint
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.animation.MapAnimationOptions

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(viewModel: MapViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    val viewAction by viewModel.viewAction.collectAsState()

    val viewPortState = rememberMapViewportState {
        setCameraOptions {
            center(viewState?.currentLocation)
            zoom(12.0)
            bearing(0.0)
        }
    }
    MyMapboxMap(viewPortState)

    when (viewAction) {
        MapAction.Init -> {
            viewModel.obtainEvent(viewEvent = MapEvent.Init)
        }
    }
}