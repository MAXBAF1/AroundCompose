package com.example.aroundcompose.data

import android.util.Log
import com.example.aroundcompose.data.models.IDTO
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object JwtRequestManager {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun createRequest(
        methodType: HttpMethod,
        address: String,
        body: IDTO? = null,
        accessToken: String? = null,
    ): HttpResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.request(address) {
                    this.method = methodType
                    contentType(ContentType.Application.Json)

                    accessToken?.let {
                        header(HttpHeaders.Authorization, "Bearer $it")
                    }

                    body?.let {
                        setBody(it)
                    }
                }
                Log.d("JwtRequest", "R ${response.bodyAsText()}")

                response
            } catch (e: Exception) {
                Log.e("JwtRequest", e.stackTraceToString())
                null
            }
        }
    }
}