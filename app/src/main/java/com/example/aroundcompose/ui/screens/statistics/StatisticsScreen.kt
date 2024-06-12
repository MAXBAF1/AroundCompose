package com.example.aroundcompose.ui.screens.statistics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.data.models.FriendDTO
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsEvent
import com.example.aroundcompose.ui.screens.statistics.views.ButtonListView
import com.example.aroundcompose.ui.screens.statistics.views.HexagonView
import com.example.aroundcompose.ui.screens.statistics.views.StatisticTeamView
import com.example.aroundcompose.ui.screens.statistics.views.UserCard
import com.example.aroundcompose.ui.theme.JetAroundTheme

class StatisticsScreen(
    private val viewModel: StatisticsViewModel,
    private val toUserScreen: (id: Int) -> Unit,
    private val onBackClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        Surface(color = JetAroundTheme.colors.primaryBackground) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = JetAroundTheme.margins.mainMargin,
                        end = JetAroundTheme.margins.mainMargin
                    )
            ) {
                CustomTopAppBar(
                    textId = R.string.statistic, onBackClick = onBackClicked
                )
                TeamsStatistics(viewState.teamsProgressMap, Modifier.weight(0.45f))

                TopLists(
                    currentButton = viewState.currentButton,
                    list = if (viewState.currentButton) viewState.serverList else viewState.friendsList,
                    onMoreInfoClick = toUserScreen,
                    modifier = Modifier.weight(0.55F)
                )
            }
        }
    }

    @Composable
    private fun TeamsStatistics(teamsProgress: Map<Int, Float>, modifier: Modifier) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = modifier
                .fillMaxSize()
                .padding(top = 24.dp)
                .clip(JetAroundTheme.shapes.teamsStatisticsShape)
                .background(JetAroundTheme.colors.textColor)
        ) {
            Text(
                text = stringResource(id = R.string.teams),
                style = JetAroundTheme.typography.percent,
                color = JetAroundTheme.colors.primaryBackground,
                modifier = Modifier
                    .padding(start = 20.dp, top = 16.dp)
                    .align(Alignment.TopStart)
            )
            HexagonView(Modifier.offset(x = (-47).dp, y = 43.dp))

            teamsProgress[4]?.let {
                StatisticTeamView(
                    currentPercent = it, team = Teams.LIGHT_BLUE, isLeader = true
                ).Create()
            }

            teamsProgress[3]?.let {
                StatisticTeamView(
                    currentPercent = it, team = Teams.YELLOW
                ).Create()
            }

            teamsProgress[2]?.let {
                StatisticTeamView(
                    currentPercent = it, team = Teams.PURPLE
                ).Create()
            }

            teamsProgress[1]?.let {
                StatisticTeamView(
                    currentPercent = it, team = Teams.BLUE
                ).Create()
            }

            HexagonView(Modifier.offset(x = 249.dp, y = 146.dp))
        }
    }

    @Composable
    private fun TopLists(
        currentButton: Boolean,
        list: List<Pair<Boolean, FriendDTO>>?,
        onMoreInfoClick: (id: Int) -> Unit,
        modifier: Modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .clip(JetAroundTheme.shapes.teamsStatisticsShape)
                .background(JetAroundTheme.colors.textColor)
        ) {
            ListButtons(currentButton = currentButton, onClick = {
                viewModel.obtainEvent(StatisticsEvent.OnListBtnClick)
            })

            Column(
                modifier = modifier.padding(horizontal = 20.dp)
            ) {
                if (currentButton) {
                    list?.let { ContainerList(it, onMoreInfoClick) }
                } else {
                    list?.let { ContainerList(it, onMoreInfoClick) }
                }
            }
        }
    }

    @Composable
    private fun ContainerList(
        usersList: List<Pair<Boolean, FriendDTO>>,
        onMoreInfoClick: (id: Int) -> Unit,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(usersList.size) { index ->
                UserCard(position = index + 1,
                    imageId = R.drawable.avatar_example,
                    name = usersList[index].second.username,
                    score = usersList[index].second.score,
                    isCurrentUser = usersList[index].first,
                    team = Teams.getById(usersList[index].second.teamId),
                    modifier = when (index) {
                        0 -> Modifier.padding(top = 24.dp, bottom = 7.dp)
                        usersList.size - 1 -> Modifier.padding(top = 7.dp, bottom = 20.dp)
                        else -> Modifier.padding(vertical = 7.dp)
                    },
                    onClick = {
                        onMoreInfoClick(usersList[index].second.id)
                    })
            }
        }
    }

    @Composable
    private fun ListButtons(currentButton: Boolean, onClick: () -> Unit) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clip(JetAroundTheme.shapes.teamsStatisticsShape)
                .background(JetAroundTheme.colors.primary)
                .border(
                    BorderStroke(2.dp, JetAroundTheme.colors.textColor),
                    JetAroundTheme.shapes.teamsStatisticsShape
                )
                .padding(vertical = 15.dp, horizontal = 20.dp)
        ) {
            ButtonListView(
                textId = R.string.server, currentButton = currentButton, onClick = onClick
            )

            ButtonListView(
                textId = R.string.friends, currentButton = !currentButton, onClick = onClick
            )
        }
    }
}