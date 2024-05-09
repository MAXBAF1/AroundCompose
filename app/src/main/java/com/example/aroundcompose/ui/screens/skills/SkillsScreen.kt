package com.example.aroundcompose.ui.screens.skills

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.aroundcompose.ui.screens.skills.models.SkillData
import com.example.aroundcompose.ui.screens.skills.models.SkillType
import com.example.aroundcompose.ui.screens.skills.models.SkillsEvent
import com.example.aroundcompose.ui.screens.skills.views.CardView


class SkillsScreen(
    private val viewModel: SkillsViewModel,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        Box {
            Image(
                painter = painterResource(id = R.drawable.skills_background),
                contentDescription = "backgroundImage",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 90.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, top = 30.dp, end = 30.dp)
            ) {
                CustomTopAppBar(
                    textId = R.string.skills,
                    isBackButtonNeeded = false,
                    showMoney = true,
                    onBackClick = {}
                )

                SkillsContainer(
                    mapOfSkills = viewState.mapOfSkills,
                    onCardClick = { type ->
                        viewModel.obtainEvent(SkillsEvent.ClickOnCard(type))
                    },
                    onUpgradeClick = { type ->
                        viewModel.obtainEvent(SkillsEvent.ClickUpgradeBtn(type))
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
        }
    }

    @Composable
    private fun SkillsContainer(
        mapOfSkills: Map<SkillType, SkillData>,
        onCardClick: (skillType: SkillType) -> Unit,
        onUpgradeClick: (skillType: SkillType) -> Unit,
        modifier: Modifier,
    ) {
        Column(modifier) {
            SkillType.values().forEachIndexed { index, type ->
                CardView(
                    skillData = mapOfSkills[type]!!,
                    onCardClick = { onCardClick(type) },
                    onUpgradeClick = { onUpgradeClick(type) },
                    modifier = if (index == 0) Modifier.padding(top = 24.dp) else Modifier
                ).Create()

                if (type != SkillType.MINE) Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
