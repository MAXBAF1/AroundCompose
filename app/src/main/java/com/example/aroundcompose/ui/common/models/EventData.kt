package com.example.aroundcompose.ui.common.models

@kotlinx.serialization.Serializable
data class EventData(
    val title: String,
    val place: String,
    val distanceToMe: Double
)