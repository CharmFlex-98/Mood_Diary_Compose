package com.example.mooddiarycompose.core.destinations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

class SettingDestination(
    navController: NavController,
    override val routeName: String = this.root,
    override val defaultDestination: String = this.root
) : Destination(navController) {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun NavGraphBuilder.buildGraph() {
        navigation(route = root, startDestination = main) {
            composable(route = main) {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        Text(text = "This is setting page!", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }

    companion object {
        const val root = "setting"
        const val main = "$root/main"
    }


}