package com.example.aroundcompose.screens.map.views

import android.view.MotionEvent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.aroundcompose.R
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.android.gestures.StandardGestureDetector
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.DefaultSettingsProvider
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.gestures.OnMoveListener
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.generated.LocationComponentSettings

@OptIn(MapboxExperimental::class)
@Composable
fun MyMapboxMap(
    mapViewCallback: (MapView) -> Unit,
    onMapMove: () -> Unit,
    onCompassClicked: () -> Unit
) {

    val onMoveListener = object : OnMoveListener {
        override fun onMoveBegin(detector: MoveGestureDetector) = onMapMove()
        override fun onMove(detector: MoveGestureDetector): Boolean = false
        override fun onMoveEnd(detector: MoveGestureDetector) {}
    }

    val onDoubleTapListener = object : StandardGestureDetector.SimpleStandardOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            onMapMove()
            return super.onDoubleTap(e)
        }
    }

    val styleUri = stringResource(id = R.string.globe3dKey)

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        locationComponentSettings = LocationComponentSettings.Builder(
            DefaultSettingsProvider.createDefault2DPuck(LocalContext.current, true)
        ).setEnabled(true).build()
    ) {
        MapEffect {
            val mapboxMap = it.getMapboxMap()
            mapboxMap.loadStyle(style(styleUri) {
                +vectorSource(SOURCE_ID) { url("mapbox://bafi.$TILE_ID") }
            })

            mapViewCallback(it)
            it.compass.addCompassClickListener(onCompassClicked)
            it.gestures.addOnMoveListener(onMoveListener)
        }
    }
}


const val ALL_CELLS_LAYER_ID = "allCells"
const val PAINTED_CELLS_LAYER_ID = "coloredCells"
const val TILE_ID = "hexEKB0511"
const val SOURCE_ID = "source"
const val H3_RESOLUTION = 11
const val ZOOM_LEVEL = 13.5
