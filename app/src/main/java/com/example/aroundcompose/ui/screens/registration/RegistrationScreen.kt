package com.example.aroundcompose.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxSize()
                .background(JetAroundTheme.colors.primaryBackground)
                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ) {
            NavBackView(R.drawable.ic_arrow_left, onBackClicked)

            Spacer(modifier = Modifier.height(28.dp))

            TextFieldView(
                textFieldType = FieldType.LOGIN,
                restoredValue = "",
                hint = stringResource(id = R.string.hint_name),
                leadingIcon = painterResource(id = R.drawable.ic_user_octagon)
            ) {

            }

            Spacer(modifier = Modifier.height(14.dp))

            TextFieldView(
                textFieldType = FieldType.EMAIL,
                restoredValue = "",
                hint = stringResource(id = R.string.hint_email),
                leadingIcon = painterResource(id = R.drawable.ic_email)
            ) {

            }

            Spacer(modifier = Modifier.height(14.dp))

            TextFieldView(
                textFieldType = FieldType.PASSWORD,
                restoredValue = "",
                hint = stringResource(id = R.string.hint_password),
                leadingIcon = painterResource(id = R.drawable.ic_lock)
            ) {

            }

            Spacer(modifier = Modifier.height(14.dp))

            TextFieldView(
                textFieldType = FieldType.CONFIRM_PASSWORD,
                restoredValue = "",
                hint = stringResource(id = R.string.hint_confirm_password),
                leadingIcon = painterResource(id = R.drawable.ic_lock)
            ) {

            }

            Spacer(modifier = Modifier.height(40.dp))

            NextButtonView(onClick = onNextClicked)
        }
    }
}