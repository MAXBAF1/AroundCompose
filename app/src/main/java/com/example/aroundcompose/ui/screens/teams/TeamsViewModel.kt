package com.example.aroundcompose.ui.screens.teams

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.teams.models.TeamsEvent
import com.example.aroundcompose.ui.screens.teams.models.TeamsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<TeamsViewState, TeamsEvent>(initialState = TeamsViewState()) {
    private var currentTeamId = -1
    private val userInfoService = UserInfoService(tokenManager)

    override fun obtainEvent(viewEvent: TeamsEvent) {
        when (viewEvent) {
            is TeamsEvent.ChangeTeam -> {
                currentTeamId = viewEvent.teamId

                viewState.update {
                    it.copy(
                        currentTeamId = currentTeamId, isEnableNextBtn = currentTeamId != -1
                    )
                }
            }

            TeamsEvent.ClickBtnNext -> {
                viewModelScope.launch {
                    when (userInfoService.patchMe(teamId = currentTeamId)) {
                        HttpStatusCode.OK -> viewState.update { it.copy(toNextScreen = true) }
                        else -> viewState.update { it.copy(toNextScreen = false) } // FIXME сделать обработку ошибок
                    }
                }
            }
        }
    }
}