package com.example.aroundcompose.ui.screens.map

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.views.CoinView
import com.example.aroundcompose.ui.common.views.SearchView
import com.example.aroundcompose.ui.screens.map.location_service.LocationService
import com.example.aroundcompose.ui.screens.map.models.MapEvent
import com.example.aroundcompose.ui.screens.map.views.MapBtn
import com.example.aroundcompose.ui.screens.map.views.MyMapboxMap
import com.example.aroundcompose.ui.screens.map.views.MyMapboxMap.MapConstant
import com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views.EventBottomSheet
import com.example.aroundcompose.ui.screens.map.views.event_info_sheet.EventInfoSheet
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

class MapScreen(
    private val viewModel: MapViewModel,
    //private val onEventClick: (EventData) -> Unit,
) {
    private var animatorListener: Animator.AnimatorListener? = null
    private val coins = mutableIntStateOf(0)

    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsState()
        var mapView: MapView? by remember { mutableStateOf(null) }
        val searchText by remember { mutableStateOf("") }
        val positionChangedListener by remember {
            mutableStateOf(OnIndicatorPositionChangedListener {
                mapView?.getMapboxMap()?.setCamera(CameraOptions.Builder().center(it).build())
            })
        }
        var rotation by remember { mutableFloatStateOf(0F) }
        var compassIconId by remember { mutableIntStateOf(R.drawable.ic_navigate) }
        val context = LocalContext.current

        MyMapboxMap(mapViewCallback = { mv ->
            mapView = mv
            initMap(mapView, viewState.lastLocation)
            viewModel.obtainEvent(MapEvent.SetupService)
            if (!LocationService.isRunning) startService(context)
        }, onMoveListener = {
            removeCameraFollow(mapView, positionChangedListener)
            viewModel.obtainEvent(MapEvent.UpdateCameraPosition(it))
        }, onRotate = {
            compassIconId = R.drawable.ic_compass
            rotation = it
        }).Create()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(JetAroundTheme.margins.mapScreenMargin),
        ) {
            Spacer(
                Modifier.windowInsetsTopHeight(WindowInsets.systemBars)
            )

            Row {
                SearchView(modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                    value = searchText,
                    onClick = { viewModel.obtainEvent(MapEvent.ShowEventSheet(true)) }) {
                    TODO()
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
                        MapBtn(iconId = R.drawable.ic_plus, onClick = {
                            viewModel.obtainEvent(MapEvent.PlusZoomLevel)
                        })
                        Spacer(modifier = Modifier.size(width = 0.dp, height = 12.dp))
                        MapBtn(iconId = R.drawable.ic_minus,
                            onClick = { viewModel.obtainEvent(MapEvent.MinusZoomLevel) })
                    }
                    MapBtn(iconId = compassIconId, rotation = rotation) {
                        onCompassClick(mapView, positionChangedListener, onNorthFaced = {
                            rotation = 0F
                            compassIconId = R.drawable.ic_navigate
                        }, onZoomChanged = {
                            mapView?.getMapboxMap()?.cameraState?.let {
                                viewModel.obtainEvent(MapEvent.UpdateCameraPosition(it))
                            }
                        })
                    }
                }
            }
            Spacer(
                Modifier.windowInsetsBottomHeight(WindowInsets.systemBars)
            )
        }

        paintCells(mapView, viewState.paintedCells.map { it.id })

        LaunchedEffect(key1 = viewState.zoomLevel) {
            updateZoomLevel(mapView, viewState.zoomLevel)
        }

        if (viewState.isEventSheetShowed) {
            EventBottomSheet(
                events = viewState.events,
                onDismissRequest = { viewModel.obtainEvent(MapEvent.ShowEventSheet(false)) },
                onEventClick = { viewModel.obtainEvent(MapEvent.ShowEventInfoSheet(true, it)) },
            ).Create()
        }

        if (viewState.isEventInfoSheetShowed && viewState.chosenEvent != null) {
            EventInfoSheet(
                onDismissRequest = { viewModel.obtainEvent(MapEvent.ShowEventInfoSheet(false)) },
                eventData = viewState.chosenEvent!!,
            ).Create()
        }
    }

    private fun startService(context: Context) {
        val intent = Intent(context, LocationService::class.java)
        context.startForegroundService(intent)
    }

    private fun updateZoomLevel(mapView: MapView?, zoomLevel: Double) {
        mapView?.getMapboxMap()?.easeTo(
            cameraOptions = CameraOptions.Builder().zoom(zoomLevel).build()
        )
    }

    private fun initMap(mapView: MapView?, lastLocation: Point?) {
        val zoomLevel = if (lastLocation == null) 0.0 else MapConstant.ZOOM_LEVEL

        mapView?.getMapboxMap()?.setCamera(
            CameraOptions.Builder().zoom(zoomLevel).pitch(0.0).bearing(0.0).center(
                lastLocation
            ).build()
        )
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
