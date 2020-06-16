package com.junianto.notesapp.ui.home

import androidx.lifecycle.*
import com.junianto.notesapp.db.notes.Notes
import com.junianto.notesapp.repository.notesRepository.NotesRepository
import kotlinx.coroutines.*

class HomeViewModel(private val notesRepository: NotesRepository): ViewModel() {
    fun insert(notes: Notes) = viewModelScope.launch {
        notesRepository.insertNotes(notes)
    }

    fun update(notes: Notes) = viewModelScope.launch {
        notesRepository.updateNotes(notes)
    }

    fun delete(notes: Notes) = viewModelScope.launch {
        notesRepository.deleteNotes(notes)
    }

    fun getAllNotes() = notesRepository.getAllNotes()
}