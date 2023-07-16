package com.example.mooddiarycompose.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SnackBar(snackbarHostState: SnackbarHostState, modifier: Modifier = Modifier, content: String) {
    SnackbarHost(snackbarHostState, modifier.fillMaxSize()) {
        Snackbar {
            Text(text = content)
        }
    }
}