package com.example.mooddiarycompose.home.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mooddiarycompose.R
import com.example.mooddiarycompose.core.ViewModelFactory
import com.example.mooddiarycompose.core.destinations.Destination
import com.example.mooddiarycompose.core.destinations.HomeBottomBarDestination
import com.example.mooddiarycompose.core.destinations.HomeDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onFloatingButtonClick: () -> Unit) {
    val navController = rememberNavController()
    val viewModel: MainScreenViewModel =
        viewModel(factory = ViewModelFactory { MainScreenViewModel(navController = navController) })

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFloatingButtonClick,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(R.string.add_new_mood_diary)
                )
            }

        },
        bottomBar = { BottomNavBar(navController = navController) }
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navController = navController,
            startDestination = HomeBottomBarDestination.overview
        ) {
            with (HomeBottomBarDestination(navController)) {
                buildGraph()
            }
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavController,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        for (navBarItem in bottomBarItems()) {
            NavigationBarItem(
                label = { Text(navBarItem.label) },
                icon = {
                    Icon(
                        painter = painterResource(id = navBarItem.iconId),
                        contentDescription = navBarItem.label
                    )
                },
                selected = currentBackStackEntry?.destination?.route == navBarItem.path,
                onClick = { navController.navigate(navBarItem.path) }
            )
        }
    }

}

private fun bottomBarItems(): List<BottomNavBarItems> {
    return listOf(BottomNavBarItems.OVERVIEW, BottomNavBarItems.STATISTIC)
}

private enum class BottomNavBarItems(
    val path: String,
    val label: String,
    @DrawableRes val iconId: Int
) {
    OVERVIEW(
        path = HomeBottomBarDestination.overview,
        label = "Overview",
        iconId = R.drawable.ic_home
    ),
    STATISTIC(
        path = HomeBottomBarDestination.statistic,
        label = "Statistic",
        iconId = R.drawable.ic_stat
    )
}