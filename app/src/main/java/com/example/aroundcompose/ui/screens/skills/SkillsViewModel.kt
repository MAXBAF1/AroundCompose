package com.example.aroundcompose.ui.screens.skills

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.data.services.SkillsService
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.skills.models.SkillsEvent
import com.example.aroundcompose.ui.screens.skills.models.SkillsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillsViewModel @Inject constructor(tokenManager: TokenManager) :
    BaseViewModel<SkillsViewState, SkillsEvent>(initialState = SkillsViewState()) {
    private var coins: Int = 0
    private var skills: List<SkillDTO> = listOf()
    private var skillsStates: MutableList<Boolean> = mutableListOf()
    private val userInfoService = UserInfoService(tokenManager)
    private val skillsService = SkillsService(tokenManager)

    override fun obtainEvent(viewEvent: SkillsEvent) {
        when (viewEvent) {
            is SkillsEvent.ClickOnCard -> {
                skillsStates[viewEvent.index] = !skillsStates[viewEvent.index]

                viewState.update { it.copy(skillsStates = skillsStates.toList()) }
            }

            is SkillsEvent.ClickUpgradeBtn -> {
                viewModelScope.launch {
                    val id = skills[viewEvent.index].id

                    when (skillsService.buyLevels(id = id)) {
                        HttpStatusCode.OK -> {
                            val userInfo = userInfoService.getMe()

                            skillsService.getUserSkills(userInfo?.id ?: return@launch)
                                ?.let { skills ->
                                    this@SkillsViewModel.skills = skills.toList()
                                    this@SkillsViewModel.coins = userInfo.coins

                                    viewState.update {
                                        it.copy(
                                            coins = coins,
                                            skills = this@SkillsViewModel.skills.toList()
                                        )
                                    }
                                }
                        }

                        else -> return@launch // FIXME сделать обработку ошибок
                    }
                }
            }

            SkillsEvent.GetUserInfo -> {
                viewModelScope.launch {
                    val userInfo = userInfoService.getMe()

                    skillsService.getUserSkills(userInfo?.id ?: return@launch)?.let { skills ->
                        this@SkillsViewModel.skills = skills.toList()
                        skillsStates = MutableList(skills.size) { false }
                        this@SkillsViewModel.coins = userInfo.coins

                        viewState.update {
                            it.copy(
                                coins = coins,
                                skills = this@SkillsViewModel.skills.toList(),
                                skillsStates = skillsStates.toList()
                            )
                        }
                    }
                }
            }
        }
    }
}