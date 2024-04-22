package com.example.aroundcompose.ui.screens.statistics

import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsEvent
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsViewState
import com.example.aroundcompose.ui.screens.statistics.models.UserData
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class StatisticsViewModel @Inject constructor() :
    BaseViewModel<StatisticsViewState, StatisticsEvent>(initialState = StatisticsViewState()) {
    private val serverList: List<UserData> = listOf()
    private val friendsList: List<UserData> = listOf()
    private val mapOfTeamsProgress: HashMap<Teams, Float> = hashMapOf(
        Teams.LIGHT_BLUE to 69F,
        Teams.YELLOW to 20F,
        Teams.PURPLE to 10F,
        Teams.BLUE to 1F
    )
    private var currentButton: Boolean = true

    override fun obtainEvent(viewEvent: StatisticsEvent) {
        when (viewEvent) {
            StatisticsEvent.OnListBtnClick -> {
                currentButton = !currentButton

                viewState.update {
                    it.copy(
                        currentButton = currentButton
                    )
                }
            }
        }
    }
}