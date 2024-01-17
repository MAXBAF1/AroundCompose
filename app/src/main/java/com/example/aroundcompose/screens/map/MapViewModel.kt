package com.example.aroundcompose.screens.map

import android.app.Service
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.aroundcompose.domain.BaseViewModel
import com.example.aroundcompose.screens.map.locationService.LocationService
import com.example.aroundcompose.screens.map.models.MapAction
import com.example.aroundcompose.screens.map.models.MapEvent
import com.example.aroundcompose.screens.map.models.MapViewState
import com.example.aroundcompose.utils.toPoint
import com.mapbox.geojson.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : BaseViewModel<MapViewState, MapAction, MapEvent>(
    initialState = MapViewState(), initialAction = MapAction.Init
) {

    @Composable
    override fun obtainEvent(viewEvent: MapEvent) {
        when (viewEvent) {
            MapEvent.Init -> SetLastLocationAndStartService()
            MapEvent.TODO -> todo()
        }
    }

    private fun todo() {

    }

    @Composable
    fun SetLastLocationAndStartService() {
        setLastLocationFromSharedPref()
        startService()
    }

    @Composable
    private fun setLastLocationFromSharedPref() {
        val sharedPreferences = LocalContext.current.getSharedPreferences(
            LocationService.S_PREF_LOCATION, Service.MODE_PRIVATE
        )

        val lastLocation = Point.fromJson(
            sharedPreferences.getString(LocationService.LAST_LOCATION_ID, null) ?: ""
        )
        setViewState(viewState.value?.copy(currentLocation = lastLocation))
    }

    @Composable
    private fun startService() {
        LocationService.onLocationResult = {
            setViewState(viewState.value?.copy(currentLocation = it.toPoint()))
        }

        if (LocationService.isRunning) return

        val context = LocalContext.current
        val intent = Intent(context, LocationService::class.java)
        context.startForegroundService(intent)
    }
}