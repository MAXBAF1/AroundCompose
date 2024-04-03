package com.example.aroundcompose.ui.screens.registration

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.registration.models.RegistrationEvent
import com.example.aroundcompose.ui.screens.registration.models.RegistrationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() :
    BaseViewModel<RegistrationViewState, RegistrationEvent>(
        initialState = RegistrationViewState()
    ) {
    private var loginValue = ""
    private var emailValue = ""
    private var passwordValue = ""
    private var confirmPasswordValue = ""

    override fun obtainEvent(viewEvent: RegistrationEvent) {
        when (viewEvent) {
            is RegistrationEvent.ChangeFieldText -> {
                when (viewEvent.type) {
                    FieldType.LOGIN -> loginValue = viewEvent.text
                    FieldType.EMAIL -> emailValue = viewEvent.text
                    FieldType.PASSWORD -> passwordValue = viewEvent.text
                    FieldType.CONFIRM_PASSWORD -> confirmPasswordValue = viewEvent.text
                }

                viewState.update {
                    it.copy(
                        loginValue = loginValue,
                        emailValue = emailValue,
                        passwordValue = passwordValue,
                        confirmPasswordValue = confirmPasswordValue,
                        isEnabledNextBtn =
                                loginValue.isNotEmpty() &&
                                emailValue.isNotEmpty() &&
                                passwordValue.isNotEmpty() &&
                                confirmPasswordValue.isNotEmpty()
                    )
                }
            }

            RegistrationEvent.ClickNextBtn -> {}
        }
    }
}