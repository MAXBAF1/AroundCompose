package com.example.aroundcompose.ui.screens.skills.models

data class SkillData(
    val id: Int = 1,
    val iconId: Int,
    val imageId: Int,
    val titleId: Int,
    val descriptionId: Int,
    val currentLevel: Int = 1,
    val maxLevel: Int = 10,
    val price: Int = 0,
    val isCardClicked: Boolean = false,
)
