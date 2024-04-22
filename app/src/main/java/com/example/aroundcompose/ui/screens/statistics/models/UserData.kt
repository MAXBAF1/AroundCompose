package com.example.aroundcompose.ui.screens.statistics.models

import java.util.UUID

data class UserData(
    val id: UUID,
    val position: Int,
    val imageId: Int,
    val name: String,
    val score: Int
)
