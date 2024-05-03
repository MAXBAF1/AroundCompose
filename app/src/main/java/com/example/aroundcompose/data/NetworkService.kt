package com.example.aroundcompose.data

import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
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

    suspend fun authenticate(authFields: AuthFields): Int? {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.post(AroundConfig.LOGIN_ADDRESS.toString()) {
                    contentType(ContentType.Application.Json)
                    setBody(
                        mapOf(
                            "email" to "password1@gmail.com", //authFields.email.fieldText,
                            "password" to "Password1!", //authFields.password.fieldText
                        )
                    )
                }
                val gf = response.bodyAsText()

                tokenManager.saveTokens("your_access_token", "your_refresh_token")
                val f = gf
                1
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}