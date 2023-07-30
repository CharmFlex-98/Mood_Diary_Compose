package com.example.mooddiarycompose.core

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mooddiarycompose.R
import com.example.mooddiarycompose.core.destinations.Destination
import com.example.mooddiarycompose.core.destinations.HomeDestination
import com.example.mooddiarycompose.core.destinations.SettingDestination
import com.example.mooddiarycompose.core.destinations.ShowBottomNavArg
import com.example.mooddiarycompose.ui.theme.MoodDiaryComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Root(destinations: List<Destination>) {
    MoodDiaryComposeTheme {
        // A surface container using the 'background' color from the theme
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val currentBackStackEntry = navController.currentBackStackEntryAsState()
        val showBottomNavBar = currentBackStackEntry.value?.arguments?.getBoolean(
            ShowBottomNavArg.key
        ) ?: false
        val coroutineScope = rememberCoroutineScope()

        ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
            DrawerContent(navController, onChooseFinish = {
                coroutineScope.launch {
                    drawerState.close()
                }
            }
            )
        }) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                        visible = showBottomNavBar,
                    )
                }
            ) {
                NavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    navController = navController,
                    startDestination = HomeDestination.root
                ) {
                    destinations.forEach { destination ->
                        with(destination) {
                            buildGraph()
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavController,
    visible: Boolean,
) {
    if (visible) {
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
                    selected = navController.currentBackStackEntry?.destination?.route == navBarItem.path,
                    onClick = { navController.navigate(navBarItem.path) }
                )
            }
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
        path = HomeDestination.overview,
        label = "Overview",
        iconId = R.drawable.ic_home
    ),
    STATISTIC(
        path = HomeDestination.statistic,
        label = "Statistic",
        iconId = R.drawable.ic_stat
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(navController: NavController, onChooseFinish: () -> Unit) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    ModalDrawerSheet {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = "This is drawer. Will put some image in the future")
        }
        for (item in drawerItems()) {
            DrawerItem(text = item.label, iconId = item.iconId, isSelected = { currentBackStackEntry?.destination?.parent?.route == item.parentPath}) {
                navController.navigate(item.path)
                onChooseFinish()
            }
        }
    }
}

private fun drawerItems(): List<DrawerNavItem> {
    return listOf(DrawerNavItem.HOME, DrawerNavItem.SETTING)
}

private enum class DrawerNavItem(val path: String, val parentPath: String, val label: String, @DrawableRes val iconId: Int) {
    HOME(path = HomeDestination.root, parentPath = HomeDestination.root, label = "Home", iconId = R.drawable.ic_home),
    SETTING(path = SettingDestination.root, parentPath = SettingDestination.root, label = "Setting", iconId = R.drawable.ic_setting)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(
    text: String,
    @DrawableRes iconId: Int,
    isSelected: () -> Boolean,
    onClick: () -> Unit,
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(5.dp),
        label = { Text(text = text) },
        icon = { Icon(painter = painterResource(id = iconId), contentDescription = "") },
        selected = isSelected(),
        onClick = onClick
    )
}