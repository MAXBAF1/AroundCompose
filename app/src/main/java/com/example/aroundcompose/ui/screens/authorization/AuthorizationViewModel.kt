package com.example.aroundcompose.ui.screens.authorization

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationViewState
import com.example.aroundcompose.utils.TextFieldValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() :
    BaseViewModel<AuthorizationViewState, AuthorizationEvent>(AuthorizationViewState()) {
    private val mapOfFields: HashMap<FieldType, FieldData> = hashMapOf(
        FieldType.EMAIL to FieldData(),
        FieldType.PASSWORD to FieldData(),
    )

    override fun obtainEvent(viewEvent: AuthorizationEvent) {
        when (viewEvent) {
            is AuthorizationEvent.ChangeFieldText -> changeFieldText(viewEvent.type, viewEvent.text)

            AuthorizationEvent.ClickLoginBtn -> {}
            AuthorizationEvent.ClickLoginGoogleBtn -> {}
            AuthorizationEvent.ClickLoginVkBtn -> {}
        }
    }

    private fun changeFieldText(type: FieldType, text: String) {
        mapOfFields[type] = FieldData(fieldText = text)

        viewState.update {
            it.copy(
                mapOfFields = mapOfFields.toMap(),
                isEnabledLoginBtn = TextFieldValidation.isAllFieldsValid(mapOfFields)
            )
        }
    }
}