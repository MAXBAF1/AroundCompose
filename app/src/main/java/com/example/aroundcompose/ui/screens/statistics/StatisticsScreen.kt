package com.example.aroundcompose.ui.screens.statistics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsEvent
import com.example.aroundcompose.ui.screens.statistics.models.FriendDTO
import com.example.aroundcompose.ui.screens.statistics.views.ButtonListView
import com.example.aroundcompose.ui.screens.statistics.views.HexagonView
import com.example.aroundcompose.ui.screens.statistics.views.ListBtn
import com.example.aroundcompose.ui.screens.statistics.views.StatisticTeamView
import com.example.aroundcompose.ui.screens.statistics.views.UserCard
import com.example.aroundcompose.ui.theme.JetAroundTheme

class StatisticsScreen(
    private val viewModel: StatisticsViewModel,
    private val onBackClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        ) {
            CustomTopAppBar(
                textId = R.string.statistic,
                onBackClick = onBackClicked
            )
            TeamsStatistics(Modifier.weight(0.45f))
            TopLists(
                currentButton = viewState.currentButton,
                modifier = Modifier.weight(0.55F)
            )
        }
    }

    @Composable
    private fun TeamsStatistics(modifier: Modifier) {
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

            StatisticTeamView(
                currentPercent = 69F,
                team = Teams.LIGHT_BLUE,
                isLeader = true
            ).Create()

            StatisticTeamView(
                currentPercent = 20F,
                team = Teams.YELLOW
            ).Create()

            StatisticTeamView(
                currentPercent = 10F,
                team = Teams.PURPLE
            ).Create()

            StatisticTeamView(
                currentPercent = 1F,
                team = Teams.BLUE
            ).Create()

            HexagonView(Modifier.offset(x = 249.dp, y = 146.dp))
        }
    }

    @Composable
    private fun TopLists(currentButton: Boolean, modifier: Modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .clip(JetAroundTheme.shapes.teamsStatisticsShape)
                .background(JetAroundTheme.colors.textColor)
        ) {
            ListButtons(
                currentButton = currentButton,
                onClick = {
                    viewModel.obtainEvent(StatisticsEvent.OnListBtnClick)
                }
            )

            Column(
                modifier = modifier
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                UserCard(
                    position = 1,
                    imageId = R.drawable.avatar_example,
                    name = "Egor332",
                    score = 694,
                    team = Teams.LIGHT_BLUE,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(14.dp))

                UserCard(
                    position = 2,
                    imageId = R.drawable.avatar_example,
                    name = "Danila",
                    score = 537,
                    team = Teams.YELLOW,
                    isCurrentUser = true,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(14.dp))

                UserCard(
                    position = 3,
                    imageId = R.drawable.avatar_example,
                    name = "BAF1",
                    score = 356,
                    team = Teams.PURPLE,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(14.dp))

                UserCard(
                    position = 4,
                    imageId = R.drawable.avatar_example,
                    name = "Char32",
                    score = 235,
                    team = Teams.BLUE,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(14.dp))

                ListBtn(
                    onClick = {

                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    @Composable
    private fun ContainerList(usersList: List<FriendDTO>) {
        LazyColumn {

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
                textId = R.string.server,
                currentButton = currentButton,
                onClick = onClick
            )

            ButtonListView(
                textId = R.string.friends,
                currentButton = !currentButton,
                onClick = onClick
            )
        }
    }
}