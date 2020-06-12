package com.junianto.notesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.junianto.notesapp.repository.notesRepository.NotesRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val notesRepository: NotesRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel( notesRepository ) as T
    }
}