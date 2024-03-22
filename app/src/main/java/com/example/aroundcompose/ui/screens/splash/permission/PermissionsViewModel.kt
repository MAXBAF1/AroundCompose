package com.example.aroundcompose.ui.screens.splash.permission

import com.example.aroundcompose.models.BaseViewModel
import com.example.aroundcompose.ui.screens.splash.permission.models.PermissionsEvent
import com.example.aroundcompose.ui.screens.splash.permission.models.PermissionsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PermissionsViewModel @Inject constructor() :
    BaseViewModel<PermissionsViewState, PermissionsEvent>(initialState = PermissionsViewState.CheckGranted) {

    override fun obtainEvent(viewEvent: PermissionsEvent) {
        when (viewEvent) {
            PermissionsEvent.NotGranted -> viewState.update { PermissionsViewState.NotGranted }
            PermissionsEvent.DismissPermission -> viewState.update { PermissionsViewState.PermissionDismissed }
        }
    }
}