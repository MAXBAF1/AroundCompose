package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TeamDTO {
    @SerialName("id") val id: Int = 0
    @SerialName("color") val color: String = ""
    @SerialName("number") val score: Int = 0
}