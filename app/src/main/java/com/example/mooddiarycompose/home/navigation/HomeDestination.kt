package com.example.mooddiarycompose.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mooddiarycompose.home.ui.OverViewScreen
import com.example.mooddiarycompose.home.ui.StatisticScreen
import com.example.mooddiarycompose.util.Destination

class HomeDestination(
    navController: NavController,
) : Destination(navController) {
    override val routeName = ROOT
    override val defaultDestination = OVERVIEW

    override fun NavGraphBuilder.buildGraph() {
        navigation(startDestination = defaultDestination, route = routeName) {
            buildOverviewScreenGraph()
            buildStatisticScreenGraph()
        }
    }

    private fun NavGraphBuilder.buildOverviewScreenGraph() {
        composable(OVERVIEW) {
            OverViewScreen()
        }
    }

    private fun NavGraphBuilder.buildStatisticScreenGraph() {
        composable(STATISTIC) {
            StatisticScreen()
        }
    }

    fun navigateToOverViewScreen() {
    }

    companion object {
        const val ROOT = "home"
        const val OVERVIEW = "$ROOT/overview"
        const val STATISTIC = "$ROOT/statistic"
    }


}

