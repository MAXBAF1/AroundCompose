package com.example.aroundcompose.screens.map.models

import android.content.Context
import com.mapbox.maps.MapView

sealed class MapEvent {
    object Init : MapEvent()
    object StartService : MapEvent()
}
