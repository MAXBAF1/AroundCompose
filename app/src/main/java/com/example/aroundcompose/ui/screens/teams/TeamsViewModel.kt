package com.example.aroundcompose.ui.screens.teams

import com.example.aroundcompose.models.BaseViewModel
import com.example.aroundcompose.ui.screens.teams.models.TeamsEvent
import com.example.aroundcompose.ui.screens.teams.models.TeamsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor() : BaseViewModel<TeamsViewState, TeamsEvent>(
    initialState = TeamsViewState.RestoreSelectedTeams()
) {
    override fun obtainEvent(viewEvent: TeamsEvent) {
        when (viewEvent) {
            is TeamsEvent.ChangeTeam -> {  }
            TeamsEvent.ClickBtnNext -> {  }
        }
    }
}