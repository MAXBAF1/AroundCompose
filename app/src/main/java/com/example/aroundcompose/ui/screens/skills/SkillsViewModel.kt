package com.example.aroundcompose.ui.screens.skills

import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.data.models.UserDTO
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
    private var userInfo: UserDTO? = UserDTO()
    private var skills: List<SkillDTO> = listOf()
    private var skillsStates: MutableList<Boolean> = mutableListOf()
    private val userInfoService = UserInfoService(tokenManager)
    private val skillsService = SkillsService(tokenManager)

    init {
        setUserInfo()
    }

    override fun obtainEvent(viewEvent: SkillsEvent) {
        when (viewEvent) {
            is SkillsEvent.ClickOnCard -> clickOnCard(viewEvent.index)
            is SkillsEvent.ClickUpgradeBtn -> clickUpgradeBtn(viewEvent.index)
        }
    }

    private fun setUserInfo() {
        viewModelScope.launch {
            userInfo = userInfoService.getMe()

            skillsService.getUserSkills(userInfo?.id ?: return@launch)?.let { userSkills ->
                skills = userSkills.toList()
                skillsStates = MutableList(userSkills.size) { false }

                viewState.update {
                    it.copy(
                        coins = userInfo?.coins ?: return@launch,
                        skills = skills.toList(),
                        skillsStates = skillsStates.toList()
                    )
                }
            }
        }
    }

    private fun clickOnCard(index: Int) {
        skillsStates[index] = !skillsStates[index]

        viewState.update { it.copy(skillsStates = skillsStates.toList()) }
    }

    private fun clickUpgradeBtn(index: Int) {
        viewModelScope.launch {
            val skill = skills[index]

            when (skillsService.buyLevels(id = skill.id)) {
                HttpStatusCode.OK -> {
                    skill.currentLevel += 1
                    userInfo?.coins = userInfo?.coins?.minus(skill.cost[skill.currentLevel])
                        ?: return@launch

                    viewState.update {
                        it.copy(
                            coins = userInfo?.coins ?: return@launch,
                            skills = skills.toList()
                        )
                    }
                }

                else -> return@launch // FIXME сделать обработку ошибок
            }
        }
    }
}