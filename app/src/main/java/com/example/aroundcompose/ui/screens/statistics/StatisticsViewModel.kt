package com.example.aroundcompose.ui.screens.statistics

import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsEvent
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsViewState
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class StatisticsViewModel @Inject constructor() :
    BaseViewModel<StatisticsViewState, StatisticsEvent>(initialState = StatisticsViewState()) {
    private val serverList: List<FriendDTO> = listOf()
    private val friendsList: List<FriendDTO> = listOf()
    private val userStatistic: FriendDTO = FriendDTO()
    private val teamsProgressList: List<Float> = listOf()

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

            StatisticsEvent.GetStatisticInfo -> {

            }
        }
    }
}