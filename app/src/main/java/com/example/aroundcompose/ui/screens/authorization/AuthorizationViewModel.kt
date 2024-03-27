package com.example.aroundcompose.ui.screens.authorization

import com.example.aroundcompose.models.BaseViewModel
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
    BaseViewModel<AuthorizationViewState, AuthorizationEvent>(
        initialState = AuthorizationViewState.RestoreFieldsData()
    ) {
    private val fieldsText = hashMapOf(FieldType.EMAIL to "", FieldType.PASSWORD to "")

    override fun obtainEvent(viewEvent: AuthorizationEvent) {
        when (viewEvent) {
            AuthorizationEvent.RestoreInputs -> {
                viewState.update { AuthorizationViewState.RestoreFieldsData(fieldsText) }
            }

            is AuthorizationEvent.InputTextChange -> {
                fieldsText[viewEvent.type] = viewEvent.text

                if (isAllFieldsValid(fieldsText)) {
                    viewState.update { AuthorizationViewState.EnableLoginBtn }
                } else {
                    viewState.update {
                        AuthorizationViewState.Errors.ValidateErrors(viewEvent.type, viewEvent.text)
                    }
                }
            }

            AuthorizationEvent.ClickLoginBtn -> {}
            AuthorizationEvent.ClickLoginGoogleBtn -> {}
            AuthorizationEvent.ClickLoginVkBtn -> {}
        }
    }

    private fun isAllFieldsValid(fieldsText: Map<FieldType, String>): Boolean {
        fieldsText.values.forEach { text ->
            if (text == "") return false
        }
        return true
    }
}