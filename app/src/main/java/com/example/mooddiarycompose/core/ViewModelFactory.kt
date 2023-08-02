package com.example.mooddiarycompose.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory(private val viewModelBuilder: () -> ViewModel): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = viewModelBuilder()
        if (viewModel.javaClass.isAssignableFrom(modelClass)) {
            return (viewModel as T)
        }

        throw IllegalArgumentException("No such viewModel")
    }
}