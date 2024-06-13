package com.example.aroundcompose.ui.screens.authorization

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.views.CustomButton
import com.example.aroundcompose.ui.common.views.TextFieldView
import com.example.aroundcompose.ui.screens.authorization.AuthorizationViewModel.Companion.WEB_CLIENT_ID
import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.views.LoginUsingBtn
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.UpdateThemeStyleByTeam
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AuthorizationScreen(
    private val activity: Context,
    private val viewModel: AuthorizationViewModel,
    private val onLoginClicked: () -> Unit,
    private val onRegistrationClicked: () -> Unit,
    private val onForgotPasswordClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel
            .getViewState()
            .collectAsStateWithLifecycle()

        UpdateThemeStyleByTeam(viewState.userTeam)

        if (viewState.toNextScreen) {
            onLoginClicked()
            viewModel.obtainEvent(AuthorizationEvent.ClearViewState)
        }

        Surface(color = JetAroundTheme.colors.primaryBackground) {
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
                    Spacer(
                        modifier = Modifier
                            .weight(1.25f)
                            .fillMaxSize()
                    )
                    Title(Modifier.padding(bottom = 40.dp))
                    TextFields(
                        fields = viewState.fields,
                        onValueChange = { fieldType, value ->
                            viewModel.obtainEvent(
                                AuthorizationEvent.ChangeFieldText(
                                    fieldType, value
                                )
                            )
                        },
                        modifier = Modifier.padding(bottom = 14.dp),
                    )
                    ForgotPassword(
                        onFocusedColor = JetAroundTheme.colors.primary,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(bottom = 40.dp),
                    )
                    LoginButtons(viewState.isEnabledLoginBtn, onLoginClick = {
                        viewModel.obtainEvent(AuthorizationEvent.ClickLoginBtn)
                    })
                    IfNotHaveAccount(
                        onFocusedColor = JetAroundTheme.colors.primary,
                        modifier = Modifier.weight(1f)
                    )
                }
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
                color = JetAroundTheme.colors.primary
            )
            Text(
                text = stringResource(id = R.string.label),
                style = JetAroundTheme.typography.fourteenMedium,
                color = JetAroundTheme.colors.textColor
            )
        }
    }

    @Composable
    private fun TextFields(
        onValueChange: (fieldType: FieldType, value: String) -> Unit,
        fields: AuthFields,
        modifier: Modifier,
    ) {
        Column(modifier = modifier) {
            TextFieldView(
                textFieldType = FieldType.EMAIL,
                textValue = fields[FieldType.EMAIL].fieldText,
                hint = stringResource(id = R.string.hint_email),
                leadingIcon = painterResource(id = R.drawable.ic_email),
                onValueChange = { onValueChange(FieldType.EMAIL, it) },
                modifier = Modifier.padding(bottom = 14.dp)
            )

            TextFieldView(
                textFieldType = FieldType.PASSWORD,
                textValue = fields[FieldType.PASSWORD].fieldText,
                hint = stringResource(id = R.string.hint_password),
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                onValueChange = { onValueChange(FieldType.PASSWORD, it) },
                imeAction = ImeAction.Done
            )
        }
    }

    @Composable
    private fun ForgotPassword(onFocusedColor: Color, modifier: Modifier) {
        var forgotPasswordColor by remember { mutableStateOf(onFocusedColor) }

        Text(
            text = stringResource(id = R.string.forgot_password),
            style = JetAroundTheme.typography.textRegistration.copy(color = forgotPasswordColor),
            modifier = modifier.clickable(
                onClick = {
                    forgotPasswordColor = Color.DarkGray
//                onForgotPasswordClicked() TODO: Добавить экран восстановления пароля
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(radius = 0.dp)
            )
        )
    }

    @Composable
    private fun LoginButtons(enabled: Boolean, onLoginClick: () -> Unit) {
        var isGoogleClicked by remember { mutableStateOf(false) }

        CustomButton(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(id = R.string.login_btn).uppercase(),
            enabled = enabled,
            onClick = onLoginClick
        )
        TextOr(modifier = Modifier.padding(bottom = 16.dp))
        LoginUsingButtons(
            onGoogleClick = {
                isGoogleClicked = true
//               viewModel . obtainEvent (AuthorizationEvent.ClickLoginGoogleBtn)
            },
            onVkClick = { viewModel.obtainEvent(AuthorizationEvent.ClickLoginVkBtn) },
        )

        if (isGoogleClicked) SignInGoogle()
    }

    @Composable
    private fun SignInGoogle() {
        val googleIdOption = GetGoogleIdOption(
            filterByAuthorizedAccounts = false,
            serverClientId = WEB_CLIENT_ID,
            autoSelectEnabled = true,
        )

        val request = GetCredentialRequest(credentialOptions = listOf(googleIdOption))
        val context = LocalContext.current
        LaunchedEffect(key1 = Unit) {
            launch {
                try {
                    val result = CredentialManager
                        .create(context)
                        .getCredential(context, request)
                    handleSignIn(result)
                } catch (e: GetCredentialException) {
                    Log.e("MyLog", e.stackTraceToString())
                }
            }
        }
    }

    private fun handleSignIn(result: GetCredentialResponse) {
        val credential = result.credential
        if (credential is CustomCredential) {
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    val googleIdToken = GoogleIdTokenCredential.createFrom(credential.data)
                } catch (e: GoogleIdTokenParsingException) {
                    Log.e("MyLog", "Received an invalid google id token response", e)
                }
            } else Log.e("MyLog", "Unexpected type of credential")
        } else Log.e("MyLog", "Unexpected type of credential")
    }

    @Composable
    private fun TextOr(modifier: Modifier = Modifier) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
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
                style = JetAroundTheme.typography.fourteenMedium,
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
    private fun LoginUsingButtons(onGoogleClick: () -> Unit, onVkClick: () -> Unit) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            LoginUsingBtn(
                painterId = R.drawable.ic_google,
                stringId = R.string.google_btn,
                modifier = Modifier.weight(1f),
                onClick = onGoogleClick,
            )
            Spacer(modifier = Modifier.width(16.dp))
            LoginUsingBtn(
                painterId = R.drawable.ic_vk,
                stringId = R.string.vk_btn,
                modifier = Modifier.weight(1f),
                onClick = onVkClick,
            )
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

            Text(
                text = AnnotatedString(stringResource(id = R.string.registration_click)),
                style = JetAroundTheme.typography.textRegistration.copy(color = registrationColor),
                modifier = Modifier.clickable(
                    onClick = {
                        registrationColor = Color.DarkGray
                        onRegistrationClicked()
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(radius = 0.dp)
                )
            )

            if (registrationColor == Color.DarkGray) {
                LaunchedEffect(key1 = Unit) {
                    delay(100)
                    registrationColor = onFocusedColor
                }
            }
        }
    }
}