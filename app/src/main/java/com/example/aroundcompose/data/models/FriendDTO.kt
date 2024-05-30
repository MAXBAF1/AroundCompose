package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendDTO(
    @SerialName("id") val id: Int = 0,
//    @SerialName("position") val position: Int = 0,
    @SerialName("username") val username: String = "",
    @SerialName("avatar") val avatar: String = "",
    @SerialName("team_id") val teamId: Int = 0,
    @SerialName("level") val level: Int = 0,
    @SerialName("number") var score: Int = 0
) : IDTO()