package com.example.aroundcompose.ui.screens.skills.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkillData(
    @SerialName("id") val id: Int = 1,
    @SerialName("name") val name: String,
    @SerialName("max_level") val maxLevel: Int = 10,
    @SerialName("current_level") val currentLevel: Int = 1,
    @SerialName("rule") val rule: List<Int> = listOf(),
    @SerialName("cost") val cost: List<Int> = listOf(),
    @SerialName("description") val description: String,
    @SerialName("image") val iconUrl: String,
//    val imageId: Int,
)
