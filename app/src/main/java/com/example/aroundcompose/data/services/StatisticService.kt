package com.example.aroundcompose.data.services

import com.example.aroundcompose.data.AroundConfig
import com.example.aroundcompose.data.JwtRequestManager
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.data.models.TeamDTO
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod

class StatisticService(private val tokenManager: TokenManager) {
    suspend fun getTeam(id: Int): TeamDTO? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_TEAM_ADDRESS.toString() + "/$id",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<TeamDTO>()
    }

    suspend fun getAllTeams(): List<TeamDTO>? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_TEAM_ADDRESS.toString() + "/all",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<List<TeamDTO>>()
    }

    suspend fun getTopUsers(): List<FriendDTO>? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_USER_ADDRESS.toString() + "/top",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<List<FriendDTO>>()
    }

    suspend fun getUserFriends(): List<FriendDTO>? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_USER_ADDRESS.toString() + "/me/friends",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<List<FriendDTO>>()
    }

    suspend fun getUserStatistic(): FriendDTO? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_USER_ADDRESS.toString() + "/me",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<FriendDTO>()
    }

    suspend fun getUserById(id:Int): FriendDTO? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_USER_ADDRESS.toString() + "/$id",
            accessToken = tokens.accessToken
        )
        return response.castOrNull<FriendDTO>()
    }
}