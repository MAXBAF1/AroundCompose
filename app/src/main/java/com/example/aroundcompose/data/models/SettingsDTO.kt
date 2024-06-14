package com.example.aroundcompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SettingsDTO(
    @SerialName("id") val id: Int = 1,
    @SerialName("toggle_notification") var toggleNotification: Boolean = false,
    @SerialName("theme") val theme: String = "Авто",
    @SerialName("language") val language: String = "Русский",
) : IDTO()