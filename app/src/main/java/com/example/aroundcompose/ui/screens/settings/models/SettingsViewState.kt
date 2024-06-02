package com.example.aroundcompose.ui.screens.settings.models

import com.example.aroundcompose.R
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.ui.common.enums.Teams
import java.util.UUID

data class SettingsViewState(

    val meInfo: UserDTO = UserDTO(),
)