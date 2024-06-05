package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("provider") val provider: String,
    @SerialName("starts") val starts: String,
    @SerialName("ends") val ends: String? = null,
    @SerialName("url") val url: String,
    @SerialName("chunks") val cells: List<CellDTO>,
)
