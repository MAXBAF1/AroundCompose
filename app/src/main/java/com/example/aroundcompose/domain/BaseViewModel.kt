package com.example.aroundcompose.domain

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State, Action, Event>(
    initialState: State? = null,
    initialAction: Action,
) : ViewModel() {
    private val _viewState = MutableStateFlow(initialState)
    private val _viewAction = MutableStateFlow(initialAction)

    var viewState: StateFlow<State?> = _viewState
    var viewAction: StateFlow<Action> = _viewAction

    protected fun setViewState(state: State?) {
        _viewState.value = state
    }

    protected fun setActionState(action: Action) {
        _viewAction.value = action
    }

    @Composable
    abstract fun obtainEvent(viewEvent: Event)
}