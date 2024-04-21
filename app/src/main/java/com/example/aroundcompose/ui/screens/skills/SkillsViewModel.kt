package com.example.aroundcompose.ui.screens.skills

import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.skills.models.SkillsEvent
import com.example.aroundcompose.ui.screens.skills.models.SkillsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SkillsViewModel @Inject constructor() :
    BaseViewModel<SkillsViewState, SkillsEvent>(initialState = SkillsViewState()) {

}