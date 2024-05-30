package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditableUserDTO(
    @SerialName("username") val username: String? = null,
    @SerialName("avatar") val avatar: String? = null,
    @SerialName("city") val city: String? = null,
    @SerialName("team_id") val teamId: Int? = null,
): IDTO()