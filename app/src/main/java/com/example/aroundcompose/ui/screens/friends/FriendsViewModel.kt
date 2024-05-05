package com.example.aroundcompose.ui.screens.friends

import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.friends.models.FriendData
import com.example.aroundcompose.ui.screens.friends.models.FriendsEvent
import com.example.aroundcompose.ui.screens.friends.models.FriendsViewState
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

class FriendsViewModel @Inject constructor() :
    BaseViewModel<FriendsViewState, FriendsEvent>(initialState = FriendsViewState()) {
    private val friendsList: MutableList<FriendData> = mutableListOf(
        FriendData(
            id = UUID.randomUUID(),
            level = 3,
            team = Teams.YELLOW,
            position = 1,
            imageId = R.drawable.avatar_example,
            name = "MagicHero11",
            score = 560
        ),
        FriendData(
            id = UUID.randomUUID(),
            level = 5,
            team = Teams.LIGHT_BLUE,
            position = 2,
            imageId = R.drawable.avatar_example,
            name = "IdiRabotaiOlen'",
            score = 320
        ),
        FriendData(
            id = UUID.randomUUID(),
            level = 8,
            team = Teams.BLUE,
            position = 3,
            imageId = R.drawable.avatar_example,
            name = "Test228",
            score = 220
        ),
        FriendData(
            id = UUID.randomUUID(),
            level = 1,
            team = Teams.PURPLE,
            position = 4,
            imageId = R.drawable.avatar_example,
            name = "Dinozavr",
            score = 180
        ),
        FriendData(
            id = UUID.randomUUID(),
            level = 2,
            team = Teams.YELLOW,
            position = 5,
            imageId = R.drawable.avatar_example,
            name = "Fredy Fasber",
            score = 150
        ),
        FriendData(
            id = UUID.randomUUID(),
            level = 8,
            team = Teams.LIGHT_BLUE,
            position = 6,
            imageId = R.drawable.avatar_example,
            name = "Oleg Mongol",
            score = 100
        ),
        FriendData(
            id = UUID.randomUUID(),
            level = 1,
            team = Teams.PURPLE,
            position = 7,
            imageId = R.drawable.avatar_example,
            name = "Outsider",
            score = 80
        ),
        FriendData(
            id = UUID.randomUUID(),
            level = 2,
            team = Teams.BLUE,
            position = 8,
            imageId = R.drawable.avatar_example,
            name = "Anime",
            score = 50
        ),
    )
    private val friendsFilteredList: MutableList<FriendData> = mutableListOf()
    private var searchText: String = ""
    private var isEventSheetShowed: Boolean = false

    override fun obtainEvent(viewEvent: FriendsEvent) {
        when (viewEvent) {
            is FriendsEvent.OnSearchTextChanged -> {
                searchText = viewEvent.text
                searchList(searchText)

                viewState.update {
                    it.copy(
                        searchText = searchText,
                        friendsFilteredList = friendsFilteredList
                    )
                }
            }
        }
    }

    private fun searchList(text: String) {
        friendsFilteredList.clear()

        friendsList.forEach { friendCard ->
            if (friendCard.name.lowercase().contains(text.lowercase())) {
                friendsFilteredList.add(friendCard)
            }
        }
    }
}