package com.junianto.notesapp.data.source.remote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kotlinx.coroutines.delay
import com.junianto.notesapp.data.Note
import com.junianto.notesapp.data.Result
import com.junianto.notesapp.data.Result.Error
import com.junianto.notesapp.data.Result.Success
import com.junianto.notesapp.data.source.NotesDataSource
import com.junianto.notesapp.utils.getCurrentDate

object NotesRemoteDataSource: NotesDataSource {

    private const val SERVICE_LATENCY_IN_MILLIS = 2000L

    private var NOTES_SERVICE_DATA = LinkedHashMap<String, Note>(2)

    init {
        addNote("Got a Girlfriend Dude", "Actually i like someone who close to me but i can't do anything.", getCurrentDate())
    }

    private val observableNotes = MutableLiveData<Result<List<Note>>>()

    override fun observeNotes(): LiveData<Result<List<Note>>> {
        return observableNotes
    }

    override fun observeNotes(noteId: String): LiveData<Result<Note>> {
        return observableNotes.map { notes ->
            when (notes) {
                is Result.Loading -> Result.Loading
                is Error -> Error(notes.exception)
                is Success -> {
                    val note = notes.data.firstOrNull() { it.id == noteId }
                        ?: return@map Error(Exception("Not found"))
                    Success(note)
                }
            }
        }
    }

    override suspend fun getNotes(): Result<List<Note>> {
        // Simulate network by delaying the execution
        val notes = NOTES_SERVICE_DATA.values.toList()
        delay(SERVICE_LATENCY_IN_MILLIS)
        return Success(notes)
    }

    override suspend fun refreshNotes() {
        observableNotes.value = getNotes()
    }

    override suspend fun getNote(noteId: String): Result<Note> {
        // Simulate network by delaying the execution
        delay(SERVICE_LATENCY_IN_MILLIS)
        NOTES_SERVICE_DATA[noteId]?.let { return Success(it) }
        return Error(Exception("Notes not found"))
    }

    override suspend fun refreshNote(noteId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveNote(note: Note) {
        NOTES_SERVICE_DATA[note.id] = note
    }

    override suspend fun deleteAllNotes() {
        NOTES_SERVICE_DATA.clear()
    }

    override suspend fun deleteNote(noteId: String) {
        NOTES_SERVICE_DATA.remove(noteId)
    }

    private fun addNote(title: String, description: String, create: String) {
        val newNote = Note(title, description, create)
        NOTES_SERVICE_DATA[newNote.id] = newNote
    }
}