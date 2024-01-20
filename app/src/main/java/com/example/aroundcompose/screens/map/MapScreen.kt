package com.example.aroundcompose.screens.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.aroundcompose.screens.map.models.MapAction
import com.example.aroundcompose.screens.map.models.MapEvent
import com.example.aroundcompose.screens.map.views.MyMapboxMap
import com.mapbox.maps.MapView

@Composable
fun MapScreen(viewModel: MapViewModel) {
    val viewState by viewModel.getViewState().collectAsState()
    val viewAction by viewModel.getViewAction().collectAsState()

    var mapView: MapView? by remember { mutableStateOf(null) }

    MyMapboxMap(
        mapViewCallback = { mapView = it },
        onMapMove = { viewModel.obtainEvent(MapEvent.MoveMap) },
        onCompassClicked = { viewModel.obtainEvent(MapEvent.CompassClick) },
        onDoubleTap = { viewModel.obtainEvent(MapEvent.DoubleTap) }
    )

    when (viewAction) {
        MapAction.Init -> viewModel.obtainEvent(MapEvent.Init(mapView))
    }
}

const val ZOOM_LEVEL = 12.0