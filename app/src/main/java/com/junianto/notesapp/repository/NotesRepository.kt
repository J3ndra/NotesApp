package com.junianto.notesapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.junianto.notesapp.db.Notes
import com.junianto.notesapp.db.NotesDAO
import com.junianto.notesapp.db.NotesDB
import kotlinx.coroutines.CoroutineScope

class NotesRepository (application: Application, scope: CoroutineScope) {
    private var notesDAO: NotesDAO
    var listNotes: LiveData<List<Notes>>
        private set

    init {
        val database: NotesDB = NotesDB.getInstance( application, scope )
        notesDAO = database.notesDAO()
        listNotes = notesDAO.getAllNotes()
    }

    suspend fun insert(notes: Notes) {
        notesDAO.insert(notes)
    }

    suspend fun update(notes: Notes) {
        notesDAO.update(notes)
    }

    suspend fun delete(notes: Notes) {
        notesDAO.delete(notes)
    }
}