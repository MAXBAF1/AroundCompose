package com.example.aroundcompose.ui.screens.friends

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.data.services.FriendsService
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.friends.models.FriendsEvent
import com.example.aroundcompose.ui.screens.friends.models.FriendsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<FriendsViewState, FriendsEvent>(initialState = FriendsViewState()) {
    private val friendsService = FriendsService(tokenManager)
    private val usersService = UserInfoService(tokenManager)
    private var friendsList: List<FriendDTO>? = listOf()
    private var usersList: List<FriendDTO>? = listOf()
    private var friendsFilteredList: List<FriendDTO>? = listOf()
    private var searchText: String = ""

    override fun obtainEvent(viewEvent: FriendsEvent) {
        when (viewEvent) {
            FriendsEvent.GetUsersList -> {
                viewModelScope.launch {
                    usersList = usersService.findUser(searchText)

                    viewState.update { it.copy(usersList = usersList?.toList()) }
                }
            }

            FriendsEvent.GetFriendsList -> {
                viewModelScope.launch {
                    friendsList = friendsService.findFriends()?.sortedBy { it.score }

                    viewState.update { viewState ->
                        viewState.copy(
                            friendsList = friendsList?.toList() ?: return@launch
                        )
                    }
                }
            }

            is FriendsEvent.OnSearchTextChange -> {
                searchText = viewEvent.text

                friendsFilteredList = friendsList?.filter {
                    it.username.lowercase().contains(searchText.lowercase())
                }

                viewState.update { viewState ->
                    viewState.copy(
                        searchText = searchText,
                        friendsFilteredList = friendsFilteredList?.toList(),
                    )
                }
            }
        }
    }
}