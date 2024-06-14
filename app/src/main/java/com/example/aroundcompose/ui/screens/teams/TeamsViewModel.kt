package com.example.aroundcompose.ui.screens.teams

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.example.aroundcompose.data.TokenManager
import com.example.aroundcompose.data.db.DatabaseRepository
import com.example.aroundcompose.data.db.entities.Settings
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.data.services.SkillsService
import com.example.aroundcompose.data.services.UserInfoService
import com.example.aroundcompose.di.NotEncryptedSharedPref
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.teams.models.TeamsEvent
import com.example.aroundcompose.ui.screens.teams.models.TeamsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    @NotEncryptedSharedPref private val sharedPreferences: SharedPreferences,
    private val tokenManager: TokenManager,
    private val repository: DatabaseRepository
) :
    BaseViewModel<TeamsViewState, TeamsEvent>(initialState = TeamsViewState()) {
    private var currentTeam = Teams.NONE
    private val userInfoService = UserInfoService(tokenManager)

    override fun obtainEvent(viewEvent: TeamsEvent) {
        when (viewEvent) {
            is TeamsEvent.ChangeTeam -> changeTeam(viewEvent.team)
            TeamsEvent.ClickNextBtn -> clickNextBtn()
        }
    }

    private fun changeTeam(currentTeam: Teams) {
        this.currentTeam = currentTeam
        viewState.update {
            it.copy(currentTeam = currentTeam, isEnableNextBtn = currentTeam != Teams.NONE)
        }
    }

    private fun clickNextBtn() {
        viewModelScope.launch {
            when (userInfoService.patchMe(teamId = currentTeam.value)) {
                HttpStatusCode.OK -> {
                    if (!isFirstRun()) {
                        val userInfoService = UserInfoService(tokenManager)
                        val skillsService = SkillsService(tokenManager)

                        val userInfo = userInfoService.getMe()
                        var skills: List<SkillDTO> = listOf()

                        skillsService
                            .getUserSkills(userInfo?.id ?: return@launch)
                            ?.let { userSkills ->
                                skills = userSkills.toList()
                            }

                        repository.insertNewAccountData(userInfo, "0 0")
                        skills.forEach { skill ->
                            repository.insertNewSkillData(skill)
                        }
                        repository.insertNewSettingsData(
                            Settings(
                                toggleNotification = false,
                                theme = "Авто",
                                language = "Русский"
                            )
                        )
                    }

                    viewState.update { it.copy(toNextScreen = true) }
                }

                else -> viewState.update { it.copy(toNextScreen = false) } // FIXME сделать обработку ошибок
            }
        }
    }

    private fun isFirstRun(): Boolean {
        val containsFirstRun = sharedPreferences.contains("firstRun")

        if (!containsFirstRun) {
            sharedPreferences
                .edit()
                .putBoolean("firstRun", false)
                .apply()
        }

        return containsFirstRun
    }
}