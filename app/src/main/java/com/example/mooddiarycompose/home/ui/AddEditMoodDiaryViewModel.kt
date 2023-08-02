package com.example.mooddiarycompose.home.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class AddEditScreenState(
    val errorMessage: String? = null,
    val title: String = "",
    val date: LocalDate? = null,
    val content: String = "",
    val moodRating: Double = 4.toDouble()
) {
    fun minMoodRating() = 0
    fun maxMoodRating() = 5
}


class AddEditMoodDiaryViewModel: ViewModel() {
    private var _viewState = MutableStateFlow(AddEditScreenState())

    val viewState = _viewState.asStateFlow()

    fun editTitle(title: String) {
        _viewState.value = _viewState.value.copy(
            title = title
        )
    }

    fun editContent(content: String) {
        _viewState.value = _viewState.value.copy(
            content = content
        )
    }

    fun editMoodRating(rating: Double) {
        _viewState.value = _viewState.value.copy(
            moodRating = rating
        )
    }

    fun setDate(date: LocalDate) {
        _viewState.value = _viewState.value.copy(
            date = date
        )
    }
}