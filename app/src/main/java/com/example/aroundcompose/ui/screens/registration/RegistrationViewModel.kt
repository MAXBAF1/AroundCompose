package com.example.aroundcompose.ui.screens.registration

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.services.AuthenticationService
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.screens.registration.models.RegistrationEvent
import com.example.aroundcompose.ui.screens.registration.models.RegistrationFields
import com.example.aroundcompose.ui.screens.registration.models.RegistrationViewState
import com.example.aroundcompose.ui.screens.registration.models.validation_data.ErrorMessages
import com.example.aroundcompose.ui.screens.registration.models.validation_data.ErrorStatus
import com.example.aroundcompose.ui.screens.registration.models.validation_data.ErrorsKeys
import com.example.aroundcompose.ui.screens.registration.models.validation_data.TextFieldsRegex
import com.example.aroundcompose.utils.TextFieldValidation
import com.example.aroundcompose.utils.TextFieldValidation.checkPasswordConfirm
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<RegistrationViewState, RegistrationEvent>(
        initialState = RegistrationViewState()
    ) {
    private val fields = RegistrationFields()
    private val networkService = AuthenticationService(tokenManager)

    override fun obtainEvent(viewEvent: RegistrationEvent) {
        when (viewEvent) {
            is RegistrationEvent.ChangeFieldText -> {
                val textErrorId: Int? = checkInputError(viewEvent.type, viewEvent.text)

                fields[viewEvent.type] = FieldData(
                    fieldText = viewEvent.text, textErrorId = textErrorId
                )

                viewState.update {
                    it.copy(
                        fields = fields.copy(),
                        isEnabledNextBtn = TextFieldValidation.isAllFieldsValid(fields)
                    )
                }
            }

            RegistrationEvent.ClickNextBtn -> clickNextBtn()
        }
    }

    private fun clickNextBtn() {
        viewModelScope.launch {
            when (networkService.register(fields)) {
                HttpStatusCode.OK -> viewState.update { it.copy(toNextScreen = true) }
                else -> viewState.update { it.copy(toNextScreen = false) } // FIXME сделать обработку ошибок
            }
        }
    }

    private fun checkInputError(type: FieldType, text: String): Int? {
        var textErrorId: Int? = null

        when (type) {
            FieldType.LOGIN, FieldType.PASSWORD -> {
                val errorLengthStatus = TextFieldValidation.checkLength(text, type)
                val errorRegexStatus = TextFieldValidation.checkRegex(
                    text, TextFieldsRegex.getRegex(type)
                )

                if (type == FieldType.PASSWORD) {
                    val errorPasswordConfirmStatus = checkPasswordConfirm(
                        text, fields[FieldType.CONFIRM_PASSWORD].fieldText
                    )

                    fields[FieldType.CONFIRM_PASSWORD] = fields[FieldType.CONFIRM_PASSWORD].copy(
                        textErrorId = if (errorPasswordConfirmStatus == ErrorStatus.ERROR) {
                            ErrorMessages.getErrorMessages(
                                FieldType.CONFIRM_PASSWORD,
                                ErrorsKeys.EQUALS
                            )
                        } else null
                    )
                }

                textErrorId = when {
                    errorLengthStatus == ErrorStatus.ERROR -> {
                        ErrorMessages.getErrorMessages(type, ErrorsKeys.LENGTH)
                    }

                    errorRegexStatus == ErrorStatus.ERROR -> {
                        ErrorMessages.getErrorMessages(type, ErrorsKeys.REGEX)
                    }

                    else -> null
                }
            }

            FieldType.EMAIL -> {
                val errorRegexStatus =
                    TextFieldValidation.checkRegex(text, TextFieldsRegex.getRegex(type))

                textErrorId = if (errorRegexStatus == ErrorStatus.ERROR) {
                    ErrorMessages.getErrorMessages(type, ErrorsKeys.REGEX)
                } else null
            }

            FieldType.CONFIRM_PASSWORD -> {
                val errorPasswordConfirmStatus = checkPasswordConfirm(
                    fields[FieldType.PASSWORD].fieldText, text
                )

                textErrorId = if (errorPasswordConfirmStatus == ErrorStatus.ERROR) {
                    ErrorMessages.getErrorMessages(type, ErrorsKeys.EQUALS)
                } else null
            }
        }

        return textErrorId
    }
}