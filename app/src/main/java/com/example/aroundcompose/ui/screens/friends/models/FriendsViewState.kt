package com.example.aroundcompose.ui.screens.friends.models

import com.example.aroundcompose.R
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.ui.common.enums.Teams
import java.util.UUID

data class FriendsViewState(
    val friendsList: List<FriendDTO> = listOf(),
    val friendsFilteredList: List<FriendDTO>? = listOf(),
    val usersList: List<FriendDTO>? = listOf(),
    val searchText: String = "",
)