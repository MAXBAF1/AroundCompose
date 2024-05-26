package com.example.aroundcompose.ui.screens.skills.models

sealed class SkillsEvent {
    data object GetUserInfo: SkillsEvent()
    data class ClickOnCard(val index: Int) : SkillsEvent()
    data class ClickUpgradeBtn(val index: Int) : SkillsEvent()
}