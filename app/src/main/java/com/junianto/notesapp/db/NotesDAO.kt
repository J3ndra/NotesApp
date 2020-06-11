package com.junianto.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: Notes)

    @Update
    suspend fun update(vararg notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Query("SELECT * FROM NoteList")
    fun getAllNotes(): LiveData<List<Notes>>
}