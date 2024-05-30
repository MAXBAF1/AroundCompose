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
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_ADDRESS.toString() + "/team/$id",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<TeamDTO>()
    }

    suspend fun getAllTeams(): List<TeamDTO>? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_ADDRESS.toString() + "/team/all",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<List<TeamDTO>>()
    }

    suspend fun getTopUsers(): List<FriendDTO>? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_ADDRESS.toString() + "/top",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<List<FriendDTO>>()
    }

    suspend fun getUserFriends(): List<FriendDTO>? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_ADDRESS.toString() + "/me/friends",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<List<FriendDTO>>()
    }

    suspend fun getUserStatistic(): FriendDTO? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.STATISTIC_ADDRESS.toString() + "/me",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<FriendDTO>()
    }
}