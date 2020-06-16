package com.junianto.notesapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.junianto.notesapp.data.Note

@Dao
interface NotesDAO {
    @Query("SELECT * FROM Notes")
    fun observeNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM Notes WHERE noteId = :noteId")
    fun observeNoteById(noteId: String): LiveData<Note>

    @Query("SELECT * FROM Notes")
    suspend fun getNotes(): List<Note>

    @Query("SELECT * FROM Notes WHERE noteId = :noteId")
    suspend fun getNoteById(noteId: String): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note): Int

    @Query("DELETE FROM Notes WHERE noteId = :noteId")
    suspend fun deleteNoteById(noteId: String): Int

    @Query("DELETE FROM Notes")
    suspend fun deleteNotes()
}