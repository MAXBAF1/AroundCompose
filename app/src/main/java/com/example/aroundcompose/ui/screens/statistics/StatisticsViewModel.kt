package com.example.aroundcompose.ui.screens.statistics

import com.example.aroundcompose.ui.common.models.BaseViewModel
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsEvent
import com.example.aroundcompose.ui.screens.statistics.models.StatisticsViewState
import javax.inject.Inject

class StatisticsViewModel @Inject constructor() :
    BaseViewModel<StatisticsViewState, StatisticsEvent>(initialState = StatisticsViewState()) {

}