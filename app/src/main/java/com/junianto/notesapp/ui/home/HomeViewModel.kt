package com.junianto.notesapp.ui.home

import androidx.lifecycle.*
import com.junianto.notesapp.data.Note
import com.junianto.notesapp.data.source.repository.NotesRepository
import kotlinx.coroutines.*

class HomeViewModel(private val notesRepository: NotesRepository): ViewModel() {
    fun insert(note: Note) = viewModelScope.launch {
        notesRepository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        notesRepository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        notesRepository.delete(note)
    }

    fun getAllNotes() = notesRepository.getAllNotes()
}