package com.example.mooddiarycompose.home.domain

import java.time.LocalDate

data class MoodDiary(
    val title: String,
    val date: LocalDate,
    val content: String,
    val mood: Double,
)