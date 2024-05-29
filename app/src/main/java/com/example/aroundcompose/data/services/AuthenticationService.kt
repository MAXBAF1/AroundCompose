package com.example.aroundcompose.data.services

import com.example.aroundcompose.data.AroundConfig
import com.example.aroundcompose.data.JwtRequestManager
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.AuthDTO
import com.example.aroundcompose.data.models.RegistrationDTO
import com.example.aroundcompose.data.models.TokenResponse
import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import com.example.aroundcompose.ui.screens.registration.models.RegistrationFields
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode

class AuthenticationService(private val tokenManager: TokenManager) {
    suspend fun authenticate(authFields: AuthFields): HttpStatusCode? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Post,
            address = AroundConfig.LOGIN_ADDRESS.toString(),
            body = AuthDTO(
                email = authFields.email.fieldText, password = authFields.password.fieldText
            )
        )
        response.castOrNull<TokenResponse>()?.let { tokenManager.saveTokens(it) }
        return response?.status
    }

    suspend fun register(authFields: RegistrationFields): HttpStatusCode? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Post,
            address = AroundConfig.REGISTRATION_ADDRESS.toString(),
            body = RegistrationDTO(
                username = authFields.username.fieldText,
                email = authFields.email.fieldText,
                password = authFields.password.fieldText,
                teamId = 1,
                city = "Yekaterinburg",
            )
        )
        response.castOrNull<TokenResponse>()?.let { tokenManager.saveTokens(it) }
        return response?.status
    }
}