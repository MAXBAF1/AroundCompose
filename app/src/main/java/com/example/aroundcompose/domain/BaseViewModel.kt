package com.example.aroundcompose.domain

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State, Action, Event>(
    application: Application,
    initialState: State? = null,
    initialAction: Action,
) : AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()
    protected val viewState = MutableStateFlow(initialState)
    protected val viewAction = MutableStateFlow(initialAction)

    fun getViewState(): StateFlow<State?> = viewState
    fun getViewAction(): StateFlow<Action> = viewAction


    @Composable
    open fun composableObtainEvent(viewEvent: Event) {}

    open fun obtainEvent(viewEvent: Event) {}
}