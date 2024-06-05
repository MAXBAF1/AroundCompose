package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CellDTO(
    @SerialName("id") val id: String,
    @SerialName("team_id") val teamId: Int? = null
)
