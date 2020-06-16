package com.junianto.notesapp.data.source.repository

import androidx.lifecycle.LiveData
import com.junianto.notesapp.data.Result
import com.junianto.notesapp.data.Result.Success
import com.junianto.notesapp.data.Note
import com.junianto.notesapp.data.source.NotesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DefaultNotesRepository (private val notesRemoteDataSource: NotesDataSource, private val notesLocalDataSource: NotesDataSource, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): NotesRepository {
    override suspend fun getNotes(forceUpdate: Boolean): Result<List<Note>> {
        if (forceUpdate) {
            try {
                updateNotesFromRemoteDataSource()
            } catch (ex: Exception) {
                return Result.Error(ex)
            }
        }
        return notesLocalDataSource.getNotes()
    }

    private suspend fun updateNotesFromRemoteDataSource() {
        val remoteNotes = notesRemoteDataSource.getNotes()

        if (remoteNotes is Success) {
            // Propery Sync on my way
            notesLocalDataSource.deleteAllNotes()
            remoteNotes.data.forEach { note ->
                notesLocalDataSource.saveNote(note)
            }
        } else if ( remoteNotes is Result.Error ) {
            throw remoteNotes.exception
        }
    }

    private suspend fun updateNoteFromRemoteDataSource(noteId: String) {
        val remoteNote = notesRemoteDataSource.getNote(noteId)

        if (remoteNote is Success) {
            notesLocalDataSource.saveNote(remoteNote.data)
        }
    }

    override fun observeNotes(): LiveData<Result<List<Note>>> {
        return notesLocalDataSource.observeNotes()
    }

    override fun observeNotes(noteId: String): LiveData<Result<Note>> {
        return notesLocalDataSource.observeNotes(noteId)
    }

    override suspend fun refreshNotes() {
        updateNotesFromRemoteDataSource()
    }

    override suspend fun getNote(noteId: String, forceUpdate: Boolean): Result<Note> {
        if (forceUpdate) {
            updateNoteFromRemoteDataSource(noteId)
        }
        return notesLocalDataSource.getNote(noteId)
    }

    override suspend fun refreshNote(noteId: String) {
        updateNoteFromRemoteDataSource(noteId)
    }

    override suspend fun saveNote(note: Note) {
        coroutineScope {
            launch { notesRemoteDataSource.saveNote(note) }
            launch { notesLocalDataSource.saveNote(note) }
        }
    }

    override suspend fun deleteAllNotes() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch { notesRemoteDataSource.deleteAllNotes() }
                launch { notesLocalDataSource.deleteAllNotes() }
            }
        }
    }

    override suspend fun deleteNote(noteId: String) {
        coroutineScope {
            launch { notesRemoteDataSource.deleteNote(noteId) }
            launch { notesLocalDataSource.deleteNote(noteId) }
        }
    }

    private suspend fun getNoteWithId(id: String): Result<Note> {
        return notesLocalDataSource.getNote(id)
    }
}