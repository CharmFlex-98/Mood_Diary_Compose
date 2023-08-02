package com.example.mooddiarycompose.util

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDatePicker(datePickerState: DatePickerState, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    DatePickerDialog(onDismissRequest = onDismiss, confirmButton = {
        // More action on viewModel
        TextButton(onClick = onConfirm) {
            if (datePickerState.selectedDateMillis != null) Text(text = "Confirm")
        }
    }) {
        DatePicker(state = datePickerState)
    }
}