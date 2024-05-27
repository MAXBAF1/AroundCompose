package com.example.aroundcompose.ui.screens.teams

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.services.AuthenticationService
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.registration.models.RegistrationFields
import com.example.aroundcompose.ui.screens.teams.models.TeamsEvent
import com.example.aroundcompose.ui.screens.teams.models.TeamsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<TeamsViewState, TeamsEvent>(
        initialState = TeamsViewState()
    ) {

    private var fields = RegistrationFields()
    private var currentTeam = 0
    private val networkService = AuthenticationService(tokenManager)

    override fun obtainEvent(viewEvent: TeamsEvent) {
        when (viewEvent) {
            is TeamsEvent.GetRegistrationInfo -> {
                fields = viewEvent.fields
            }

            is TeamsEvent.ChangeTeam -> {
                currentTeam = viewEvent.teamId

                viewState.update {
                    it.copy(
                        currentTeam = currentTeam,
                        isEnableNextBtn = currentTeam != 0
                    )
                }
            }

            TeamsEvent.ClickBtnNext -> {
                viewModelScope.launch {
                    when (networkService.register(authFields = fields, teamId = currentTeam)) {
                        HttpStatusCode.OK -> viewState.update { it.copy(toNextScreen = true) }
                        else -> viewState.update { it.copy(toNextScreen = false) } // FIXME сделать обработку ошибок
                    }
                }
            }
        }
    }
}