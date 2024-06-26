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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.screens.skills.models.SkillsEvent
import com.example.aroundcompose.ui.screens.skills.views.CardView
import com.example.aroundcompose.ui.theme.JetAroundTheme

class SkillsScreen(
    private val viewModel: SkillsViewModel,
    private val onBackClick: () -> Unit,
    private val userId: Int,
) {
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

        Surface(color = JetAroundTheme.colors.primaryBackground) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.skills_background),
                    contentDescription = "backgroundImage",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(top = 90.dp),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(JetAroundTheme.colors.primary)
                )

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
                        textId = R.string.skills,
                        showMoney = userId == -1,
                        numberOfCoins = viewState.coins,
                        onBackClick = if (userId != -1) onBackClick else null
                    )

                    SkillsContainer(
                        skills = viewState.skills,
                        skillState = viewState.skillsStates,
                        onCardClick = { index ->
                            viewModel.obtainEvent(SkillsEvent.ClickOnCard(index))
                        },
                        onUpgradeClick = { index ->
                            viewModel.obtainEvent(SkillsEvent.ClickUpgradeBtn(index))
                        },
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }

    @Composable
    private fun SkillsContainer(
        skills: List<SkillDTO>,
        skillState: List<Boolean>,
        onCardClick: (index: Int) -> Unit,
        onUpgradeClick: (index: Int) -> Unit,
        modifier: Modifier,
    ) {
        Column(modifier) {
            skills.forEachIndexed { index, skill ->
                CardView(
                    skillData = skill,
                    onCardClick = { onCardClick(index) },
                    onUpgradeClick = { onUpgradeClick(index) },
                    isUpgradable = userId == -1,
                    isCardClicked = skillState[index],
                    modifier = if (index == 0) Modifier.padding(top = 24.dp) else Modifier
                ).Create()

                if (index != skills.size) Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
