package com.example.aroundcompose.ui.screens.greetings

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.data.services.AuthenticationService
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.navigation.Screens
import com.example.aroundcompose.ui.screens.greetings.models.GreetingsEvent
import com.example.aroundcompose.ui.screens.greetings.models.GreetingsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GreetingsViewModel @Inject constructor(private val tokenManager: TokenManager) :
    BaseViewModel<GreetingsViewState, GreetingsEvent>(GreetingsViewState()) {
    private val authService = AuthenticationService(tokenManager)
    private val userInfoService = UserInfoService(tokenManager)

    init {
        setNewScreen()
    }

    override fun obtainEvent(viewEvent: GreetingsEvent) {

    }

    private fun setNewScreen() {
        val tokens = tokenManager.getTokens()
        viewModelScope.launch {
            var me: UserDTO? = null
            val newScreens = if (tokens == null) {
                Screens.AuthorizationScreen
            } else {
                me = userInfoService.getMe()
                if (me == null) {
                    if (authService.refresh() == HttpStatusCode.OK) {
                        Screens.MapScreen
                    } else Screens.AuthorizationScreen
                } else Screens.MapScreen
            }

            viewState.update {
                it.copy(
                    newScreens = newScreens,
                    team = Teams.getById(me?.teamId ?: Random.nextInt(1, 5))
                )
            }
        }
    }
}