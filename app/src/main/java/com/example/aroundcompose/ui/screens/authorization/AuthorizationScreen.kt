package com.example.aroundcompose.ui.screens.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.views.TextFieldView
import com.example.aroundcompose.ui.screens.authorization.views.LoginUsingBtn
import com.example.aroundcompose.ui.theme.JetAroundTheme

class AuthorizationScreen(
    private val viewModel: AuthorizationViewModel,
    private val onLoginClicked: () -> Unit,
    private val onRegistrationClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Fit
                )
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(200.dp))

            Text(
                text = stringResource(id = R.string.heading),
                style = JetAroundTheme.typography.headingLogin,
                color = JetAroundTheme.colors.onFocusedColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.label),
                style = JetAroundTheme.typography.textField,
                color = JetAroundTheme.colors.textColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            TextFieldView(
                textFieldType = FieldType.EMAIL,
                restoredValue = "",
                hint = stringResource(id = R.string.hint_email),
                leadingIcon = painterResource(id = R.drawable.ic_email),
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(14.dp))

            TextFieldView(
                textFieldType = FieldType.PASSWORD,
                restoredValue = "",
                hint = stringResource(id = R.string.hint_password),
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.forgot_password),
                style = JetAroundTheme.typography.textRegistration,
                color = JetAroundTheme.colors.onFocusedColor,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        /* Переход на экран восстановления пароля */
                    }
            )

            Spacer(modifier = Modifier.height(40.dp))

            TextButton(
                onClick = onLoginClicked,
                enabled = true,
                shape = JetAroundTheme.shapes.textFieldShape,
                colors = ButtonColors(
                    containerColor = JetAroundTheme.colors.onFocusedColor,
                    contentColor = JetAroundTheme.colors.primaryBackground,
                    disabledContainerColor = JetAroundTheme.colors.notActiveColor,
                    disabledContentColor = JetAroundTheme.colors.primaryBackground
                ),
                contentPadding = PaddingValues(vertical = 21.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.login_btn),
                    style = JetAroundTheme.typography.textBtn
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier
                        .width(117.dp)
                        .height(1.dp)
                        .background(JetAroundTheme.colors.textFieldHint)
                )

                Text(
                    text = stringResource(id = R.string.or),
                    style = JetAroundTheme.typography.textField,
                    color = JetAroundTheme.colors.textFieldHint,
                    modifier = Modifier.padding(horizontal = 22.dp)
                )

                Spacer(
                    modifier = Modifier
                        .width(117.dp)
                        .height(1.dp)
                        .background(JetAroundTheme.colors.textFieldHint)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                LoginUsingBtn(
                    painterId = R.drawable.ic_google,
                    stringId = R.string.google_btn,
                    onClick = {}
                )

                Spacer(modifier = Modifier.width(16.dp))

                LoginUsingBtn(
                    painterId = R.drawable.ic_vk,
                    stringId = R.string.vk_btn,
                    onClick = {}
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 25.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.is_have_not_account),
                    style = JetAroundTheme.typography.textRegistration,
                    color = JetAroundTheme.colors.textColor,
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(id = R.string.registration_click),
                    style = JetAroundTheme.typography.textRegistration,
                    color = JetAroundTheme.colors.onFocusedColor,
                    modifier = Modifier
                        .clickable {
                            onRegistrationClicked()
                        }
                )
            }
        }
    }
}