package com.example.aroundcompose.ui.screens.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.common.views.SearchView
import com.example.aroundcompose.ui.screens.friends.models.FriendData
import com.example.aroundcompose.ui.screens.friends.models.FriendsEvent
import com.example.aroundcompose.ui.screens.friends.views.FriendCard

class FriendsScreen(
    private val viewModel: FriendsViewModel,
    private val onBackClick: () -> Unit,
    private val onMoreInfoClick: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

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

                if (viewState.searchText == "") {
                    FriendsContainer(
                        list = viewState.friendsList,
                        onMoreInfoClick = onMoreInfoClick
                    )
                } else {
                    FriendsContainer(
                        list = viewState.friendsFilteredList,
                        onMoreInfoClick = onMoreInfoClick
                    )
                }
            }
        }
    }

    @Composable
    private fun FriendsContainer(
        list: List<FriendData>,
        onMoreInfoClick: () -> Unit,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(list.size) { index ->
                FriendCard(
                    friendData = list[index],
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