package com.example.aroundcompose.ui.screens.statistics.models

import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.ui.common.enums.Teams

data class StatisticsViewState(
    val serverList: List<FriendDTO> = listOf(),
    val friendsList: List<FriendDTO> = listOf(),
    val mapOfTeamsProgress: Map<Teams, Float> = mapOf(
        Teams.LIGHT_BLUE to 69F,
        Teams.YELLOW to 20F,
        Teams.PURPLE to 10F,
        Teams.BLUE to 1F
    ),
    val currentButton: Boolean = true,
)