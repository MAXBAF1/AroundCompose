package com.example.aroundcompose.screens.map

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.screens.map.location_service.LocationService
import com.example.aroundcompose.screens.map.models.MapEvent
import com.example.aroundcompose.screens.map.models.MapViewState
import com.example.aroundcompose.screens.map.views.ALL_CELLS_LAYER_ID
import com.example.aroundcompose.screens.map.views.MapBtn
import com.example.aroundcompose.screens.map.views.MyMapboxMap
import com.example.aroundcompose.screens.map.views.PAINTED_CELLS_LAYER_ID
import com.example.aroundcompose.screens.map.views.SOURCE_ID
import com.example.aroundcompose.screens.map.views.TILE_ID
import com.example.aroundcompose.screens.map.views.ZOOM_LEVEL
import com.example.aroundcompose.utils.toPoint
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.extension.style.expressions.dsl.generated.rgb
import com.mapbox.maps.extension.style.expressions.generated.Expression
import com.mapbox.maps.extension.style.layers.addLayer
import com.mapbox.maps.extension.style.layers.generated.fillLayer
import com.mapbox.maps.plugin.animation.easeTo
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.plugin.locationcomponent.location

class MapScreen(private val viewModel: MapViewModel) {
    private var positionChangedListener: OnIndicatorPositionChangedListener? = null
    private var animatorListener: Animator.AnimatorListener? = null

    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsState()
        val context = LocalContext.current
        var mapView: MapView? by remember { mutableStateOf(null) }

        MyMapboxMap(mapViewCallback = {
            mapView = it
            viewModel.obtainEvent(MapEvent.Init)
        },
            onMapMove = { removeCameraFollow(mapView) },
            onCompassClicked = { onCompassClick(mapView) })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.size(width = 0.dp, height = 60.dp)) // FIXME Заглушка
            Column {
                Spacer(modifier = Modifier.size(width = 0.dp, height = 60.dp))
                MapBtn(iconId = R.drawable.ic_plus) { }
                Spacer(modifier = Modifier.size(width = 0.dp, height = 12.dp))
                MapBtn(iconId = R.drawable.ic_minus) { }
            }
            MapBtn(iconId = R.drawable.ic_navigate) { }
        }


        when (viewState) {
            is MapViewState.Init -> {
                initMap(mapView, (viewState as MapViewState.Init).lastLocation)
                viewModel.obtainEvent(MapEvent.StartService)
                if (!LocationService.isRunning) startService(context)
            }

            is MapViewState.CellsCaptured -> {
                paintCells(mapView, (viewState as MapViewState.CellsCaptured).paintedCells)
            }
        }
    }

    private fun initMap(mapView: MapView?, lastLocation: Point?) {
        val centerPoint = LocationService.lastLocation?.toPoint() ?: lastLocation
        val zoomLevel = if (centerPoint == null) 0.0 else ZOOM_LEVEL

        mapView?.getMapboxMap()?.setCamera(
            CameraOptions.Builder().zoom(zoomLevel).pitch(0.0).bearing(0.0).center(
                centerPoint
            ).build()
        )
        mapView?.compass?.fadeWhenFacingNorth = false
    }

    private fun startService(context: Context) {
        val intent = Intent(context, LocationService::class.java)
        context.startForegroundService(intent)
    }

    private fun paintCells(mapView: MapView?, paintedCells: List<String>) {
        val mapboxMap = mapView?.getMapboxMap()
        val paintedCellsFilter =
            Expression.inExpression(Expression.get("id"), literal(paintedCells))
        val unpaintedCellsFilter = Expression.not(paintedCellsFilter)

        mapboxMap?.getStyle {
            it.removeStyleLayer(PAINTED_CELLS_LAYER_ID)
            it.removeStyleLayer(ALL_CELLS_LAYER_ID)

            it.addLayer(fillLayer(PAINTED_CELLS_LAYER_ID, SOURCE_ID) {
                sourceLayer(TILE_ID)
                filter(paintedCellsFilter)
                fillOpacity(0.2)
                fillColor(rgb(0.0, 176.0, 255.0))
                fillOutlineColor(Color.TRANSPARENT)
            })

            it.addLayer(fillLayer(ALL_CELLS_LAYER_ID, SOURCE_ID) {
                sourceLayer(TILE_ID)
                filter(unpaintedCellsFilter)
                fillOpacity(0.05)
                fillColor(Color.TRANSPARENT)
                fillOutlineColor(rgb(0.0, 0.0, 0.0))
            })
        }
    }

    private fun onCompassClick(mapView: MapView?) {
        animatorListener = animatorListener ?: object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) = cameraFollow(mapView)
            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationRepeat(p0: Animator) {}
        }

        val mapboxMap = mapView?.getMapboxMap()

        if (mapboxMap?.cameraState?.bearing == 0.0 && mapboxMap.cameraState.pitch == 0.0) {
            mapboxMap.flyTo(
                cameraOptions = CameraOptions.Builder()
                    .center(LocationService.lastLocation?.toPoint())
                    .zoom(maxOf(mapboxMap.cameraState.zoom, ZOOM_LEVEL)).build(),
                animatorListener = animatorListener
            )
        } else {
            mapboxMap?.easeTo(CameraOptions.Builder().bearing(0.0).pitch(0.0).build())
        }
    }

    private fun cameraFollow(mapView: MapView?) {
        positionChangedListener = positionChangedListener ?: OnIndicatorPositionChangedListener {
            mapView?.getMapboxMap()?.setCamera(CameraOptions.Builder().center(it).build())
        }
        mapView?.location?.addOnIndicatorPositionChangedListener(positionChangedListener!!)
        mapView?.compass?.fadeWhenFacingNorth = true
    }

    private fun removeCameraFollow(mapView: MapView?) {
        positionChangedListener?.let {
            mapView?.location?.removeOnIndicatorPositionChangedListener(it)
        }
        mapView?.compass?.fadeWhenFacingNorth = false
    }
}