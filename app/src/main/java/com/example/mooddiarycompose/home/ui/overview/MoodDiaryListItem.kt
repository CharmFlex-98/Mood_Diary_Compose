package com.example.mooddiarycompose.home.ui.overview

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import com.example.mooddiarycompose.ui.theme.MoodDiaryComposeTheme

@Composable
fun MoodDiaryListItem() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .padding(8.dp), border = BorderStroke(1.dp, Color.Black),
        shape = RectangleShape
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), text = "Date")
            Text(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), text = "Title")
        }
    }
}

@Preview
@Composable
fun Preview(
    uiMode: Int = Configuration.UI_MODE_NIGHT_YES,
    name: String = "DefaultPreviewDark"
) {
    MoodDiaryComposeTheme {
        MoodDiaryListItem()
    }
}