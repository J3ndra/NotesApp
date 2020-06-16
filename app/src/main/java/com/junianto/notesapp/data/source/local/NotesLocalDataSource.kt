package com.junianto.notesapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.junianto.notesapp.data.Note
import com.junianto.notesapp.data.Result
import com.junianto.notesapp.data.Result.Error
import com.junianto.notesapp.data.Result.Success
import com.junianto.notesapp.data.source.NotesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class NotesLocalDataSource internal constructor( private val notesDAO: NotesDAO, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): NotesDataSource {

    override fun observeNotes(): LiveData<Result<List<Note>>> {
        return notesDAO.observeNotes().map {
            Success(it)
        }
    }

    override fun observeNotes(noteId: String): LiveData<Result<Note>> {
        return notesDAO.observeNoteById(noteId).map{
            Success(it)
        }
    }

    override suspend fun getNotes(): Result<List<Note>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(notesDAO.getNotes())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun refreshNotes() {
        TODO("Not yet implemented")
    }

    override suspend fun getNote(noteId: String): Result<Note> = withContext(ioDispatcher){
        try {
            val note = notesDAO.getNoteById(noteId)
            if (note != null) {
                return@withContext Success(note)
            } else {
                return@withContext Error(Exception("Note not found!"))
            }
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun refreshNote(noteId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveNote(note: Note) = withContext(ioDispatcher) {
        notesDAO.insertNote(note)
    }

    override suspend fun deleteAllNotes() {
        notesDAO.deleteNotes()
    }

    override suspend fun deleteNote(noteId: String) {
        notesDAO.deleteNoteById(noteId)
    }
}