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
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.DefaultSettingsProvider
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.gestures.OnMoveListener
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.generated.LocationComponentSettings

@OptIn(MapboxExperimental::class)
@Composable
fun MyMapboxMap(
    mapViewCallback: (MapView) -> Unit,
    onMapMove: () -> Unit,
    onCompassClicked: () -> Unit,
    onDoubleTap: () -> Unit,
) {
    val styleUri = stringResource(id = R.string.globe3dKey)

    val onMoveListener = object : OnMoveListener {
        override fun onMoveBegin(detector: MoveGestureDetector) = onMapMove()
        override fun onMove(detector: MoveGestureDetector): Boolean = false
        override fun onMoveEnd(detector: MoveGestureDetector) {}
    }

    val onDoubleTapListener = object : StandardGestureDetector.SimpleStandardOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            onDoubleTap()
            return super.onDoubleTap(e)
        }
    }

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapInitOptionsFactory = { mapInitContext ->
            MapInitOptions(context = mapInitContext, styleUri = styleUri)
        },
        locationComponentSettings = LocationComponentSettings.Builder(
            DefaultSettingsProvider.createDefault2DPuck(LocalContext.current, true)
        ).setEnabled(true).build()
    ) {
        MapEffect {
            mapViewCallback(it)
            it.compass.addCompassClickListener(onCompassClicked)
            it.gestures.addOnMoveListener(onMoveListener)


        }
    }
}

