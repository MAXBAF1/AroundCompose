package com.example.aroundcompose.ui.screens.teams

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.aroundcompose.ui.theme.JetAroundStyle
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.ui.theme.LocalSettingsEventBus
import com.example.aroundcompose.utils.UpdateThemeStyleByTeam

class SelectTeamScreen(
    private val viewModel: TeamsViewModel,
    private val onNextClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        UpdateThemeStyleByTeam(viewState.currentTeam)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(JetAroundTheme.colors.primaryBackground)
                .padding(start = 30.dp, top = 125.dp, end = 30.dp)
        ) {
            Title()

            GridLayoutTeams(
                currentTeam = viewState.currentTeam,
                onClick = { viewModel.obtainEvent(TeamsEvent.ChangeTeam(it)) },
                modifier = Modifier.padding(top = 40.dp)
            )

            NextButtonView(
                enabled = viewState.isEnableNextBtn,
                onClick = { viewModel.obtainEvent(TeamsEvent.ClickNextBtn) },
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
    private fun GridLayoutTeams(currentTeam: Teams, onClick: (Teams) -> Unit, modifier: Modifier) {
        Column(modifier) {
            Row(modifier = Modifier.padding(bottom = 12.dp)) {
                TeamView(modifier = Modifier.padding(end = 12.dp),
                    containerColor = JetAroundTheme.colors.blue,
                    isEnable = currentTeam == Teams.BLUE,
                    onClick = { onClick(Teams.BLUE) })
                TeamView(containerColor = JetAroundTheme.colors.purple,
                    isEnable = currentTeam == Teams.PURPLE,
                    onClick = { onClick(Teams.PURPLE) })
            }
            Row {
                TeamView(modifier = Modifier.padding(end = 12.dp),
                    containerColor = JetAroundTheme.colors.yellow,
                    isEnable = currentTeam == Teams.YELLOW,
                    onClick = { onClick(Teams.YELLOW) })
                TeamView(containerColor = JetAroundTheme.colors.lightBlue,
                    isEnable = currentTeam == Teams.LIGHT_BLUE,
                    onClick = { onClick(Teams.LIGHT_BLUE) })
            }
        }
    }
}