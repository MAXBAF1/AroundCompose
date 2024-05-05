package com.example.aroundcompose.ui.screens.friends.models

import com.example.aroundcompose.ui.common.enums.Teams
import java.util.UUID

data class FriendData(
    val id: UUID,
    val level: Int = 1,
    val team: Teams,
    val position: Int,
    val imageId: Int,
    val name: String = "",
    val score: Int = 0
)