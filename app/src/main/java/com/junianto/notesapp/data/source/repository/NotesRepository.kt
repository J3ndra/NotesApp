package com.junianto.notesapp.data.source.repository

import androidx.lifecycle.LiveData
import com.junianto.notesapp.data.Note
import com.junianto.notesapp.data.Result

interface NotesRepository {
    fun observeNotes(): LiveData<Result<List<Note>>>

    suspend fun getNotes(forceUpdate: Boolean = false): Result<List<Note>>

    suspend fun refreshNotes()

    fun observeNotes(noteId: String): LiveData<Result<Note>>

    suspend fun getNote(noteId: String, forceUpdate: Boolean): Result<Note>

    suspend fun refreshNote(noteId: String)

    suspend fun saveNote(note: Note)

    suspend fun deleteAllNotes()

    suspend fun deleteNote(noteId: String)
}