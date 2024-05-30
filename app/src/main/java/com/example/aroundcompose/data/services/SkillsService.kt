package com.example.aroundcompose.data.services

import com.example.aroundcompose.data.AroundConfig
import com.example.aroundcompose.data.JwtRequestManager
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.utils.castOrNull
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode

class SkillsService(private val tokenManager: TokenManager) {
    suspend fun getUserSkills(id: Int): Array<SkillDTO>? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Get,
            address = AroundConfig.USER_ADDRESS.toString() + "/$id/skills",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response.castOrNull<Array<SkillDTO>>()
    }

    suspend fun buyLevels(id: Int, levels: Int = 1): HttpStatusCode? {
        val response = JwtRequestManager.createRequest(
            methodType = HttpMethod.Post,
            address = AroundConfig.SKILLS_ADDRESS.toString() + "/$id/buyLevels?levels=$levels",
            accessToken = tokenManager.getTokens().first.toString()
        )
        return response?.status
    }
}