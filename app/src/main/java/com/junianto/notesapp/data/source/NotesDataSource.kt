package com.junianto.notesapp.data.source

import androidx.lifecycle.LiveData
import com.junianto.notesapp.data.Note
import com.junianto.notesapp.data.Result

interface NotesDataSource {

    fun observeNotes(): LiveData<Result<List<Note>>>

    suspend fun getNotes(): Result<List<Note>>

    suspend fun refreshNotes()

    fun observeNotes(noteId: String): LiveData<Result<Note>>

    suspend fun getNote(noteId: String): Result<Note>

    suspend fun refreshNote(noteId: String)

    suspend fun saveNote(note: Note)

    suspend fun deleteAllNotes()

    suspend fun deleteNote(noteId: String)
}