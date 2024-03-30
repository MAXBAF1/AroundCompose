package com.example.aroundcompose.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.views.NavBackView
import com.example.aroundcompose.ui.common.views.NextButtonView
import com.example.aroundcompose.ui.common.views.TextFieldView
import com.example.aroundcompose.ui.theme.JetAroundTheme

class RegistrationScreen(
    private val viewModel: RegistrationViewModel,
    private val onNextClicked: () -> Unit,
    private val onBackClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(JetAroundTheme.colors.primaryBackground)
                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ) {
            NavBackView(R.string.registration, onBackClicked)

            Spacer(modifier = Modifier.height(28.dp))

            TextFields()

            Spacer(modifier = Modifier.height(40.dp))

            NextButtonView(
                enabled = true, onClick = onNextClicked, modifier = Modifier.align(Alignment.End)
            )
        }
    }

    @Composable
    private fun TextFields() {
        FieldType.values().forEachIndexed { i, value ->
            TextFieldView(
                textFieldType = value, textValue = "", hint = when (value) {
                    FieldType.LOGIN -> stringResource(id = R.string.hint_name)
                    FieldType.EMAIL -> stringResource(id = R.string.hint_email)
                    FieldType.PASSWORD -> stringResource(id = R.string.hint_password)
                    FieldType.CONFIRM_PASSWORD -> stringResource(id = R.string.hint_confirm_password)
                }, leadingIcon = when (value) {
                    FieldType.LOGIN -> painterResource(id = R.drawable.ic_user_octagon)
                    FieldType.EMAIL -> painterResource(id = R.drawable.ic_email)
                    else -> painterResource(id = R.drawable.ic_lock)
                }, imeAction = if (i == FieldType.values().size - 1) {
                    ImeAction.Done
                } else {
                    ImeAction.Next
                }
            ) {

            }

            if (value != FieldType.CONFIRM_PASSWORD) Spacer(modifier = Modifier.height(14.dp))
        }
    }
}