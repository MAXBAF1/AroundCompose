package com.example.aroundcompose.ui.screens.account.models

sealed class AccountEvent {
    data class GetUserInfo(val id: Int) : AccountEvent()
}