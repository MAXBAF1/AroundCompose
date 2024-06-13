package com.example.aroundcompose.ui.screens.authorization

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.services.AuthenticationService
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationEvent
import com.example.aroundcompose.ui.screens.authorization.models.AuthorizationViewState
import com.example.aroundcompose.utils.TextFieldValidation
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    tokenManager: TokenManager,
) : BaseViewModel<AuthorizationViewState, AuthorizationEvent>(AuthorizationViewState()) {
    private var fields = AuthFields()
    private val authenticationService = AuthenticationService(tokenManager)
    private val userInfoService = UserInfoService(tokenManager)

    override fun obtainEvent(viewEvent: AuthorizationEvent) {
        when (viewEvent) {
            is AuthorizationEvent.ChangeFieldText -> changeFieldText(viewEvent.type, viewEvent.text)
            AuthorizationEvent.ClickLoginBtn -> clickLoginBtn()
            AuthorizationEvent.ClickLoginGoogleBtn -> clickLoginGoogleBtn()
            AuthorizationEvent.ClickLoginVkBtn -> {}
            AuthorizationEvent.ClearViewState -> clearViewState()
        }
    }

    private fun clickLoginGoogleBtn() {
        /*val googleIdOption: GetGoogleIdOption = GetGoogleIdOption
            .Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(WEB_CLIENT_ID)
            .setAutoSelectEnabled(true)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest
            .Builder()
            .addCredentialOption(googleIdOption)
            .build()


        viewModelScope.launch {
            try {
                val result = CredentialManager.create(context).getCredential(
                    request = request,
                    context = context,
                )
                handleSignIn(result)
            } catch (e: GetCredentialException) {
                Log.e("MyLog", "$e")
            }
        }*/
    }

    private fun handleSignIn(result: GetCredentialResponse) {
        // Handle the successfully returned credential.
        val credential = result.credential

        when (credential) {

            // Passkey credential
            is PublicKeyCredential -> {
                // Share responseJson such as a GetCredentialResponse on your server to
                // validate and authenticate
                val responseJson = credential.authenticationResponseJson
            }

            // Password credential
            is PasswordCredential -> {
                // Send ID and password to your server to validate and authenticate.
                val username = credential.id
                val password = credential.password
            }

            // GoogleIdToken credential
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract id to validate and
                        // authenticate on your server.
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e("MyLog", "Received an invalid google id token response", e)
                    }
                } else {
                    // Catch any unrecognized custom credential type here.
                    Log.e("MyLog", "Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e("MyLog", "Unexpected type of credential")
            }
        }
    }

    private fun clearViewState() {
        viewState.update { AuthorizationViewState() }
        fields = AuthFields()
    }

    private fun clickLoginBtn() {
        viewModelScope.launch {
            when (authenticationService.authenticate(fields)) {
                HttpStatusCode.OK -> {
                    val myTeam = userInfoService.getMe()?.teamId ?: -1
                    viewState.update {
                        it.copy(toNextScreen = true, userTeam = Teams.getById(myTeam))
                    }
                }

                else -> viewState.update { it.copy(toNextScreen = false) } // FIXME сделать обработку ошибок
            }
        }
    }

    private fun changeFieldText(type: FieldType, text: String) {
        fields[type] = FieldData(fieldText = text)

        viewState.update {
            it.copy(
                fields = fields.copy(),
                isEnabledLoginBtn = TextFieldValidation.isAllFieldsValid(fields)
            )
        }
    }

    companion object {
        const val WEB_CLIENT_ID =
            "241228577692-vsetumrujgjrrn0bs64rpucvgknjdme1.apps.googleusercontent.com"
    }
}