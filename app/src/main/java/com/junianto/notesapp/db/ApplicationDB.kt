package com.junianto.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.junianto.notesapp.db.notes.Notes
import com.junianto.notesapp.db.notes.NotesDAO
import com.junianto.notesapp.db.reminder.Reminder
import com.junianto.notesapp.db.reminder.ReminderDAO

@Database( entities = [ Notes::class, Reminder::class ],version = 1, exportSchema = false )
abstract class ApplicationDB : RoomDatabase() {
    abstract val notesDAO: NotesDAO
    abstract val reminderDAO: ReminderDAO

    companion object {
        fun getInstance(context: Context): ApplicationDB = Room.databaseBuilder(
            context.applicationContext,
            ApplicationDB::class.java, "applications.db"
        ).fallbackToDestructiveMigration().build()
    }
}