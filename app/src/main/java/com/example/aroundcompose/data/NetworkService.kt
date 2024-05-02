package com.example.aroundcompose.data

import com.example.aroundcompose.ui.screens.authorization.models.AuthFields
import io.ktor.client.*
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class NetworkService {
    private val client = HttpClient {
        // Настройка клиента
        //install(JsonFeature)
    }

    suspend fun authenticate(authFields: AuthFields): String? {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.post(AroundConfig.LOGIN_ADDRESS.value) {
                    contentType(ContentType.Application.Json)
                    setBody(authFields)
                }
                response.toString()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}