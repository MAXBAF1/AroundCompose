package com.example.aroundcompose.data

import android.util.Log
import com.example.aroundcompose.data.models.AuthDTO
import com.example.aroundcompose.data.models.IDTO
import com.example.aroundcompose.data.models.RegistrationDTO
import com.example.aroundcompose.data.models.TokenResponse
import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import com.example.aroundcompose.ui.screens.registration.models.RegistrationFields
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkService(private val tokenManager: TokenManager) {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun authenticate(authFields: AuthFields): HttpStatusCode? {
        return createJwtRequest(
            address = AroundConfig.LOGIN_ADDRESS.toString(), body = AuthDTO(
                email = authFields.email.fieldText, password = authFields.password.fieldText
            )
        )
    }

    suspend fun register(authFields: RegistrationFields): HttpStatusCode? {
        return createJwtRequest(
            address = AroundConfig.REGISTRATION_ADDRESS.toString(), body = RegistrationDTO(
                username = authFields.username.fieldText,
                email = authFields.email.fieldText,
                password = authFields.password.fieldText,
                teamId = 1,
                city = "Yekaterinburg",
            )
        )
    }

    private suspend fun createJwtRequest(address: String, body: IDTO): HttpStatusCode? {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.post(address) {
                    contentType(ContentType.Application.Json)
                    setBody(body)
                }
                Log.d("MyLog", "R ${response.bodyAsText()}")
                if (response.status == HttpStatusCode.OK) {
                    val tokens = response.body<TokenResponse>()
                    tokenManager.saveTokens(tokens)
                }
                response.status
            } catch (e: Exception) {
                Log.e("MyLog", e.stackTraceToString())
                null
            }
        }
    }
}