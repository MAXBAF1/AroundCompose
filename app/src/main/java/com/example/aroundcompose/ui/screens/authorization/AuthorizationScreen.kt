package com.example.aroundcompose.ui.screens.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.views.TextFieldView
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.views.LoginUsingBtn
import com.example.aroundcompose.ui.theme.JetAroundTheme
import kotlinx.coroutines.delay

class AuthorizationScreen(
    private val viewModel: AuthorizationViewModel,
    private val onLoginClicked: () -> Unit,
    private val onRegistrationClicked: () -> Unit,
    private val onForgotPasswordClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        Box {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "backgroundImage"
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                val onFocusedColor = JetAroundTheme.colors.onFocusedColor

                Spacer(
                    modifier = Modifier
                        .weight(1.25f)
                        .fillMaxSize()
                )

                Title(Modifier.padding(bottom = 40.dp))

                TextFields(
                    emailValue = viewState.emailValue,
                    passwordValue = viewState.passwordValue,
                    onValueChange = { fieldType, value ->
                        viewModel.obtainEvent(AuthorizationEvent.InputTextChange(fieldType, value))
                    },
                    modifier = Modifier.padding(bottom = 14.dp)
                )

                ForgotPassword(
                    onFocusedColor,
                    Modifier
                        .align(Alignment.End)
                        .padding(bottom = 40.dp)
                )

                LoginButtons(viewState.isEnabledLoginBtn)

                IfNotHaveAccount(onFocusedColor, modifier = Modifier.weight(1f))
            }
        }
    }

    @Composable
    private fun Title(modifier: Modifier) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.heading),
                modifier = Modifier.padding(bottom = 10.dp),
                style = JetAroundTheme.typography.headingLogin,
                color = JetAroundTheme.colors.onFocusedColor
            )

            Text(
                text = stringResource(id = R.string.label),
                style = JetAroundTheme.typography.textField,
                color = JetAroundTheme.colors.textColor
            )
        }
    }

    @Composable
    private fun TextFields(
        onValueChange: (fieldType: FieldType, value: String) -> Unit,
        emailValue: String,
        passwordValue: String,
        modifier: Modifier,
    ) {
        Column(modifier = modifier) {
            TextFieldView(
                textFieldType = FieldType.EMAIL,
                textValue = emailValue,
                hint = stringResource(id = R.string.hint_email),
                leadingIcon = painterResource(id = R.drawable.ic_email),
                onValueChange = { onValueChange(FieldType.EMAIL, it) },
                modifier = Modifier.padding(bottom = 14.dp)
            )

            TextFieldView(
                textFieldType = FieldType.PASSWORD,
                textValue = passwordValue,
                hint = stringResource(id = R.string.hint_password),
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                onValueChange = { onValueChange(FieldType.PASSWORD, it) },
                imeAction = ImeAction.Done
            )
        }
    }

    @Composable
    private fun ForgotPassword(onFocusedColor: Color, modifier: Modifier) {
        var forgotPasswordColor by remember {
            mutableStateOf(onFocusedColor)
        }

        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.forgot_password)),
            style = JetAroundTheme.typography.textRegistration.copy(color = forgotPasswordColor),
            modifier = modifier
        ) {
            forgotPasswordColor = Color.DarkGray
            onForgotPasswordClicked()
        }
    }

    @Composable
    private fun LoginButtons(enabled: Boolean) {
        LoginButton(enabled)
        Spacer(modifier = Modifier.height(16.dp))
        TextOr()
        Spacer(modifier = Modifier.height(16.dp))
        LoginUsingButtons()
    }

    @Composable
    private fun LoginButton(enabled: Boolean) {
        TextButton(
            onClick = onLoginClicked,
            enabled = enabled,
            shape = JetAroundTheme.shapes.textFieldShape,
            elevation = ButtonDefaults.buttonElevation(JetAroundTheme.shadows.loginUsingShadow),
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
                text = stringResource(id = R.string.login_btn).uppercase(),
                style = JetAroundTheme.typography.textBtn
            )
        }
    }

    @Composable
    private fun TextOr() {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .clip(shape = CircleShape)
                    .weight(1f)
                    .background(JetAroundTheme.colors.textFieldHint)
            )

            Text(
                text = stringResource(id = R.string.or),
                style = JetAroundTheme.typography.textField,
                color = JetAroundTheme.colors.textFieldHint,
                modifier = Modifier.padding(horizontal = 22.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .clip(shape = CircleShape)
                    .weight(1f)
                    .background(JetAroundTheme.colors.textFieldHint)
            )
        }
    }

    @Composable
    private fun LoginUsingButtons() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            LoginUsingBtn(painterId = R.drawable.ic_google,
                stringId = R.string.google_btn,
                modifier = Modifier.weight(1f),
                onClick = {})

            Spacer(modifier = Modifier.width(16.dp))

            LoginUsingBtn(painterId = R.drawable.ic_vk,
                stringId = R.string.vk_btn,
                modifier = Modifier.weight(1f),
                onClick = {})
        }
    }

    @Composable
    private fun IfNotHaveAccount(onFocusedColor: Color, modifier: Modifier) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.is_have_not_account),
                style = JetAroundTheme.typography.textRegistration,
                color = JetAroundTheme.colors.textColor,
            )

            Spacer(modifier = Modifier.width(4.dp))

            var registrationColor by remember {
                mutableStateOf(onFocusedColor)
            }

            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.registration_click)),
                style = JetAroundTheme.typography.textRegistration.copy(color = registrationColor)
            ) {
                registrationColor = Color.DarkGray
                onRegistrationClicked()
            }

            if (registrationColor == Color.DarkGray) {
                LaunchedEffect(key1 = Unit) {
                    delay(100)
                    registrationColor = onFocusedColor
                }
            }
        }
    }
}