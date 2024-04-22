package com.example.aroundcompose.ui.screens.skills.models

sealed class SkillsEvent {
    data class ClickOnCard(val type: SkillType) : SkillsEvent()
    data class ClickUpgradeBtn(val type: SkillType) : SkillsEvent()
}