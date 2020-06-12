package com.junianto.notesapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.junianto.notesapp.db.Notes
import com.junianto.notesapp.db.NotesDAO
import com.junianto.notesapp.db.NotesDB
import kotlinx.coroutines.CoroutineScope

class NotesRepository (val database: NotesDB) {
    suspend fun insert(notes: Notes) = database.notesDAO().insert(notes)

    suspend fun update(notes: Notes) = database.notesDAO().update(notes)

    suspend fun delete(notes: Notes) = database.notesDAO().delete(notes)

    fun getAllNotes() = database.notesDAO().getAllNotes()
}