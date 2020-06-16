package com.junianto.notesapp.db.reminder

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReminderDAO {
    @Query("SELECT * FROM reminder ORDER BY reminderId DESC")
    fun getReminder():LiveData<List<Reminder>>

    @Query("SELECT * FROM reminder ORDER BY reminderId DESC")
    fun getReminderList():List<Reminder>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Query("DELETE FROM reminder")
    suspend fun clearAllReminder()

    @Query("SELECT * FROM reminder ORDER BY reminderId DESC LIMIT 1")
    suspend fun getLatestReminder():Reminder?
}