package com.example.aroundcompose.screens.map.views

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.aroundcompose.R
import com.example.aroundcompose.screens.map.models.MapViewState
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.DefaultSettingsProvider
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.locationcomponent.generated.LocationComponentSettings

@OptIn(MapboxExperimental::class)
@Composable
fun MyMapboxMap(viewportState: MapViewportState) {
    val styleUri = stringResource(id = R.string.globe3dKey)

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = viewportState,
        mapInitOptionsFactory = { mapInitContext ->
            MapInitOptions(context = mapInitContext, styleUri = styleUri)
        },
        locationComponentSettings = LocationComponentSettings.Builder(
            DefaultSettingsProvider.createDefault2DPuck(LocalContext.current, true)
        ).setEnabled(true).build()
    ) {
    }
}