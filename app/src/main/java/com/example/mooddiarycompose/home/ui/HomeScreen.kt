package com.example.mooddiarycompose.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mooddiarycompose.core.destinations.HomeDestination
import com.example.mooddiarycompose.core.destinations.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val destinations = remember { createHomeDestinations(navController) }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navController = navController,
            startDestination = HomeDestination.root
        ) {
            destinations.forEach { destination ->
                with(destination) { buildGraph() }
            }
        }
    }
}


private fun createHomeDestinations(navController: NavController): List<Destination> {
    return listOf(HomeDestination(navController = navController))
}