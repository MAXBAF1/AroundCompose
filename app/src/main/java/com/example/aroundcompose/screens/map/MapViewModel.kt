package com.example.aroundcompose.screens.map

import android.app.Service
import android.content.Context
import android.content.SharedPreferences
import com.example.aroundcompose.domain.BaseViewModel
import com.example.aroundcompose.screens.map.location_service.LocationService
import com.example.aroundcompose.screens.map.models.MapEvent
import com.example.aroundcompose.screens.map.models.MapViewState
import com.example.aroundcompose.screens.map.views.H3_RESOLUTION
import com.mapbox.geojson.Point
import com.uber.h3core.H3Core
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    BaseViewModel<MapViewState, MapEvent>(initialState = MapViewState.CellsCaptured()) {

    private val paintedCells: ArrayList<String> = arrayListOf()
    private val h3 = H3Core.newSystemInstance()

    override fun obtainEvent(viewEvent: MapEvent) {
        when (viewEvent) {
            MapEvent.Init -> viewState.update { MapViewState.Init(getLastLocationFromSharedPref()) }
            MapEvent.StartService -> startService()
        }
    }

    private fun getLastLocationFromSharedPref(): Point? {
        return Point.fromJson(
            sharedPreferences.getString(LocationService.LAST_LOCATION_ID, null) ?: ""
        )
    }

    private fun startService() {
        viewState.update { MapViewState.CellsCaptured(paintedCells.toList()) }

        LocationService.onLocationResult = {
            val newCell = h3.latLngToCellAddress(it.latitude, it.longitude, H3_RESOLUTION)

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