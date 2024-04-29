package com.example.aroundcompose.ui.screens.friends.models

sealed class FriendsEvent {
    data class OnSearchTextChanged(val text: String) : FriendsEvent()
}