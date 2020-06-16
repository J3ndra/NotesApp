package com.junianto.notesapp.db.notes

import androidx.lifecycle.LiveData
import androidx.room.*
import com.junianto.notesapp.db.notes.Notes

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Update()
    suspend fun updateNotes(notes: Notes)

    @Query("SELECT * FROM notes WHERE noteId = :key")
    suspend fun getNotes(key: Int): Notes?

    @Query("SELECT * FROM notes WHERE note_identifier = :notesid")
    suspend fun getNoteById(notesid:String):Notes?

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("DELETE FROM notes")
    suspend fun clearAllNotes()

    @Query("SELECT * FROM notes ORDER BY noteTime DESC")
    fun getNotes(): LiveData<List<Notes>>
}