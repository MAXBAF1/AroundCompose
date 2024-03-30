package com.example.aroundcompose.ui.screens.authorization

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
    BaseViewModel<AuthorizationViewState, AuthorizationEvent>(AuthorizationViewState.RestoreData()) {
    private val fieldsText = hashMapOf(FieldType.EMAIL to "", FieldType.PASSWORD to "")

    override fun obtainEvent(viewEvent: AuthorizationEvent) {
        when (viewEvent) {
            AuthorizationEvent.RestoreInputs -> {
                viewState.value = AuthorizationViewState.RestoreData(fieldsText)
            }

            is AuthorizationEvent.InputTextChange -> {
                fieldsText[viewEvent.type] = viewEvent.text

                if (isAllFieldsValid(fieldsText)) {
                    viewState.value = AuthorizationViewState.EnableLoginBtn
                } else {
                    viewState.value = AuthorizationViewState.FieldsTextChanged(fieldsText)
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