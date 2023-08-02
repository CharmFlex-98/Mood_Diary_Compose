package com.example.mooddiarycompose.util

import androidx.annotation.DrawableRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTopAppbar(title: String = "", @DrawableRes iconId: Int, onIconClick: () -> Unit) {
    TopAppBar(title = { Text(text = title) }, navigationIcon = {
        IconButton(onClick = onIconClick) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Top app bar navigation icon"
            )
        }
    })
}