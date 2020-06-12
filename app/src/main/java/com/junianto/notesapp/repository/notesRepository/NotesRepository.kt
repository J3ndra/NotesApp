package com.junianto.notesapp.repository.notesRepository

import com.junianto.notesapp.db.notesDB.Notes
import com.junianto.notesapp.db.notesDB.NotesDB

class NotesRepository (val database: NotesDB) {
    suspend fun insertNotes(notes: Notes) = database.applicationDAO().insertNotes(notes)

    suspend fun updateNotes(notes: Notes) = database.applicationDAO().updateNotes(notes)

    suspend fun deleteNotes(notes: Notes) = database.applicationDAO().deleteNotes(notes)

    fun getAllNotes() = database.applicationDAO().getAllNotes()
}