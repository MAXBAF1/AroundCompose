package com.example.aroundcompose.ui.screens.friends.models

sealed class FriendsEvent {
    data object SetUsersList: FriendsEvent()
    data class OnSearchTextChange(val text: String) : FriendsEvent()
}