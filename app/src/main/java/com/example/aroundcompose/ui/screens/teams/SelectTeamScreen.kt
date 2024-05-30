package com.example.aroundcompose.ui.screens.teams

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.views.NextButtonView
import com.example.aroundcompose.ui.screens.teams.models.TeamsEvent
import com.example.aroundcompose.ui.screens.teams.views.TeamView
import com.example.aroundcompose.ui.theme.JetAroundTheme

class SelectTeamScreen(
    private val viewModel: TeamsViewModel,
    private val onNextClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(JetAroundTheme.colors.primaryBackground)
                .padding(start = 30.dp, top = 125.dp, end = 30.dp)
        ) {
            Title()

            GridLayoutTeams(
                viewState.currentTeamId,
                onClick = {
                    viewModel.obtainEvent(TeamsEvent.ChangeTeam(it.value))
                },
                modifier = Modifier.padding(top = 40.dp)
            )

            NextButtonView(
                enabled = viewState.isEnableNextBtn,
                onClick = { viewModel.obtainEvent(TeamsEvent.ClickBtnNext) },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .align(Alignment.End)
            )

            if (viewState.toNextScreen) onNextClicked()
        }
    }

    @Composable
    private fun Title() {
        Text(
            text = stringResource(id = R.string.choose_team).uppercase(),
            style = JetAroundTheme.typography.bold24,
            color = JetAroundTheme.colors.textColor
        )
    }

    @Composable
    private fun GridLayoutTeams(currentTeamId: Int, onClick: (Teams) -> Unit, modifier: Modifier) {
        Column(modifier) {
            Row {
                TeamView(
                    containerColor = JetAroundTheme.colors.blue,
                    isEnable = currentTeamId == Teams.BLUE.value,
                    onClick = { onClick(Teams.BLUE) }
                )

                Spacer(modifier = Modifier.width(12.dp))

                TeamView(
                    containerColor = JetAroundTheme.colors.purple,
                    isEnable = currentTeamId == Teams.PURPLE.value,
                    onClick = { onClick(Teams.PURPLE) }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                TeamView(
                    containerColor = JetAroundTheme.colors.yellow,
                    isEnable = currentTeamId == Teams.YELLOW.value,
                    onClick = { onClick(Teams.YELLOW) }
                )

                Spacer(modifier = Modifier.width(12.dp))

                TeamView(
                    containerColor = JetAroundTheme.colors.lightBlue,
                    isEnable = currentTeamId == Teams.LIGHT_BLUE.value,
                    onClick = { onClick(Teams.LIGHT_BLUE) }
                )
            }
        }
    }
}