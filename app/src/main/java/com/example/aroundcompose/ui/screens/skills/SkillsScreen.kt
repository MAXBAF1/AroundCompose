package com.example.aroundcompose.ui.screens.skills

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.example.aroundcompose.ui.theme.JetAroundTheme


class SkillsScreen(
    private val viewModel: SkillsViewModel,

    ) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(JetAroundTheme.colors.primaryBackground)
                    .padding(start = 30.dp, top = 30.dp, end = 30.dp)
            ) {

            }
        }

        CustomTopAppBar(
            textId = R.string.skills,
            isBackButtonNeeded = false,
            showMoney = true,
            onBackClick = {}
        )
    }
}
