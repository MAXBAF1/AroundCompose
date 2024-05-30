package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName("id") val id: Int = 0,
    @SerialName("username") val username: String = "",
    @SerialName("avatar") val avatar: String = "",
    @SerialName("email") val email: String = "",
    @SerialName("city") val city: String = "",
    @SerialName("team_id") val teamId: Int = 0,
    @SerialName("level") val level: Int = 0,
    @SerialName("coins") var coins: Int = 0,
    @SerialName("verified") val verified: Boolean = false,
) : IDTO()