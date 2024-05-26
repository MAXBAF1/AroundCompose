package com.example.aroundcompose.ui.screens.skills.models

import com.example.aroundcompose.data.models.SkillDTO

data class SkillsViewState(
    val coins: Int = 0,
    val skills: List<SkillDTO> = listOf(),
    val skillsStates: List<Boolean> = listOf()
)