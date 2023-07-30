package com.example.mooddiarycompose.core.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mooddiarycompose.home.ui.OverViewScreen
import com.example.mooddiarycompose.home.ui.StatisticScreen

class HomeDestination(
    navController: NavController,
) : Destination(navController) {
    override val routeName = root
    override val defaultDestination = overview

    override fun NavGraphBuilder.buildGraph() {
        navigation(startDestination = defaultDestination, route = routeName) {
            buildOverviewScreenGraph()
            buildStatisticScreenGraph()
        }
    }

    private fun NavGraphBuilder.buildOverviewScreenGraph() {
        composable(route = overview, arguments = listOf(ShowBottomNavArg.namedNavArgument)) {
            OverViewScreen()
        }
    }

    private fun NavGraphBuilder.buildStatisticScreenGraph() {
        composable(route = statistic, arguments = listOf(ShowBottomNavArg.namedNavArgument)) {
            StatisticScreen()
        }
    }


    companion object {
        const val root = "home"
        const val overview = "$root/overview"
        const val statistic = "$root/statistic"
    }


}

