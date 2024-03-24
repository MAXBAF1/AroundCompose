package com.example.aroundcompose.ui.screens.authorization

import com.example.aroundcompose.models.BaseViewModel
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
    BaseViewModel<AuthorizationViewState, AuthorizationEvent>(
        initialState = AuthorizationViewState.RestoreInputsData()
    ) {
    override fun obtainEvent(viewEvent: AuthorizationEvent) {
        when (viewEvent) {
            AuthorizationEvent.RestoreInputs -> {}
            is AuthorizationEvent.InputTextChange -> {}

            AuthorizationEvent.ClickLoginBtn -> {}
            AuthorizationEvent.ClickLoginGoogleBtn -> {}
            AuthorizationEvent.ClickLoginVkBtn -> {}
            AuthorizationEvent.ClickForgotPasswordBtn -> {}
            AuthorizationEvent.ClickRegistrationBtn -> {}
        }
    }
}