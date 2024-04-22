package com.example.aroundcompose.ui.screens.registration

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.screens.registration.models.RegistrationEvent
import com.example.aroundcompose.ui.screens.registration.models.RegistrationViewState
import com.example.aroundcompose.ui.screens.registration.models.validation_data.ErrorMessages
import com.example.aroundcompose.ui.screens.registration.models.validation_data.ErrorStatus
import com.example.aroundcompose.ui.screens.registration.models.validation_data.TextFieldsRegex
import com.example.aroundcompose.utils.TextFieldValidation
import com.example.aroundcompose.utils.TextFieldValidation.checkPasswordConfirm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() :
    BaseViewModel<RegistrationViewState, RegistrationEvent>(
        initialState = RegistrationViewState()
    ) {
    private val mapOfFields: HashMap<FieldType, FieldData> = hashMapOf(
        FieldType.LOGIN to FieldData(),
        FieldType.EMAIL to FieldData(),
        FieldType.PASSWORD to FieldData(),
        FieldType.CONFIRM_PASSWORD to FieldData()
    )

    override fun obtainEvent(viewEvent: RegistrationEvent) {
        when (viewEvent) {
            is RegistrationEvent.ChangeFieldText -> {
                val textErrorId: Int? = checkInputError(viewEvent.type, viewEvent.text)

                mapOfFields[viewEvent.type] = FieldData(
                    fieldText = viewEvent.text,
                    textErrorId = textErrorId
                )

                viewState.update {
                    it.copy(
                        mapOfFields = mapOfFields.toMap(),
                        isEnabledNextBtn = TextFieldValidation.isAllFieldsValid(mapOfFields)
                    )
                }
            }

            RegistrationEvent.ClickNextBtn -> {}
        }
    }

    private fun checkInputError(type: FieldType, text: String): Int? {
        var textErrorId: Int? = null

        when (type) {
            FieldType.LOGIN, FieldType.PASSWORD -> {
                val errorLengthStatus = TextFieldValidation.checkLength(text, type)
                val errorRegexStatus =
                    TextFieldValidation.checkRegex(text, TextFieldsRegex.getRegex(type))

                textErrorId = when {
                    errorLengthStatus == ErrorStatus.ERROR -> {
                        ErrorMessages.getErrorMessages(type, "Length")
                    }

                    errorRegexStatus == ErrorStatus.ERROR -> {
                        ErrorMessages.getErrorMessages(type, "Regex")
                    }

                    else -> null
                }
            }

            FieldType.EMAIL -> {
                val errorRegexStatus =
                    TextFieldValidation.checkRegex(text, TextFieldsRegex.getRegex(type))

                textErrorId = if (errorRegexStatus == ErrorStatus.ERROR) {
                    ErrorMessages.getErrorMessages(type, "Regex")
                } else null
            }

            FieldType.CONFIRM_PASSWORD -> {
                val errorPasswordConfirmStatus = checkPasswordConfirm(
                    mapOfFields[FieldType.PASSWORD]?.fieldText ?: "", text
                )

                textErrorId = if (errorPasswordConfirmStatus == ErrorStatus.ERROR) {
                    ErrorMessages.getErrorMessages(type, "Equals")
                } else null
            }
        }

        return textErrorId
    }
}