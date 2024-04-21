package com.example.aroundcompose.ui.screens.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.views.CustomTopAppBar

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
        }
    }

    @Composable
    private fun TeamsStatistics() {

    }
}