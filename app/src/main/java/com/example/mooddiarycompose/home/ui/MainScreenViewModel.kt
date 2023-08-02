package com.example.mooddiarycompose.home.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.mooddiarycompose.core.destinations.HomeBottomBarDestination
import com.example.mooddiarycompose.core.destinations.HomeDestination

class MainScreenViewModel(
    private val navController: NavController
) : ViewModel() {



    fun onAction(action: MainScreenAction) {
        when (action) {
            MainScreenAction.OverviewNavAction -> {
                navController.navigate(HomeBottomBarDestination.overview)
            }
            MainScreenAction.StatisticNavAction -> {
                navController.navigate(HomeBottomBarDestination.statistic)
            }
        }
    }
}

sealed interface MainScreenAction {
    object OverviewNavAction : MainScreenAction
    object StatisticNavAction : MainScreenAction
}