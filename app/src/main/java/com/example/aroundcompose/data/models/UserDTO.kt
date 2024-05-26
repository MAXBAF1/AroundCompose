package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("city") val city: String,
    @SerialName("team_id") val teamId: Int,
    @SerialName("level") val level: Int,
    @SerialName("coins") val coins: Int,
    @SerialName("verified") val verified: Boolean,
) : IDTO()