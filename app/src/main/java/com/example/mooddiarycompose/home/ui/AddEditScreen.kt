package com.example.mooddiarycompose.home.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mooddiarycompose.R
import com.example.mooddiarycompose.ui.theme.MoodDiaryComposeTheme
import com.example.mooddiarycompose.util.BasicDatePicker
import com.example.mooddiarycompose.util.BasicTopAppbar
import com.example.mooddiarycompose.util.milliToLocalDate
import com.example.mooddiarycompose.util.toFormattedString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(onBack: () -> Unit) {
    val viewModel: AddEditMoodDiaryViewModel = viewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    var showDatePickerDialog by remember { mutableStateOf(false) }

    BackHandler {
        onBack()
    }

    Scaffold(topBar = { BasicTopAppbar(iconId = R.drawable.ic_back) { onBack() } }) { paddingVal ->
        Column(
            modifier = Modifier
                .padding(paddingVal)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            GeneralTextField(label = "Title",
                value = viewState.title,
                onValueChange = { viewModel.editTitle(it) })
            GeneralTextField(
                label = "Date", value = viewState.date.toFormattedString(), readOnly = true
            ) {
                IconButton(onClick = { showDatePickerDialog = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit_calendar),
                        contentDescription = "Edit Date"
                    )
                }
            }


            GeneralTextField(
                label = "Content",
                value = viewState.content,
                onValueChange = { viewModel.editContent(it) },
            )
            GeneralTextField(label = "Rating",
                value = viewState.moodRating.toString(),
                onValueChange = { viewModel.editMoodRating(it.toDouble()) })

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(onClick = {}) {
                    Text(text = "Submit")
                }
            }
        }

        if (showDatePickerDialog) {
            BasicDatePicker(datePickerState = datePickerState, onDismiss = { showDatePickerDialog = false}) {
                val selectedDate = datePickerState.selectedDateMillis?.milliToLocalDate()
                selectedDate?.let { viewModel.setDate(selectedDate.toLocalDate()) }
                showDatePickerDialog = false
            }
        }
    }
}

@Preview
@Composable
fun AddEditScreenPreview() {
    MoodDiaryComposeTheme {
        AddEditScreen() {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        readOnly = readOnly,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}