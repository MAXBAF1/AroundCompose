package com.example.aroundcompose.ui.screens.map

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.MyInfoSingleton
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.CellDTO
import com.example.aroundcompose.data.models.EventDTO
import com.example.aroundcompose.data.services.CellsService
import com.example.aroundcompose.data.services.EventsService
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.di.NotEncryptedSharedPref
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.common.models.EventData
import com.example.aroundcompose.ui.screens.map.location_service.LocationService
import com.example.aroundcompose.ui.screens.map.models.MapEvent
import com.example.aroundcompose.ui.screens.map.models.MapViewState
import com.example.aroundcompose.ui.screens.map.models.MutableCameraState
import com.example.aroundcompose.ui.screens.map.views.MyMapboxMap.MapConstant
import com.example.aroundcompose.utils.toMutable
import com.mapbox.geojson.Point
import com.uber.h3core.H3Core
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    @NotEncryptedSharedPref private val sharedPreferences: SharedPreferences,
    private val tokenManager: TokenManager,
) : BaseViewModel<MapViewState, MapEvent>(initialState = MapViewState()) {
    private val cellsService = CellsService(tokenManager)
    private val eventsService = EventsService(tokenManager)
    private val userInfoService = UserInfoService(tokenManager)

    private val paintedCells: ArrayList<CellDTO> = arrayListOf()
    private var lastCell: String = ""
    private val coins = 0
    private var searchText = ""
    private var cameraState = MutableCameraState()
    private var isEventSheetShowed = false
    private var isEventInfoSheetShowed = false


    private var events: List<EventDTO> = listOf()

    override fun obtainEvent(viewEvent: MapEvent) {
        when (viewEvent) {
            is MapEvent.Init -> init()
            is MapEvent.EditSearchText -> searchText = viewEvent.text
            MapEvent.MinusZoomLevel -> updateZoomLevel(viewEvent)
            MapEvent.PlusZoomLevel -> updateZoomLevel(viewEvent)
            is MapEvent.UpdateCameraPosition -> cameraState = viewEvent.cameraState.toMutable()
            is MapEvent.ShowEventSheet -> showEventSheet(viewEvent.show)
            is MapEvent.ShowEventInfoSheet -> showEventInfoSheet(viewEvent.show, viewEvent.event)
        }
    }

    private fun showEventInfoSheet(show: Boolean, chosenEvent: EventData? = null) {
        showEventSheet(!show)
        isEventInfoSheetShowed = show
        viewState.update {
            it.copy(isEventInfoSheetShowed = isEventInfoSheetShowed, chosenEvent = chosenEvent)
        }
    }

    private fun showEventSheet(show: Boolean) {
        isEventSheetShowed = show
        viewModelScope.launch {
            events = eventsService.getAllEvents() ?: return@launch

            viewState.update { it.copy(isEventSheetShowed = isEventSheetShowed, events = events) }
        }
    }

    private fun init() {
        if (MyInfoSingleton.myInfo == null) {
            viewModelScope.launch {
                userInfoService.getMe()?.let { myInfo ->
                    MyInfoSingleton.myInfo = myInfo
                    viewState.update { it.copy(myInfo = myInfo) }
                }
            }
        } else viewState.update { it.copy(myInfo = MyInfoSingleton.myInfo) }

        this.cameraState.center = getLastLocationFromSharedPref()
        LocationService.lastLocation = cameraState.center
        viewState.update {
            it.copy(lastLocation = cameraState.center, searchText = searchText, coins = coins)
        }

        setupService()
    }

    private fun updateZoomLevel(zoomLevelUpdateEvent: MapEvent) {
        if (zoomLevelUpdateEvent == MapEvent.MinusZoomLevel) {
            cameraState.zoom -= MapConstant.ZOOM_LEVEL_DELTA
        } else if (zoomLevelUpdateEvent == MapEvent.PlusZoomLevel) {
            cameraState.zoom += (MapConstant.ZOOM_LEVEL_DELTA)
        }
        viewState.update { it.copy(zoomLevel = cameraState.zoom) }
    }

    private fun getLastLocationFromSharedPref(): Point? {
        return Point.fromJson(
            sharedPreferences.getString(LocationService.LAST_LOCATION_ID, null) ?: ""
        )
    }

    private fun setupService() {
        val h3 = H3Core.newSystemInstance()
        val accessToken = tokenManager.getTokens()?.accessToken ?: return

        viewModelScope.launch {
            val newCells = cellsService.getAll() ?: return@launch

            viewState.update { it.copy(paintedCells = newCells) }
        }

        val receivedCellsChannel = Channel<CellDTO>()
        viewModelScope.launch {
            //JwtRequestManager.receiveCellsFromWebSocket(receivedCellsChannel, accessToken)
        }

        viewModelScope.launch {
            paintedCells.addAll(receivedCellsChannel.toList())
            viewState.update { it.copy(paintedCells = paintedCells.toList()) }
        }

        LocationService.onLocationResult = { location ->
            val newCell = h3.latLngToCellAddress(
                location.latitude, location.longitude, MapConstant.H3_RESOLUTION
            )

            if (lastCell != newCell) {
                viewModelScope.launch {
                    //JwtRequestManager.sendCellToWebSocket(CellDTO(newCell), accessToken)
                    lastCell = newCell
                }
            }

            /* if (!paintedCells.contains(newCell)) {
                 val neighborsCells = h3.gridDisk(newCell, 2)
                 neighborsCells.removeAll(paintedCells)
                 val newCells = neighborsCells.take(1)

                 paintedCells.addAll(newCells)
                 viewState.update { it.copy(paintedCells = paintedCells.toList()) }
             }*/
        }
    }
}