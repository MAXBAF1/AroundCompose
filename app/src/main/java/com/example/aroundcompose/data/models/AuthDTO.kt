package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthDTO(
    @SerialName("email") val email: String, @SerialName("password") val password: String
) : IDTO()
