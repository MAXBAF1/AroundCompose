package com.example.aroundcompose.ui.screens.statistics.models

sealed class StatisticsEvent {
    data object GetStatisticInfo: StatisticsEvent()
    data object OnListBtnClick: StatisticsEvent()
}