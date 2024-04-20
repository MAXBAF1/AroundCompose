package com.example.aroundcompose.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.common.views.NextButtonView
import com.example.aroundcompose.ui.common.views.TextFieldView
import com.example.aroundcompose.ui.screens.registration.models.RegistrationEvent
import com.example.aroundcompose.ui.theme.JetAroundTheme

class RegistrationScreen(
    private val viewModel: RegistrationViewModel,
    private val onNextClicked: () -> Unit,
    private val onBackClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(JetAroundTheme.colors.primaryBackground)
                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ) {
            CustomTopAppBar(R.string.registration, onBackClicked)

            TextFields(
                mapOfFields = viewState.mapOfFields,
                onValueChange = { fieldType, text ->
                    viewModel.obtainEvent(RegistrationEvent.ChangeFieldText(fieldType, text))
                },
                modifier = Modifier.padding(top = 28.dp)
            )

            NextButtonView(
                enabled = viewState.isEnabledNextBtn,
                onClick = onNextClicked,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .align(Alignment.End)
            )
        }
    }

    @Composable
    private fun TextFields(
        onValueChange: (fieldType: FieldType, text: String) -> Unit,
        mapOfFields: Map<FieldType, FieldData>,
        modifier: Modifier,
    ) {
        Column(modifier) {
            FieldType.values().forEachIndexed { index, fieldType ->
                TextFieldView(
                    textFieldType = fieldType,
                    textValue = mapOfFields[fieldType]?.fieldText ?: "",
                    textErrorId = mapOfFields[fieldType]?.textErrorId,
                    hint = when (fieldType) {
                        FieldType.LOGIN -> stringResource(id = R.string.hint_name)
                        FieldType.EMAIL -> stringResource(id = R.string.hint_email)
                        FieldType.PASSWORD -> stringResource(id = R.string.hint_password)
                        FieldType.CONFIRM_PASSWORD -> stringResource(id = R.string.hint_confirm_password)
                    },
                    leadingIcon = when (fieldType) {
                        FieldType.LOGIN -> painterResource(id = R.drawable.ic_user_octagon)
                        FieldType.EMAIL -> painterResource(id = R.drawable.ic_email)
                        else -> painterResource(id = R.drawable.ic_lock)
                    },
                    imeAction = if (index == FieldType.values().size - 1) {
                        ImeAction.Done
                    } else {
                        ImeAction.Next
                    },
                    onValueChange = { onValueChange(fieldType, it) }
                )


                mapOfFields[fieldType]?.textErrorId?.let {
                    Text(
                        text = stringResource(id = it),
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
                        style = JetAroundTheme.typography.informationText,
                        color = JetAroundTheme.colors.errorColor
                    )
                }

                if (fieldType != FieldType.CONFIRM_PASSWORD) Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}