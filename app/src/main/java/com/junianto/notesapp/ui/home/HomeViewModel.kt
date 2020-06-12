package com.junianto.notesapp.ui.home

import androidx.lifecycle.*
import com.junianto.notesapp.db.Notes
import com.junianto.notesapp.db.NotesDB
import com.junianto.notesapp.repository.NotesRepository
import kotlinx.coroutines.*

class HomeViewModel(private val notesRepository: NotesRepository): ViewModel() {
    fun insert(notes: Notes) = viewModelScope.launch {
        notesRepository.insert(notes)
    }

    fun update(notes: Notes) = viewModelScope.launch {
        notesRepository.update(notes)
    }

    fun delete(notes: Notes) = viewModelScope.launch {
        notesRepository.delete(notes)
    }

    fun getAllNotes() = notesRepository.getAllNotes()
}