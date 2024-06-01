package com.example.aroundcompose.data.services

import com.example.aroundcompose.data.AroundConfig
import com.example.aroundcompose.data.JwtRequestManager
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod

class FriendsService(private val tokenManager: TokenManager) {
    suspend fun findFriends(): List<FriendDTO>? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_ADDRESS.toString() + "/me/friends",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<List<FriendDTO>>()
    }
}