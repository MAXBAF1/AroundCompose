package com.example.aroundcompose.data

import android.util.Log
import com.example.aroundcompose.data.models.CellDTO
import com.example.aroundcompose.data.models.IDTO
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.ws
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
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.hildan.krossbow.stomp.StompClient
import org.hildan.krossbow.stomp.headers.StompSubscribeHeaders
import org.hildan.krossbow.websocket.ktor.KtorWebSocketClient

object JwtRequestManager {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
        install(WebSockets)
    }
    private val wsClient = KtorWebSocketClient(client)
    private val stompClient = StompClient(wsClient)

    suspend fun createRequest(
        methodType: HttpMethod,
        address: String,
        body: IDTO? = null,
        accessToken: String? = null,
    ): HttpResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.request(address) {
                    method = methodType
                    contentType(ContentType.Application.Json)

                    accessToken?.let { header(HttpHeaders.Authorization, "Bearer $it") }
                    body?.let { setBody(it) }
                }
                Log.d("MyLog", "R ${response.bodyAsText()}")

                response
            } catch (e: Exception) {
                Log.e("MyLog", e.stackTraceToString())
                null
            }
        }
    }


    suspend fun receiveCellsFromWebSocket(
        receivedCellsChannel: Channel<CellDTO>,
        accessToken: String,
    ) {
        withContext(Dispatchers.IO) {
            val session = stompClient.connect(
                url = AroundConfig.WS_HOST.toString(),
                customStompConnectHeaders = mapOf(HttpHeaders.Authorization to "Bearer $accessToken")
            )

            val subscription = session.subscribe(
                StompSubscribeHeaders(
                    destination = AroundConfig.CELLS_CHANGES_EVENT.toString(),
                    customHeaders = mapOf(HttpHeaders.Authorization to "Bearer $accessToken")
                )
            )

            subscription.collect {
                val receivedText = it.bodyAsText
                val receivedCell = Json.decodeFromString(CellDTO.serializer(), receivedText)

                Log.i("MyLog", "SUCCESS get cell from ws: $receivedCell")
                receivedCellsChannel.send(receivedCell)
            }
        }
    }

    suspend fun receiveCellsFromWebSocke(
        receivedCellsChannel: Channel<CellDTO>,
        accessToken: String,
    ) {
        try {
            client.ws(
                urlString = AroundConfig.CELLS_CHANGES_EVENT.toString(),
                request = { header(HttpHeaders.Authorization, "Bearer $accessToken") },
            ) {
                for (frame in incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val receivedText = frame.readText()
                            val receivedCell =
                                Json.decodeFromString(CellDTO.serializer(), receivedText)

                            Log.i("MyLog", "SUCCESS get cell from ws: $receivedCell")
                            receivedCellsChannel.send(receivedCell)
                        }

                        else -> Log.e("MyLog", "Error receiving from WebSocket: not text")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("MyLog", "Error receiving from WebSocket: ${e.message}")
        } finally {
            receivedCellsChannel.close()
        }
    }

    suspend fun sendCellToWebSocket(cell: CellDTO, accessToken: String) {
        try {
            client.webSocket(AroundConfig.CELLS_CHANGES_FROM_USER.toString(), request = {
                header(HttpHeaders.Authorization, "Bearer $accessToken")
            }) {
                val cellJson = Json.encodeToString(CellDTO.serializer(), cell)
                send(Frame.Text(cellJson))
            }
        } catch (e: Exception) {
            println("Error sending to WebSocket: ${e.message}")
        }
    }
}