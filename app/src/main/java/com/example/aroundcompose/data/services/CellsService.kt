package com.example.aroundcompose.data.services

import com.example.aroundcompose.data.AroundConfig
import com.example.aroundcompose.data.JwtRequestManager
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.CellDTO
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod

class CellsService(private val tokenManager: TokenManager) {

    suspend fun getAll(): List<CellDTO>? {
        val tokens = tokenManager.getTokens() ?: return null
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.CELLS_ALL_ADDRESS.toString(),
            accessToken = tokens.accessToken
        )
        return response.castOrNull<List<CellDTO>>()
    }
}