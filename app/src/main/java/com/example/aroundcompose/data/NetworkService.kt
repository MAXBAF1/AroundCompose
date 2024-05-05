package com.example.aroundcompose.data

import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
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
        return withContext(Dispatchers.IO) {
            try {
                val response = client.post(AroundConfig.LOGIN_ADDRESS.toString()) {
                    contentType(ContentType.Application.Json)
                    setBody(
                        mapOf(
                            "email" to authFields.email.fieldText,
                            "password" to authFields.password.fieldText
                        )
                    )
                }
                if (response.status == HttpStatusCode.OK) {
                    val tokens = response.body<TokenResponse>()
                    tokenManager.saveTokens(tokens)
                }
                response.status
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}