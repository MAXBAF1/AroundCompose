package com.example.aroundcompose.ui.screens.map.views

import android.view.Gravity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.maps.CameraState
import com.mapbox.maps.ImageHolder
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
import com.mapbox.maps.plugin.scalebar.scalebar


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
                    +vectorSource(MapConstant.SOURCE_ID) { url("mapbox://bafi.${MapConstant.TILE_ID}") }
                })

                mapViewCallback(it)
                setupCompass(it)

                it.gestures.addOnMoveListener(getOnMoveListener())
            }
        }
    }

    private fun setupCompass(mapView: MapView) {
        mapView.compass.addCompassClickListener(onCompassClicked)
        mapView.scalebar.enabled = false
        mapView.compass.image = ImageHolder.Companion.from(R.drawable.ic_compass)
        mapView.compass.position = Gravity.BOTTOM + Gravity.END
        mapView.compass.marginBottom = 50.dp.value
        mapView.compass.marginRight = 30.dp.value
    }

    object MapConstant {
        const val ALL_CELLS_LAYER_ID = "allCells"
        const val PAINTED_CELLS_LAYER_ID = "coloredCells"
        const val TILE_ID = "hexEKB0511"
        const val SOURCE_ID = "source"
        const val H3_RESOLUTION = 11
        const val ZOOM_LEVEL = 13.5
        const val ZOOM_LEVEL_DELTA = 1
    }
}
