package com.example.aroundcompose.ui.screens.friends.models

data class FriendsViewState(
    val friendsList: List<FriendData> = listOf(),
    val searchText: String = "",
    val isEventSheetShowed: Boolean = false,
)