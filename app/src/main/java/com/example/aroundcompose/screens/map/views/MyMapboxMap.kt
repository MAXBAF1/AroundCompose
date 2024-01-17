package com.example.aroundcompose.screens.map.views

import android.animation.TimeInterpolator
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.aroundcompose.R
import com.example.aroundcompose.screens.map.ZOOM_LEVEL
import com.example.aroundcompose.screens.map.locationService.LocationService
import com.example.aroundcompose.utils.toPoint
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.DefaultSettingsProvider
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.plugin.animation.CameraAnimatorsFactory
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.compass.generated.CompassSettings
import com.mapbox.maps.plugin.gestures.OnMoveListener
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.plugin.locationcomponent.generated.LocationComponentSettings
import com.mapbox.maps.plugin.locationcomponent.location

@OptIn(MapboxExperimental::class)
@Composable
fun MyMapboxMap(viewportState: MapViewportState) {
    val styleUri = stringResource(id = R.string.globe3dKey)

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = viewportState,
        compassSettings = CompassSettings {
            fadeWhenFacingNorth = false
        },
        mapInitOptionsFactory = { mapInitContext ->
            MapInitOptions(context = mapInitContext, styleUri = styleUri)
        },
        locationComponentSettings = LocationComponentSettings.Builder(
            DefaultSettingsProvider.createDefault2DPuck(LocalContext.current, true)
        ).setEnabled(true).build()
    ) {

        MapEffect { mapView ->
            val compass = mapView.compass
            val locationPlugin = mapView.location
            val mapboxMap = mapView.getMapboxMap()
            val positionChangedListener = OnIndicatorPositionChangedListener {
                viewportState.setCameraOptions(CameraOptions.Builder().center(it).build())
            }
            val onMoveListener = object : OnMoveListener {
                override fun onMoveBegin(detector: MoveGestureDetector) {
                    locationPlugin.removeOnIndicatorPositionChangedListener(positionChangedListener)
                    compass.fadeWhenFacingNorth = false
                }

                override fun onMove(detector: MoveGestureDetector): Boolean {
                    return false
                }

                override fun onMoveEnd(detector: MoveGestureDetector) {}
            }
            mapView.gestures.addOnMoveListener(onMoveListener)

            compass.addCompassClickListener {

                if (mapboxMap.cameraState.bearing == 0.0) {
                    viewportState.flyTo(
                        CameraOptions.Builder().center(LocationService.lastLocation?.toPoint())
                            .zoom(maxOf(mapboxMap.cameraState.zoom, ZOOM_LEVEL))
                            .build(),
                        MapAnimationOptions.mapAnimationOptions {
                            duration(3000)
                        }
                    ) {
                        locationPlugin.addOnIndicatorPositionChangedListener(positionChangedListener)
                        compass.fadeWhenFacingNorth = true
                    }
                } else {
                    viewportState.flyTo(CameraOptions.Builder().bearing(0.0).build())
                }
            }

        }
    }
}