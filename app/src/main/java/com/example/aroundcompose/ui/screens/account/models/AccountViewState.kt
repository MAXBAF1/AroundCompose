package com.example.aroundcompose.ui.screens.account.models

import com.example.aroundcompose.data.models.UserDTO

data class AccountViewState(
    val userInfo: UserDTO = UserDTO(),
    val myCells: Int = 0,
    val myTeamCells: Int = 0,
)