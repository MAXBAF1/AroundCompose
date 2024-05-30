package com.example.aroundcompose.ui.screens.statistics.models

import com.example.aroundcompose.data.models.FriendDTO

data class StatisticsViewState(
    val teamsProgressMap: Map<Int, Float> = mapOf(),
    val serverList: List<Pair<Boolean, FriendDTO>>? = listOf(),
    val friendsList: List<Pair<Boolean, FriendDTO>>? = listOf(),
    val currentButton: Boolean = true,
)