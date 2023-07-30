package com.example.mooddiarycompose.core.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument

abstract class Destination(private val navController: NavController) {
    abstract val routeName: String
    abstract val defaultDestination: String

    abstract fun NavGraphBuilder.buildGraph()

    fun navigate(path: String) {
        navController.navigate(path)
    }
}

private interface NavProperty {
    val key: String
    val namedNavArgument: NamedNavArgument
}

object ShowBottomNavArg: NavProperty {
    override val key: String = "show_bottom_nav_bar"
    override val namedNavArgument: NamedNavArgument = navArgument(key) {
        type = NavType.BoolType
        defaultValue = true
    }
}