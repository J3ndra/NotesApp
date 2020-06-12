package com.junianto.notesapp.db.notesDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.junianto.notesapp.db.notesDB.Notes

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Update()
    suspend fun updateNotes(notes: Notes)

    @Query("SELECT * FROM notes WHERE id = :key")
    suspend fun getNotes(key: Int): Notes?

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY noteTime DESC")
    fun getAllNotes(): LiveData<List<Notes>>
}