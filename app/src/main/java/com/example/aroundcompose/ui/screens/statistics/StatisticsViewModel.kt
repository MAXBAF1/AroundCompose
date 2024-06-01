package com.example.aroundcompose.ui.screens.statistics

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.data.services.StatisticService
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsEvent
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<StatisticsViewState, StatisticsEvent>(initialState = StatisticsViewState()) {
    private val statisticsService = StatisticService(tokenManager)
    private val userInfoService = UserInfoService(tokenManager)

    private var serverList: HashSet<Pair<Boolean, FriendDTO>>? = hashSetOf()
    private var friendsList: List<Pair<Boolean, FriendDTO>>? = listOf()
    private var userStatistic: FriendDTO? = FriendDTO()
    private val teamsProgressMap: HashMap<Int, Float> = hashMapOf()

    private var totalScore: Float = 0F
    private var currentButton: Boolean = true
    private var currentUserId: Int = 0

    init {
        setStatisticInfo()
    }

    override fun obtainEvent(viewEvent: StatisticsEvent) {
        when (viewEvent) {
            StatisticsEvent.OnListBtnClick -> clickListBtn()
        }
    }

    private fun setStatisticInfo() {
        viewModelScope.launch {
            currentUserId = userInfoService.getMe()?.id ?: return@launch

            statisticsService.getAllTeams()?.forEach { teamDTO ->
                teamsProgressMap[teamDTO.id] = teamDTO.score.toFloat()
                totalScore += teamDTO.score.toFloat()
            }

            teamsProgressMap.values.forEachIndexed { index, score ->
                teamsProgressMap[index + 1] = (score / totalScore) * 100
            }

            totalScore = 0F

            updateListStatistic(currentButton)

            viewState.update { viewState ->
                viewState.copy(teamsProgressMap = teamsProgressMap.toMap(),
                    friendsList = friendsList?.toList() ?: return@launch,
                    serverList = serverList?.sortedByDescending {
                        it.second.score
                    } ?: return@launch)
            }
        }
    }

    private fun clickListBtn() {
        viewModelScope.launch {
            currentButton = !currentButton

            updateListStatistic(currentButton)

            viewState.update { viewState ->
                viewState.copy(currentButton = currentButton,
                    friendsList = friendsList?.toList() ?: return@launch,
                    serverList = serverList?.sortedByDescending {
                        it.second.score
                    } ?: return@launch)
            }
        }
    }

    private suspend fun updateListStatistic(currentButton: Boolean) {
        if (currentButton) {
            serverList = statisticsService.getTopUsers()?.map {
                (currentUserId == it.id) to it
            }?.toHashSet()
            userStatistic = statisticsService.getUserStatistic()

            userStatistic?.let { serverList?.add(true to it) }
        } else {
            friendsList = statisticsService.getUserFriends()?.map {
                false to it
            }?.toList()?.sortedByDescending {
                it.second.score
            }
        }
    }
}