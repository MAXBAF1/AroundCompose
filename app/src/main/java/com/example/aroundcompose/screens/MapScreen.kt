package com.example.aroundcompose.screens

import android.Manifest
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.aroundcompose.LocationService
import com.example.aroundcompose.R
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.DefaultSettingsProvider
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.locationcomponent.generated.LocationComponentSettings
import dev.shreyaspatil.permissionflow.compose.rememberPermissionState
import kotlinx.coroutines.launch

@Composable
@OptIn(MapboxExperimental::class)
fun MapScreen() {
    val coroutineScope = rememberCoroutineScope()
    val currentLocation: Point? by remember { mutableStateOf(null) }
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(1.0)
            pitch(0.0)
        }
    }


    if (currentLocation != null) {
        //currentLocation = LocationService.getCurrentLocation(context)
        val mapAnimationOptions = MapAnimationOptions.Builder().duration(1500L).build()
        mapViewportState.flyTo(
            CameraOptions.Builder().center(currentLocation).zoom(12.0).build(),
            mapAnimationOptions
        )
    }

    val gesturesSettings by remember {
        mutableStateOf(DefaultSettingsProvider.defaultGesturesSettings)
    }
    val styleUri = stringResource(id = R.string.globe3dKey)

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = mapViewportState,
        gesturesSettings = gesturesSettings,
        mapInitOptionsFactory = { context ->
            MapInitOptions(
                context = context,
                styleUri = styleUri
            )
        },
        locationComponentSettings = LocationComponentSettings.Builder(
            DefaultSettingsProvider.createDefault2DPuck(LocalContext.current, true)
        ).setEnabled(true).build()
    ) {

    }
}