package com.example.aroundcompose.ui.screens.settings

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.settings.models.SettingsEvent
import com.example.aroundcompose.ui.screens.settings.models.SettingsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val tokenManager: TokenManager) :
    BaseViewModel<SettingsViewState, SettingsEvent>(initialState = SettingsViewState()) {
    private val userInfoService = UserInfoService(tokenManager)
    private var meInfo = UserDTO()


    init {
        setMeInfo()
    }

    override fun obtainEvent(viewEvent: SettingsEvent) {
        when (viewEvent) {
            SettingsEvent.ExitFromAccount -> exitFromAccount()
        }
    }

    private fun exitFromAccount() {
        tokenManager.clearTokens()
        viewState.update { it.copy(toAuthorizationScreen = true) }
    }

    private fun setMeInfo() {
        viewModelScope.launch {
            meInfo = userInfoService.getMe() ?: return@launch
            viewState.update { it.copy(meInfo = meInfo) }
        }
    }
}