package com.example.mooddiarycompose.home.ui.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mooddiarycompose.ui.theme.MoodDiaryComposeTheme

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
        LazyColumn {
            items(
                count = 5,
                key = { it },
            ) {
                MoodDiaryListItem()
            }
        }
    }
}

@Composable
fun OverViewPreview() {
    MoodDiaryComposeTheme() {
        OverViewScreen()
    }
}