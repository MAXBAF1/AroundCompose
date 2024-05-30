package com.example.aroundcompose.data.services

import com.example.aroundcompose.data.AroundConfig
import com.example.aroundcompose.data.JwtRequestManager
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod

class FriendsService(private val tokenManager: TokenManager) {
    suspend fun findFriends(): Array<FriendDTO>? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/me/friends",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<Array<FriendDTO>>()
    }
}