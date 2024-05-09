package com.example.aroundcompose.ui.screens.skills.models

import com.example.aroundcompose.R

data class SkillsViewState(
    val mapOfSkills: Map<SkillType, SkillData> = mapOf(
        SkillType.SQUARE to SkillData(
            iconId = R.drawable.ic_square,
            imageId = R.drawable.skill_square,
            titleId = R.string.title_square,
            descriptionId = R.string.desc_square,
            currentLevel = 2
        ),
        SkillType.BOMB to SkillData(
            iconId = R.drawable.ic_bomb,
            imageId = R.drawable.skill_bomb,
            titleId = R.string.title_bomb,
            descriptionId = R.string.desc_bomb,
            currentLevel = 10
        ),
        SkillType.INVISIBLE to SkillData(
            iconId = R.drawable.ic_invisible_user,
            imageId = R.drawable.skill_invisible,
            titleId = R.string.title_invisible,
            descriptionId = R.string.desc_invisible,
            currentLevel = 5
        ),
        SkillType.MINE to SkillData(
            iconId = R.drawable.ic_mine,
            imageId = R.drawable.skill_mine,
            titleId = R.string.title_mine,
            descriptionId = R.string.desc_mine,
            currentLevel = 8
        )
    ),
)