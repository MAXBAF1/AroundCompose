package com.example.aroundcompose.data.services

import com.example.aroundcompose.data.AroundConfig
import com.example.aroundcompose.data.JwtRequestManager
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.EditableUserDTO
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode

class UserInfoService(private val tokenManager: TokenManager) {
    suspend fun getMe(): UserDTO? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/me",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<UserDTO>()
    }

    suspend fun patchMe(
        username: String? = null,
        avatar: String? = null,
        city: String? = null,
        teamId: Int? = null,
    ): HttpStatusCode? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Patch,
            body = EditableUserDTO(
                username = username,
                avatar = avatar,
                city = city,
                teamId = teamId,
            ),
            address = AroundConfig.USER_ADDRESS.toString() + "/me",
            accessToken = tokens.accessToken
        )
        return response?.status
    }

    suspend fun getUser(id: Int): UserDTO? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/$id",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<UserDTO>()
    }

    suspend fun findUser(username: String): List<FriendDTO>? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Post,
            address = AroundConfig.USER_ADDRESS.toString() + "/find?username=$username",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<List<FriendDTO>>()
    }
}