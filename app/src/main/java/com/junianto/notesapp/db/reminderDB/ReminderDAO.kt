package com.junianto.notesapp.db.reminderDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReminderDAO {
    @Query("SELECT * FROM reminder WHERE isFinishied == 0")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder): Long

    @Query("SELECT * FROM reminder where isFinishied == 0")
    fun getAllReminder(): LiveData<List<Reminder>>

    @Query("UPDATE reminder SET isFinishied = 1 WHERE id = :uid")
    fun finishReminder(uid: Long)

    @Query("UPDATE reminder SET isShow = :isShow WHERE id LIKE :id")
    fun isShowUpdate(id: Long, isShow: Int)

    @Query("SELECT * FROM reminder WHERE id LIKE :id")
    fun getReminder(id: Long): Reminder

    @Query("DELETE FROM reminder WHERE id = :uid")
    fun deleteReminder(uid: Long)
}