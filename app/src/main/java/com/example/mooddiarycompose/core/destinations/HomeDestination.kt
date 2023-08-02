package com.example.mooddiarycompose.core.destinations

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.mooddiarycompose.home.ui.AddEditScreen
import com.example.mooddiarycompose.home.ui.MainScreen
import com.example.mooddiarycompose.home.ui.overview.OverViewScreen
import com.example.mooddiarycompose.home.ui.statistic.StatisticScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeDestination(
    private val navController: NavController,
) : Destination(navController) {
    override val routeName = root
    override val defaultDestination = main

    override fun NavGraphBuilder.buildGraph() {
        navigation(startDestination = defaultDestination, route = routeName) {
            buildMainScreenGraph()
            buildAddEditScreen()
        }
    }

    private fun NavGraphBuilder.buildMainScreenGraph() {
        composable(route = main) {
            MainScreen { navController.navigate(add_edit)}
        }
    }


    @OptIn(ExperimentalAnimationApi::class)
    private fun NavGraphBuilder.buildAddEditScreen() {
        composable(route = add_edit) {
            var isVisible by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                isVisible = true
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                ) { it },
            ) {
                AddEditScreen {
                    navController.popBackStack()
                }
            }
        }
    }


    companion object {
        const val root = "home"
        const val main = "$root/main"
        const val add_edit = "$root/add_edit"
    }
}

class HomeBottomBarDestination(
    private val navController: NavController,
    override val routeName: String = root,
    override val defaultDestination: String = overview
) : Destination(navController = navController) {
    override fun NavGraphBuilder.buildGraph() {
        buildBottomNavGraph()
    }

    private fun NavGraphBuilder.buildBottomNavGraph() {
        composable(overview) { OverViewScreen() }
        composable(statistic) { StatisticScreen() }
    }

    companion object {
        const val root = "home/bottomNavBar"
        const val overview = "$root/overview"
        const val statistic = "$root/statistic"
    }

}

