package com.junianto.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDB : RoomDatabase() {
    abstract fun notesDAO(): NotesDAO

    companion object{

        @Volatile
        var INSTANCE: NotesDB ?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder( context.applicationContext, NotesDB::class.java, "NotesDB.db" ).build()
    }

}