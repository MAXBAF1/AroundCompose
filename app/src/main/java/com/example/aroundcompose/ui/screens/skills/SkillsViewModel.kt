package com.example.aroundcompose.ui.screens.skills

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.NetworkService
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.skills.models.SkillData
import com.example.aroundcompose.ui.screens.skills.models.SkillsEvent
import com.example.aroundcompose.ui.screens.skills.models.SkillsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillsViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<SkillsViewState, SkillsEvent>(initialState = SkillsViewState()) {
    private var skills: List<SkillData> = listOf()
    private var skillsStates: MutableList<Boolean> = mutableListOf()
    private val networkService = NetworkService(tokenManager)

    override fun obtainEvent(viewEvent: SkillsEvent) {
        when (viewEvent) {
            is SkillsEvent.ClickOnCard -> {
                skillsStates[viewEvent.index] = !skillsStates[viewEvent.index]

                viewState.update { it.copy(skillsStates = skillsStates.toList()) }
            }

            is SkillsEvent.ClickUpgradeBtn -> {}

            SkillsEvent.GetInfo -> {
                viewModelScope.launch {
                    val id = networkService.getMe()?.id ?: return@launch

                    networkService.getUserSkills(id)?.let { skills ->
                        this@SkillsViewModel.skills = skills.toList()
                        skillsStates = MutableList(skills.size) { false }
                        viewState.update { it.copy(
                            skills = skills.toList(),
                            skillsStates = skillsStates.toList()
                        ) }
                    }
                }
            }
        }
    }
}