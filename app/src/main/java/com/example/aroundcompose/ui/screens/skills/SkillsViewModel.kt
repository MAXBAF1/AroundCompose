package com.example.aroundcompose.ui.screens.skills

import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.skills.models.SkillData
import com.example.aroundcompose.ui.screens.skills.models.Skills
import com.example.aroundcompose.ui.screens.skills.models.SkillsEvent
import com.example.aroundcompose.ui.screens.skills.models.SkillsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SkillsViewModel @Inject constructor() :
    BaseViewModel<SkillsViewState, SkillsEvent>(initialState = SkillsViewState()) {
    private val skills: Skills = Skills()

    override fun obtainEvent(viewEvent: SkillsEvent) {
        when (viewEvent) {
            is SkillsEvent.ClickOnCard -> {
                skills[viewEvent.type] = SkillData(
                    iconId = skills[viewEvent.type].iconId,
                    imageId = skills[viewEvent.type].imageId,
                    titleId = skills[viewEvent.type].titleId,
                    descriptionId = skills[viewEvent.type].descriptionId,
                    currentLevel = skills[viewEvent.type].currentLevel,
                    maxLevel = skills[viewEvent.type].maxLevel,
                    price = skills[viewEvent.type].price,
                    isCardClicked = !skills[viewEvent.type].isCardClicked
                )

                viewState.update {
                    it.copy(
                        skills = skills.copy()
                    )
                }
            }

            is SkillsEvent.ClickUpgradeBtn -> {}
        }
    }
}