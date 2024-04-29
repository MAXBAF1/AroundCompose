package com.example.aroundcompose.ui.screens.friends

import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.friends.models.FriendData
import com.example.aroundcompose.ui.screens.friends.models.FriendsEvent
import com.example.aroundcompose.ui.screens.friends.models.FriendsViewState
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FriendsViewModel @Inject constructor() :
    BaseViewModel<FriendsViewState, FriendsEvent>(initialState = FriendsViewState()) {
    private val friendsList: MutableList<FriendData> = mutableListOf()
    private var searchText: String = ""
    private var isEventSheetShowed: Boolean = false

    override fun obtainEvent(viewEvent: FriendsEvent) {
        when (viewEvent) {
            is FriendsEvent.OnSearchTextChanged -> {
                searchText = viewEvent.text

                viewState.update {
                    it.copy(
                        searchText = searchText
                    )
                }
            }
        }
    }
}