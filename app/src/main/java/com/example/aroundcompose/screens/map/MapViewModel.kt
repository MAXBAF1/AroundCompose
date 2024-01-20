package com.example.aroundcompose.screens.map

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.app.Application
import android.app.Service
import android.content.Intent
import com.example.aroundcompose.domain.BaseViewModel
import com.example.aroundcompose.screens.map.locationService.LocationService
import com.example.aroundcompose.screens.map.models.MapAction
import com.example.aroundcompose.screens.map.models.MapEvent
import com.example.aroundcompose.screens.map.models.MapViewState
import com.example.aroundcompose.utils.toPoint
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.animation.easeTo
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.plugin.locationcomponent.location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(application: Application) :
    BaseViewModel<MapViewState, MapAction, MapEvent>(
        application = application, initialState = MapViewState(), initialAction = MapAction.Init
    ) {
    private val positionChangedListener = OnIndicatorPositionChangedListener {
        viewState.value?.mapView?.getMapboxMap()?.setCamera(
            CameraOptions.Builder().center(it).build()
        )
    }

    private val animatorListener = object : AnimatorListener {
        override fun onAnimationStart(p0: Animator) {}
        override fun onAnimationEnd(p0: Animator) = cameraFollow()
        override fun onAnimationCancel(p0: Animator) {}
        override fun onAnimationRepeat(p0: Animator) {}
    }

    override fun obtainEvent(viewEvent: MapEvent) {
        when (viewEvent) {
            is MapEvent.Init -> initComponents(viewEvent.mapView)
            MapEvent.CompassClick -> onCompassClick()
            MapEvent.MoveMap, MapEvent.DoubleTap -> removeCameraFollow()
        }
    }

    private fun initComponents(mapView: MapView?) {
        initMap(mapView)
        startService()
    }

    private fun initMap(mapView: MapView?) {
        viewState.update { it?.copy(mapView = mapView) }

        mapView?.getMapboxMap()?.setCamera(
            CameraOptions.Builder().center(getLastLocationFromSharedPref()).zoom(ZOOM_LEVEL)
                .bearing(0.0).build()
        )
        cameraFollow()
    }

    private fun cameraFollow() {
        val mapView = viewState.value?.mapView
        mapView?.location?.addOnIndicatorPositionChangedListener(positionChangedListener)
        mapView?.compass?.fadeWhenFacingNorth = true
    }

    private fun removeCameraFollow() {
        val mapView = viewState.value?.mapView
        mapView?.location?.removeOnIndicatorPositionChangedListener(positionChangedListener)
        mapView?.compass?.fadeWhenFacingNorth = false
    }

    private fun onCompassClick() {
        val mapView = viewState.value!!.mapView!!
        val mapboxMap = mapView.getMapboxMap()

        if (mapboxMap.cameraState.bearing == 0.0) {
            mapboxMap.flyTo(
                cameraOptions = CameraOptions.Builder()
                    .center(LocationService.lastLocation?.toPoint())
                    .zoom(maxOf(mapboxMap.cameraState.zoom, ZOOM_LEVEL)).build(),
                animatorListener = animatorListener
            )
        } else {
            mapboxMap.easeTo(CameraOptions.Builder().bearing(0.0).build())
        }
    }


    private fun getLastLocationFromSharedPref(): Point? {
        val sharedPreferences = context.getSharedPreferences(
            LocationService.S_PREF_LOCATION, Service.MODE_PRIVATE
        )

        return Point.fromJson(
            sharedPreferences.getString(LocationService.LAST_LOCATION_ID, null) ?: ""
        )
    }

    private fun startService() {
        LocationService.onLocationResult = {
            /*viewState.update {
                it?.copy(currentLocation = location.toPoint())
            }*/
        }

        if (LocationService.isRunning) return

        val intent = Intent(context, LocationService::class.java)
        context.startForegroundService(intent)
    }
}