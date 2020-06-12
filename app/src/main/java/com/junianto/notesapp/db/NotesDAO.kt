package com.junianto.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: Notes)

    @Update()
    suspend fun update(notes: Notes)

    @Query("SELECT * FROM notes WHERE id = :key")
    suspend fun get(key: Int): Notes?

    @Delete
    suspend fun delete(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY noteTime DESC")
    fun getAllNotes(): LiveData<List<Notes>>
}