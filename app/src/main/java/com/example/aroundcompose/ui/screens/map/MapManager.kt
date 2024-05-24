package com.example.aroundcompose.ui.screens.map

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.aroundcompose.ui.common.models.EventData
import com.example.aroundcompose.ui.screens.map.location_service.LocationService
import com.example.aroundcompose.ui.screens.map.models.MapEvent
import com.example.aroundcompose.ui.screens.map.models.MapViewState
import com.mapbox.maps.MapView

class MapManager(private val viewModel: MapViewModel, private val onEventClick: (EventData) -> Unit,) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsState()
        var mapView: MapView? by remember { mutableStateOf(null) }
        var searchText by remember { mutableStateOf("") }

        val mapScreen = MapScreen(onMapInit = {
            mapView = it
            viewModel.obtainEvent(MapEvent.Init)
        },
            onMoveListener = { viewModel.obtainEvent(MapEvent.UpdateCameraPosition(it)) },
            onPlusZoomLevel = { viewModel.obtainEvent(MapEvent.PlusZoomLevel) },
            onMinusZoomLevel = { viewModel.obtainEvent(MapEvent.MinusZoomLevel) },
            onZoomChanged = {
                viewModel.obtainEvent(MapEvent.UpdateCameraPosition(it))
            },
            onEventClick = onEventClick)
        mapScreen.Create()

        when (val state = viewState) {
            is MapViewState.Init -> {
                searchText = state.searchText
                mapScreen.initMap(mapView, state.lastLocation)
                viewModel.obtainEvent(MapEvent.SetupService)
                if (!LocationService.isRunning) startService(LocalContext.current)
            }

            is MapViewState.CellsCaptured -> mapScreen.paintCells(mapView, state.paintedCells)
            is MapViewState.ZoomLevelUpdated -> mapScreen.updateZoomLevel(mapView, state.zoomLevel)
        }
    }

    private fun startService(context: Context) {
        val intent = Intent(context, LocationService::class.java)
        context.startForegroundService(intent)
    }
}