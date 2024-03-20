package com.example.aroundcompose.ui.screens.map.models

import com.example.aroundcompose.ui.screens.map.views.MyMapboxMap
import com.mapbox.geojson.Point
import com.mapbox.maps.EdgeInsets

data class MutableCameraState(
    var center: Point? = null,
    val padding: EdgeInsets? = null,
    var zoom: Double = MyMapboxMap.MapConstante.ZOOM_LEVEL,
    val bearing: Double = 0.0,
    val pitch: Double = 0.0,
)