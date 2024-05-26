package com.example.aroundcompose.ui.screens.skills.models

data class SkillsViewState(
    val skills: List<SkillData> = listOf(),
    val skillsStates: List<Boolean> = listOf()
)