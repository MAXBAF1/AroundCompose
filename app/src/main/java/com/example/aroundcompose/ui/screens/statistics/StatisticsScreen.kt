package com.example.aroundcompose.ui.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import com.example.aroundcompose.ui.screens.statistics.views.HexagonView
import com.example.aroundcompose.ui.screens.statistics.views.StatisticTeamView
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

            Spacer(modifier = Modifier.weight(0.55f))
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
                team = Teams.LIGHT_BLUE
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
    private fun TopLists() {

    }

    @Composable
    private fun ListButtons() {

    }
}