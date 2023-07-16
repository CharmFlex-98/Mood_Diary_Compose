package com.example.mooddiarycompose.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun OverViewScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Introduction(Modifier.weight(0.3f))
        MoodDiaryListView(Modifier.weight(0.7f))
    }
}


@Composable
fun Introduction(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "this is for introduction", textAlign = TextAlign.Center)
    }
}


@Composable
fun MoodDiaryListView(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text("This is for mood diary list view")
    }
}