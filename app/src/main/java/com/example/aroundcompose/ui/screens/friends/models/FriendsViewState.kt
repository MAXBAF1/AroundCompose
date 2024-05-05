package com.example.aroundcompose.ui.screens.friends.models

import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.Teams
import java.util.UUID

data class FriendsViewState(
    val friendsList: List<FriendData> = listOf(
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
    ),
    val friendsFilteredList: List<FriendData> = listOf(),
    val searchText: String = "",
)