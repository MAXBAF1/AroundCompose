package com.example.aroundcompose.ui.screens.registration

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.common.models.FieldData
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
    private val mapOfFields: HashMap<FieldType, FieldData> = hashMapOf(
        FieldType.LOGIN to FieldData(),
        FieldType.EMAIL to FieldData(),
        FieldType.PASSWORD to FieldData(),
        FieldType.CONFIRM_PASSWORD to FieldData()
    )

    override fun obtainEvent(viewEvent: RegistrationEvent) {
        when (viewEvent) {
            is RegistrationEvent.ChangeFieldText -> {
                mapOfFields[viewEvent.type] = FieldData(
                    fieldText = viewEvent.text,
                    textError = "Здесь вызвать метод валидации"
                )

                viewState.update {
                    it.copy(
                        mapOfFields = mapOfFields.toMap(),
                        isEnabledNextBtn = mapOfFields.values.all { fieldData ->
                            fieldData.fieldText.isNotEmpty() &&
                                    fieldData.textError == null
                        }
                    )
                }
            }

            RegistrationEvent.ClickNextBtn -> {}
        }
    }
}