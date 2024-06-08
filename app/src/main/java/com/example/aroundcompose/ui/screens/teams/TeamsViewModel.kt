package com.example.aroundcompose.ui.screens.teams

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.enums.Teams
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
    private var currentTeam = Teams.NONE
    private val userInfoService = UserInfoService(tokenManager)

    override fun obtainEvent(viewEvent: TeamsEvent) {
        when (viewEvent) {
            is TeamsEvent.ChangeTeam -> changeTeam(viewEvent.team)
            TeamsEvent.ClickNextBtn -> clickNextBtn()
        }
    }

    private fun changeTeam(currentTeam: Teams) {
        this.currentTeam = currentTeam
        viewState.update {
            it.copy(currentTeam = currentTeam, isEnableNextBtn = currentTeam != Teams.NONE)
        }
    }

    private fun clickNextBtn() {
        viewModelScope.launch {
            when (userInfoService.patchMe(teamId = currentTeam.value)) {
                HttpStatusCode.OK -> viewState.update { it.copy(toNextScreen = true) }
                else -> viewState.update { it.copy(toNextScreen = false) } // FIXME сделать обработку ошибок
            }
        }
    }
}