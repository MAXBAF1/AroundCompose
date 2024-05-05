package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationDTO(
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("team_id") val teamId: Int,
    @SerialName("city") val city: String,
) : IDTO()
