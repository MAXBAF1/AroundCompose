package com.example.aroundcompose.data

import com.example.aroundcompose.data.models.AuthDTO
import com.example.aroundcompose.data.models.RegistrationDTO
import com.example.aroundcompose.data.models.TokenResponse
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import com.example.aroundcompose.ui.screens.registration.models.RegistrationFields
import com.example.aroundcompose.ui.screens.skills.models.SkillData
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode

class NetworkService(private val tokenManager: TokenManager) {
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

    suspend fun getUserSkills(id: Int): Array<SkillData>? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/$id/skills",
            accessToken = tokenManager.getTokens().first.toString()
        )

        return response.castOrNull<Array<SkillData>>()
    }

    suspend fun getMe(): UserDTO? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/me",
            accessToken = tokenManager.getTokens().first.toString()
        )

        return response.castOrNull<UserDTO>()
    }

    suspend fun getUser(id: Int): HttpStatusCode? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/$id",
            accessToken = tokenManager.getTokens().first.toString()
        )

        return response?.status
    }
}