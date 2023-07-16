package com.example.mooddiarycompose.util

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

abstract class Destination(private val navController: NavController) {
    abstract val routeName: String
    abstract val defaultDestination: String

    abstract fun NavGraphBuilder.buildGraph()

    fun navigate(path: String) {
        navController.navigate(path)
    }
}