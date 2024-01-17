package com.example.aroundcompose.utils

import android.location.Location
import com.mapbox.geojson.Point

fun Location.toPoint(): Point {
    return Point.fromLngLat(this.longitude, this.latitude)
}

fun Location.toJsonPoint(): String {
    return this.toPoint().toJson()
}