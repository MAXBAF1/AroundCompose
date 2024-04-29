package com.example.aroundcompose.ui.screens.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.common.views.SearchView
import com.example.aroundcompose.ui.screens.friends.models.FriendData
import com.example.aroundcompose.ui.screens.friends.models.FriendsEvent
import com.example.aroundcompose.ui.screens.friends.views.FriendCard
import java.util.UUID

class FriendsScreen(
    private val viewModel: FriendsViewModel,
    private val onBackClick: () -> Unit,
    private val onMoreInfoClick: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        var isEventSheetShowed by remember { mutableStateOf(false) }

        Box {
            Image(
                painter = painterResource(id = R.drawable.friends_background),
                contentDescription = "backgroundImage",
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                contentScale = ContentScale.Crop
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, top = 30.dp, end = 30.dp)
            ) {
                CustomTopAppBar(
                    modifier = Modifier.padding(bottom = 24.dp),
                    textId = R.string.friends,
                    onBackClick = onBackClick
                )

                SearchView(
                    modifier = Modifier
                        .padding(bottom = 22.dp),
                    restoredValue = viewState.searchText,
                    onValueChange = {
                        viewModel.obtainEvent(FriendsEvent.OnSearchTextChanged(it))
                    }
                )

                FriendCard(
                    friendData = FriendData(
                        id = UUID.randomUUID(),
                        level = 3,
                        team = Teams.YELLOW,
                        position = 1,
                        imageId = R.drawable.avatar_example,
                        name = "MagicHero11",
                        score = 560
                    ),
                    onMoreInfoClick = onMoreInfoClick
                ).Create(Modifier)

                FriendCard(
                    friendData = FriendData(
                        id = UUID.randomUUID(),
                        level = 5,
                        team = Teams.LIGHT_BLUE,
                        position = 2,
                        imageId = R.drawable.avatar_example,
                        name = "IdiRabotaiOlen'",
                        score = 320
                    ),
                    onMoreInfoClick = onMoreInfoClick
                ).Create(Modifier.padding(top = 16.dp))

                FriendsContainer(
                    friendsList = viewState.friendsList,
                    onMoreInfoClick = onMoreInfoClick
                )
            }
        }
    }

    @Composable
    private fun FriendsContainer(
        friendsList: List<FriendData>,
        onMoreInfoClick: () -> Unit,
    ) {
        LazyColumn {
            items(friendsList.size) { index ->
                FriendCard(
                    friendData = friendsList[index],
                    onMoreInfoClick = onMoreInfoClick
                ).Create(
                    modifier = if (index != 0) {
                        Modifier.padding(top = 16.dp)
                    } else Modifier
                )
            }
        }
    }
}