package com.junianto.notesapp.db.reminderDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.junianto.notesapp.db.notesDB.NotesDAO

@Database( entities = [Reminder::class], version = 1, exportSchema = false)
abstract class ReminderDB : RoomDatabase() {
    abstract fun applicationDAO(): NotesDAO

    companion object{

        @Volatile
        var INSTANCE: ReminderDB?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        private fun buildDatabase(context: Context) = Room.databaseBuilder( context.applicationContext, ReminderDB::class.java, "RemindersDB.db" ).build()
    }

}