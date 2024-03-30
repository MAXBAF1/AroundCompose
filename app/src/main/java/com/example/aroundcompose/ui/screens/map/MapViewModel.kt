package com.example.aroundcompose.ui.screens.map

import android.content.SharedPreferences
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.map.location_service.LocationService
import com.example.aroundcompose.ui.screens.map.models.MapEvent
import com.example.aroundcompose.ui.screens.map.models.MapViewState
import com.example.aroundcompose.ui.screens.map.models.MutableCameraState
import com.example.aroundcompose.ui.screens.map.views.MyMapboxMap.MapConstant
import com.example.aroundcompose.utils.toMutable
import com.mapbox.geojson.Point
import com.uber.h3core.H3Core
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    BaseViewModel<MapViewState, MapEvent>(initialState = MapViewState.CellsCaptured()) {

    private val paintedCells: ArrayList<String> = arrayListOf()
    private val coins = 0
    private var searchText = ""
    private var cameraState = MutableCameraState()

    override fun obtainEvent(viewEvent: MapEvent) {
        when (viewEvent) {
            is MapEvent.Init -> init()
            MapEvent.StartService -> startService()
            is MapEvent.EditSearchText -> searchText = viewEvent.text
            MapEvent.ZoomLevelMinus -> updateZoomLevel(viewEvent)
            MapEvent.ZoomLevelPlus -> updateZoomLevel(viewEvent)
            is MapEvent.UpdateCameraPosition -> cameraState = viewEvent.cameraState.toMutable()
        }
    }

    private fun init() {
        this.cameraState.center = getLastLocationFromSharedPref()
        LocationService.lastLocation = cameraState.center
        viewState.update { MapViewState.Init(cameraState.center, searchText, coins) }
    }

    private fun updateZoomLevel(zoomLevelUpdateEvent: MapEvent) {
        if (zoomLevelUpdateEvent == MapEvent.ZoomLevelMinus) {
            cameraState.zoom -= MapConstant.ZOOM_LEVEL_DELTA
        } else if (zoomLevelUpdateEvent == MapEvent.ZoomLevelPlus) {
            cameraState.zoom += (MapConstant.ZOOM_LEVEL_DELTA)
        }
        viewState.update { MapViewState.ZoomLevelUpdated(cameraState.zoom) }
    }

    private fun getLastLocationFromSharedPref(): Point? {
        return Point.fromJson(
            sharedPreferences.getString(LocationService.LAST_LOCATION_ID, null) ?: ""
        )
    }

    private fun startService() {
        viewState.update { MapViewState.CellsCaptured(paintedCells.toList()) }
        val h3 = H3Core.newSystemInstance()

        LocationService.onLocationResult = {
            val newCell =
                h3.latLngToCellAddress(it.latitude, it.longitude, MapConstant.H3_RESOLUTION)

            if (!paintedCells.contains(newCell)) {
                val neighborsCells = h3.gridDisk(newCell, 2)
                neighborsCells.removeAll(paintedCells)
                val newCells = neighborsCells.take(1)

                paintedCells.addAll(newCells)
                viewState.update { MapViewState.CellsCaptured(paintedCells.toList()) }
            }
        }
    }
}