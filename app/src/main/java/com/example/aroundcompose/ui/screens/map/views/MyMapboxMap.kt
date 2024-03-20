package com.example.aroundcompose.ui.screens.map.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.aroundcompose.R
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.maps.CameraState
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.extension.compose.DefaultSettingsProvider
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.gestures.OnMoveListener
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.generated.LocationComponentSettings


class MyMapboxMap(
    private val mapViewCallback: (MapView) -> Unit,
    private val onCameraPositionChanged: (CameraState) -> Unit,
    private val onCompassClicked: () -> Unit,
) {
    private var mapView: MapView? = null
    private var mapboxMap: MapboxMap? = null

    private fun getOnMoveListener() = object : OnMoveListener {
        override fun onMoveBegin(detector: MoveGestureDetector) {}
        override fun onMove(detector: MoveGestureDetector): Boolean = false
        override fun onMoveEnd(detector: MoveGestureDetector) {
            mapboxMap?.cameraState?.let { onCameraPositionChanged(it) }
        }
    }

    @OptIn(MapboxExperimental::class)
    @Composable
    fun Create() {
        val styleUri = stringResource(id = R.string.globe3dKey)

        MapboxMap(
            modifier = Modifier.fillMaxSize(),
            locationComponentSettings = LocationComponentSettings.Builder(
                DefaultSettingsProvider.createDefault2DPuck(LocalContext.current, true)
            ).setEnabled(true).build()
        ) {
            MapEffect {
                mapView = it
                mapboxMap = it.getMapboxMap()
                mapboxMap!!.loadStyle(style(styleUri) {
                    +vectorSource(MapConstante.SOURCE_ID) { url("mapbox://bafi.${MapConstante.TILE_ID}") }
                })

                mapViewCallback(it)
                it.compass.addCompassClickListener(onCompassClicked)
                it.gestures.addOnMoveListener(getOnMoveListener())
            }
        }
    }

    object MapConstante {
        const val ALL_CELLS_LAYER_ID = "allCells"
        const val PAINTED_CELLS_LAYER_ID = "coloredCells"
        const val TILE_ID = "hexEKB0511"
        const val SOURCE_ID = "source"
        const val H3_RESOLUTION = 11
        const val ZOOM_LEVEL = 13.5
        const val ZOOM_LEVEL_DELTA = 1
    }
}
