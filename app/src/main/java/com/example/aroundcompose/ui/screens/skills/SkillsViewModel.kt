package com.example.aroundcompose.ui.screens.skills

import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.skills.models.SkillData
import com.example.aroundcompose.ui.screens.skills.models.SkillType
import com.example.aroundcompose.ui.screens.skills.models.SkillsEvent
import com.example.aroundcompose.ui.screens.skills.models.SkillsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SkillsViewModel @Inject constructor() :
    BaseViewModel<SkillsViewState, SkillsEvent>(initialState = SkillsViewState()) {
    private val mapOfSkills: HashMap<SkillType, SkillData> = hashMapOf(
        SkillType.SQUARE to SkillData(
            iconId = R.drawable.ic_square,
            imageId = R.drawable.image_square,
            titleId = R.string.title_square,
            descriptionId = R.string.desc_square,
        ),
        SkillType.BOMB to SkillData(
            iconId = R.drawable.ic_bomb,
            imageId = R.drawable.image_square,
            titleId = R.string.title_bomb,
            descriptionId = R.string.desc_bomb,
        ),
        SkillType.INVISIBLE to SkillData(
            iconId = R.drawable.ic_invisible_user,
            imageId = R.drawable.image_square,
            titleId = R.string.title_invisible,
            descriptionId = R.string.desc_invisible,
        ),
        SkillType.MINE to SkillData(
            iconId = R.drawable.ic_mine,
            imageId = R.drawable.image_square,
            titleId = R.string.title_mine,
            descriptionId = R.string.desc_mine,
        )
    )

    override fun obtainEvent(viewEvent: SkillsEvent) {
        when (viewEvent) {
            is SkillsEvent.ClickOnCard -> {
                mapOfSkills[viewEvent.type] = SkillData(
                    iconId = mapOfSkills[viewEvent.type]?.iconId!!,
                    imageId = mapOfSkills[viewEvent.type]?.imageId!!,
                    titleId = mapOfSkills[viewEvent.type]?.titleId!!,
                    descriptionId =mapOfSkills[viewEvent.type]?.descriptionId!!,
                    isCardClicked = !mapOfSkills[viewEvent.type]?.isCardClicked!!
                )

                viewState.update {
                    it.copy(
                        mapOfSkills = mapOfSkills.toMap()
                    )
                }
            }

            is SkillsEvent.ClickUpgradeBtn -> {}
        }
    }
}