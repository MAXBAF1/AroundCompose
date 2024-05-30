package com.example.aroundcompose.ui.screens.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.common.views.SearchView
import com.example.aroundcompose.ui.screens.friends.models.FriendsEvent
import com.example.aroundcompose.ui.screens.friends.views.FriendCard
import com.example.aroundcompose.ui.theme.JetAroundTheme
import kotlinx.coroutines.delay

class FriendsScreen(
    private val viewModel: FriendsViewModel,
    private val onBackClick: () -> Unit,
    private val toUserScreen: (id: Int) -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        LaunchedEffect(key1 = Unit) {
            viewModel.obtainEvent(FriendsEvent.GetFriendsList)
        }

        Box {
            Image(
                painter = painterResource(id = R.drawable.friends_background),
                contentDescription = "backgroundImage",
                modifier = Modifier.align(Alignment.BottomEnd),
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

                SearchView(modifier = Modifier.padding(top = 14.dp, bottom = 8.dp),
                    value = viewState.searchText,
                    onValueChange = {
                        viewModel.obtainEvent(FriendsEvent.OnSearchTextChange(it))
                    })

                if (viewState.searchText == "") {
                    UsersContainer(
                        list = viewState.friendsList, toUserScreen = toUserScreen
                    )
                } else {
                    if (!viewState.friendsFilteredList.isNullOrEmpty()) {
                        UsersContainer(
                            list = viewState.friendsFilteredList!!, toUserScreen = toUserScreen
                        )

                        HorizontalDivider(
                            thickness = 2.dp,
                            color = JetAroundTheme.colors.notActiveColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                        )
                    }

                    viewState.usersList?.let {
                        UsersContainer(list = it, toUserScreen = toUserScreen)
                    }
                }
            }
        }

        LaunchedEffect(key1 = viewState.searchText) {
            if (viewState.searchText.isBlank()) return@LaunchedEffect

            delay(200)

            viewModel.obtainEvent(FriendsEvent.GetUsersList)
        }
    }

    @Composable
    private fun UsersContainer(
        list: List<FriendDTO>,
        toUserScreen: (id: Int) -> Unit,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(list.size) { index ->
                FriendCard(position = index + 1, friendData = list[index], onMoreInfoClick = {
                    toUserScreen(list[index].id)
                }).Create(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}