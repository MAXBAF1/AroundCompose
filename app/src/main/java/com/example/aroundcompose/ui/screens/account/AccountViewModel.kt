package com.example.aroundcompose.ui.screens.account

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.MyInfoSingleton
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.data.services.StatisticService
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.account.models.AccountEvent
import com.example.aroundcompose.ui.screens.account.models.AccountViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<AccountViewState, AccountEvent>(initialState = AccountViewState()) {
    private val userInfoService = UserInfoService(tokenManager)
    private val statisticService = StatisticService(tokenManager)
    private var userInfo = UserDTO()
    private var myCells: Int = 0
    private var myTeamCells: Int = 0
    private var myAllTimeCells: Int = 0

    override fun obtainEvent(viewEvent: AccountEvent) {
        super.obtainEvent(viewEvent)

        when (viewEvent) {
            is AccountEvent.GetUserInfo -> getUserInfo(viewEvent.id)
        }
    }

    private fun getUserInfo(id: Int) {
        viewModelScope.launch {
            userInfo = (if (id == -1) {
                MyInfoSingleton.myInfo
            } else userInfoService.getUser(id)) ?: UserDTO()

            myCells = statisticService.getUserById(id)?.score ?: 0
            myTeamCells = statisticService.getTeam(id)?.score ?: 0

            viewState.update {
                it.copy(userInfo = userInfo.copy(), myCells = myCells, myTeamCells = myTeamCells)
            }
        }
    }
}