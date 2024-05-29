package com.example.aroundcompose.ui.screens.friends.models

sealed class FriendsEvent {
    data object GetFriendsList: FriendsEvent()
    data object GetUsersList: FriendsEvent()
    data class OnSearchTextChange(val text: String) : FriendsEvent()
}