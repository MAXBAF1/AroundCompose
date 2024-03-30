package com.example.aroundcompose.ui.screens.registration

import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.registration.models.RegistrationEvent
import com.example.aroundcompose.ui.screens.registration.models.RegistrationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() :
    BaseViewModel<RegistrationViewState, RegistrationEvent>(
        initialState = RegistrationViewState.RestoreInputsData()
    ) {
    override fun obtainEvent(viewEvent: RegistrationEvent) {
        when (viewEvent) {
            is RegistrationEvent.InputTextChange -> {}
            RegistrationEvent.RestoreInputs -> {}

            RegistrationEvent.ClickBackBtn -> {}
            RegistrationEvent.ClickNextBtn -> {}
        }
    }
}