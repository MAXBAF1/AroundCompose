package com.example.aroundcompose.ui.screens.authorization

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.NetworkService
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationViewState
import com.example.aroundcompose.utils.TextFieldValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<AuthorizationViewState, AuthorizationEvent>(AuthorizationViewState()) {
    private val fields = AuthFields()
    private val networkService = NetworkService(tokenManager)


    override fun obtainEvent(viewEvent: AuthorizationEvent) {
        when (viewEvent) {
            is AuthorizationEvent.ChangeFieldText -> changeFieldText(viewEvent.type, viewEvent.text)

            AuthorizationEvent.ClickLoginBtn -> clickLoginBtn()
            AuthorizationEvent.ClickLoginGoogleBtn -> {}
            AuthorizationEvent.ClickLoginVkBtn -> {}
        }
    }

    private fun clickLoginBtn() {
        viewModelScope.launch {
            when (networkService.authenticate(fields)) {
                HttpStatusCode.OK -> viewState.update { it.copy(toNextScreen = true) }
                else -> viewState.update { it.copy(toNextScreen = false) }
            }
        }
    }

    private fun changeFieldText(type: FieldType, text: String) {
        fields[type] = FieldData(fieldText = text)

        viewState.update {
            it.copy(
                fields = fields.copy(),
                isEnabledLoginBtn = TextFieldValidation.isAllFieldsValid(fields)
            )
        }
    }
}