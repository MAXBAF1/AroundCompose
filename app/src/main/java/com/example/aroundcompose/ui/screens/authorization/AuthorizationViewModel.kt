package com.example.aroundcompose.ui.screens.authorization

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
    BaseViewModel<AuthorizationViewState, AuthorizationEvent>(AuthorizationViewState()) {
    private var emailValue = ""
    private var passwordValue = ""

    override fun obtainEvent(viewEvent: AuthorizationEvent) {
        when (viewEvent) {
            is AuthorizationEvent.InputTextChange -> {
                when (viewEvent.type) {
                    FieldType.EMAIL -> emailValue = viewEvent.text
                    FieldType.PASSWORD -> passwordValue = viewEvent.text
                    else -> throw NotImplementedError()
                }

                viewState.update {
                    it.copy(
                        emailValue = emailValue,
                        passwordValue = passwordValue,
                        isEnabledLoginBtn = emailValue.isNotEmpty() && passwordValue.isNotEmpty()
                    )
                }
            }

            AuthorizationEvent.ClickLoginBtn -> {}
            AuthorizationEvent.ClickLoginGoogleBtn -> {}
            AuthorizationEvent.ClickLoginVkBtn -> {}
        }
    }
}