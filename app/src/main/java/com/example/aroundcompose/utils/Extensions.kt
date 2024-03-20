package com.example.aroundcompose.utils

import android.location.Location
import com.example.aroundcompose.ui.screens.map.models.MutableCameraState
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraState

fun Location.toPoint(): Point {
    return Point.fromLngLat(this.longitude, this.latitude)
}

fun CameraState.toMutable(): MutableCameraState {
    return MutableCameraState(this.center, this.padding, this.zoom, this.bearing, this.pitch)
}