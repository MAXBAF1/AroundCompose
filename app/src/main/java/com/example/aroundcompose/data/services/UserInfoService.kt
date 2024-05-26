package com.example.aroundcompose.data.services

import com.example.aroundcompose.data.AroundConfig
import com.example.aroundcompose.data.JwtRequestManager
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode

class UserInfoService(private val tokenManager: TokenManager) {
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

    suspend fun findUser(username: String): UserDTO? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Post,
            address = AroundConfig.USER_ADDRESS.toString() + "/find?username=$username"
        )
        return response.castOrNull<UserDTO>()
    }
}