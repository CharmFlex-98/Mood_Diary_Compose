package com.example.mooddiarycompose.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mooddiarycompose.R
import com.example.mooddiarycompose.home.navigation.HomeDestination
import com.example.mooddiarycompose.ui.theme.MoodDiaryComposeTheme
import com.example.mooddiarycompose.util.Destination

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoodDiaryComposeTheme {
                // A surface container using the 'background' color from the theme
                AppScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    fun AppScreen() {
        val navController = rememberNavController()
        val snackbarHostState = remember { SnackbarHostState() }
        val destinations = remember { createDestinations(navController) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = { BottomNavigationBar(navController) }
        ) {
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                navController = navController,
                startDestination = HomeDestination.ROOT
            ) {
                destinations.forEach { destination ->
                    with(destination) { buildGraph() }
                }
            }
        }
    }

    private fun createDestinations(navController: NavController): List<Destination> {
        return listOf(HomeDestination(navController = navController))
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        NavigationBar {
            NavigationBarItem(
                label = { Text("Overview") },
                selected = navController.currentDestination?.navigatorName == HomeDestination.ROOT,
                onClick = { navController.navigate(HomeDestination.ROOT) },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home"
                    )
                })
            NavigationBarItem(
                label = { Text("Statistic") },
                selected = navController.currentDestination?.navigatorName == HomeDestination.STATISTIC,
                onClick = { navController.navigate(HomeDestination.STATISTIC) },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_stat),
                        contentDescription = "statistic"
                    )
                })
        }
    }
}




