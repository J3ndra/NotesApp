package com.junianto.notesapp.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.junianto.notesapp.db.Notes
import com.junianto.notesapp.repository.NotesRepository
import kotlinx.coroutines.*

class HomeViewModel( application: Application) : AndroidViewModel(application) {
    private var notesRepository: NotesRepository
    var listNotes: LiveData<List<Notes>>
        private set

    init {
        notesRepository = NotesRepository(application, viewModelScope)
        listNotes = notesRepository.listNotes
    }

    fun insert(notes: Notes) = viewModelScope.launch {
        notesRepository.insert(notes)
    }

    fun update(notes: Notes) = viewModelScope.launch {
        notesRepository.update(notes)
    }

    fun delete(notes: Notes) = viewModelScope.launch {
        notesRepository.delete(notes)
    }
}