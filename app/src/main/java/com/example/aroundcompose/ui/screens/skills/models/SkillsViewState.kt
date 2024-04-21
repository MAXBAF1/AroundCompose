package com.example.aroundcompose.ui.screens.skills.models

import com.example.aroundcompose.R

data class SkillsViewState(
    val mapOfSkills: Map<SkillType, SkillData> = mapOf(
        SkillType.SQUARE to SkillData(
            iconId = R.drawable.ic_square,
            imageId = R.drawable.image_square,
            titleId = R.string.title_square,
            descriptionId = R.string.desc_square,
        ),
        SkillType.BOMB to SkillData(
            iconId = R.drawable.ic_bomb,
            imageId = R.drawable.image_square,
            titleId = R.string.title_square,
            descriptionId = R.string.desc_square,
        ),
        SkillType.INVISIBLE to SkillData(
            iconId = R.drawable.ic_invisible_user,
            imageId = R.drawable.image_square,
            titleId = R.string.title_square,
            descriptionId = R.string.desc_square,
        ),
        SkillType.MINE to SkillData(
            iconId = R.drawable.ic_mine,
            imageId = R.drawable.image_square,
            titleId = R.string.title_square,
            descriptionId = R.string.desc_square,
        )
    ),
)