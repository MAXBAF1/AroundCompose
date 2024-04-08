package com.example.aroundcompose.ui.screens.teams

import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.teams.models.TeamsEvent
import com.example.aroundcompose.ui.screens.teams.models.TeamsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor() : BaseViewModel<TeamsViewState, TeamsEvent>(
    initialState = TeamsViewState()
) {
    private var currentTeam = Teams.NONE

    override fun obtainEvent(viewEvent: TeamsEvent) {
        when (viewEvent) {
            is TeamsEvent.ChangeTeam -> {
                currentTeam = viewEvent.teams

                viewState.update {
                    it.copy(
                        currentTeam = currentTeam,
                        isEnableNextBtn = currentTeam != Teams.NONE
                    )
                }
            }

            TeamsEvent.ClickBtnNext -> {  }
        }
    }
}