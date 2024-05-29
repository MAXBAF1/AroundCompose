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
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/me",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<UserDTO>()
    }

    suspend fun patchMe(
        username: String? = null,
        avatar: String? = null,
        city: String? = null,
        teamId: Int? = null,
    ): HttpStatusCode? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Patch,
            body = EditableUserDTO(
                username = username,
                avatar = avatar,
                city = city,
                teamId = teamId,
            ),
            address = AroundConfig.USER_ADDRESS.toString() + "/me",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response?.status
    }

    suspend fun getUser(id: Int): UserDTO? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/$id",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<UserDTO>()
    }

    suspend fun findUser(username: String): Array<FriendDTO>? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Post,
            address = AroundConfig.USER_ADDRESS.toString() + "/find?username=$username",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<Array<FriendDTO>>()
    }
}