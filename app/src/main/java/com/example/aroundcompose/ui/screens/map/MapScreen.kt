package com.example.aroundcompose.ui.screens.map

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.views.CoinView
import com.example.aroundcompose.ui.common.views.SearchView
import com.example.aroundcompose.ui.screens.map.location_service.LocationService
import com.example.aroundcompose.ui.screens.map.models.MapEvent
import com.example.aroundcompose.ui.screens.map.models.MapViewState
import com.example.aroundcompose.ui.screens.map.views.MapBtn
import com.example.aroundcompose.ui.screens.map.views.MyMapboxMap
import com.example.aroundcompose.ui.screens.map.views.MyMapboxMap.MapConstant
import com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views.EventBottomSheet
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.extension.style.expressions.dsl.generated.rgb
import com.mapbox.maps.extension.style.expressions.generated.Expression
import com.mapbox.maps.extension.style.layers.addLayer
import com.mapbox.maps.extension.style.layers.generated.fillLayer
import com.mapbox.maps.plugin.animation.easeTo
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.plugin.locationcomponent.location
import kotlinx.coroutines.launch

class MapScreen(private val viewModel: MapViewModel) {
    private var animatorListener: Animator.AnimatorListener? = null
    private val coins = mutableIntStateOf(0)

    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsState()
        val context = LocalContext.current
        var mapView: MapView? by remember { mutableStateOf(null) }
        var searchText by remember { mutableStateOf("") }
        var positionChangedListener by remember {
            mutableStateOf(OnIndicatorPositionChangedListener {})
        }
        var isEventSheetShowed by remember { mutableStateOf(false) }
        val animatedRotation = remember { Animatable(0F) }
        val scope = rememberCoroutineScope()
        var compassIconId by remember { mutableIntStateOf(R.drawable.ic_navigate) }

        MyMapboxMap(mapViewCallback = {
            mapView = it
            viewModel.obtainEvent(MapEvent.Init)
        }, onMoveListener = {
            removeCameraFollow(mapView, positionChangedListener)
            viewModel.obtainEvent(MapEvent.UpdateCameraPosition(it))
        }, onRotate = {
            compassIconId = R.drawable.ic_compass
            scope.launch { animatedRotation.animateTo(it, tween(0)) }
        }).Create()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(JetAroundTheme.margins.mapScreenMargin),
        ) {
            Row {
                SearchView(modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                    restoredValue = searchText,
                    onClick = {
                        isEventSheetShowed = true
                    }) {
                    viewModel.obtainEvent(MapEvent.EditSearchText(it))
                }
                CoinView(modifier = Modifier, value = coins.intValue)
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 28.dp),
                horizontalArrangement = Arrangement.End
            ) {
                //MyScaleBar(scaleBarValue ?: 0f)
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.size(width = 0.dp, height = 0.dp))
                    Column {
                        Spacer(modifier = Modifier.size(width = 0.dp, height = 60.dp))
                        MapBtn(iconId = R.drawable.ic_plus) { viewModel.obtainEvent(MapEvent.ZoomLevelPlus) }
                        Spacer(modifier = Modifier.size(width = 0.dp, height = 12.dp))
                        MapBtn(iconId = R.drawable.ic_minus) { viewModel.obtainEvent(MapEvent.ZoomLevelMinus) }
                    }
                    MapBtn(iconId = compassIconId, rotation = animatedRotation.value) {
                        onCompassClick(mapView, positionChangedListener, onNorthFaced = {
                            scope.launch { animatedRotation.animateTo(0F) }
                            compassIconId = R.drawable.ic_navigate
                        }, onZoomChanged = {
                            viewModel.obtainEvent(
                                MapEvent.UpdateCameraPosition(mapView?.getMapboxMap()?.cameraState!!)
                            )
                        })
                    }
                }
            }
        }


        if (isEventSheetShowed) EventBottomSheet { isEventSheetShowed = false }


        when (val state = viewState) {
            is MapViewState.Init -> {
                searchText = state.searchText
                initMap(mapView, state.lastLocation)
                positionChangedListener = OnIndicatorPositionChangedListener {
                    mapView?.getMapboxMap()?.setCamera(CameraOptions.Builder().center(it).build())
                }
                viewModel.obtainEvent(MapEvent.StartService)
                if (!LocationService.isRunning) startService(context)
            }

            is MapViewState.CellsCaptured -> {
                paintCells(mapView, state.paintedCells)
            }

            is MapViewState.ZoomLevelUpdated -> {
                mapView?.getMapboxMap()?.easeTo(
                    cameraOptions = CameraOptions.Builder().center(LocationService.lastLocation)
                        .zoom(state.zoomLevel).build()
                )
            }
        }
    }

    private fun initMap(mapView: MapView?, lastLocation: Point?) {
        val zoomLevel = if (lastLocation == null) 0.0 else MapConstant.ZOOM_LEVEL

        mapView?.getMapboxMap()?.setCamera(
            CameraOptions.Builder().zoom(zoomLevel).pitch(0.0).bearing(0.0).center(
                lastLocation
            ).build()
        )
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
            it.removeStyleLayer(MapConstant.PAINTED_CELLS_LAYER_ID)
            it.removeStyleLayer(MapConstant.ALL_CELLS_LAYER_ID)

            it.addLayer(fillLayer(MapConstant.PAINTED_CELLS_LAYER_ID, MapConstant.SOURCE_ID) {
                sourceLayer(MapConstant.TILE_ID)
                filter(paintedCellsFilter)
                fillOpacity(0.2)
                fillColor(rgb(0.0, 176.0, 255.0))
                fillOutlineColor(Color.TRANSPARENT)
            })

            it.addLayer(fillLayer(MapConstant.ALL_CELLS_LAYER_ID, MapConstant.SOURCE_ID) {
                sourceLayer(MapConstant.TILE_ID)
                filter(unpaintedCellsFilter)
                fillOpacity(0.05)
                fillColor(Color.TRANSPARENT)
                fillOutlineColor(rgb(0.0, 0.0, 0.0))
            })
        }
    }

    private fun onCompassClick(
        mapView: MapView?,
        positionChangedListener: OnIndicatorPositionChangedListener,
        onNorthFaced: () -> Unit,
        onZoomChanged: () -> Unit,
    ) {
        animatorListener = animatorListener ?: object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) {
                cameraFollow(mapView, positionChangedListener)
                onZoomChanged()
            }

            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationRepeat(p0: Animator) {}
        }

        val mapboxMap = mapView?.getMapboxMap()

        if (mapboxMap?.cameraState?.bearing != 0.0 || mapboxMap.cameraState.pitch != 0.0) {
            mapboxMap?.easeTo(CameraOptions.Builder().bearing(0.0).pitch(0.0).build())
            onNorthFaced()

        } else {
            mapboxMap.easeTo(
                cameraOptions = CameraOptions.Builder().center(LocationService.lastLocation)
                    .zoom(maxOf(mapboxMap.cameraState.zoom, MapConstant.ZOOM_LEVEL)).build(),
                animatorListener = animatorListener
            )
        }
    }

    private fun cameraFollow(
        mapView: MapView?,
        positionChangedListener: OnIndicatorPositionChangedListener,
    ) {
        mapView?.location?.addOnIndicatorPositionChangedListener(positionChangedListener)
    }

    private fun removeCameraFollow(
        mapView: MapView?,
        positionChangedListener: OnIndicatorPositionChangedListener,
    ) {
        mapView?.location?.removeOnIndicatorPositionChangedListener(positionChangedListener)
    }
}