package com.example.aroundcompose.ui.screens.authorization

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
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
            is AuthorizationEvent.HandleGoogleResponse -> handleSignIn(viewEvent.result)
            AuthorizationEvent.ClickGoogleBtn -> viewState.update { it.copy(isGoogleBtnClicked = true) }
            AuthorizationEvent.ClickLoginVkBtn -> {}
            AuthorizationEvent.ClearViewState -> clearViewState()

        }
    }


    private fun handleSignIn(result: GetCredentialResponse) {
        viewState.update { it.copy(isGoogleBtnClicked = false) }

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