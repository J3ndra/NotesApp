package com.junianto.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.locks.Lock

@Database( entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDB : RoomDatabase() {
    abstract fun notesDAO(): NotesDAO

    companion object{

        @Volatile private var INSTANCE: NotesDB? = null

        //if parameter before ?: symbol is not null, return it
        //otherwise return the right expression
        fun getInstance(context: Context, scope: CoroutineScope) = INSTANCE ?: synchronized(this){
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext, NotesDB::class.java, "notesdb")
                .fallbackToDestructiveMigration()
                .addCallback(NoteDatabaseCallback(scope))
                .build()
                .also { INSTANCE = it }
        }
    }

    private class  NoteDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    populateDatabase(it.notesDAO())
                }
            }
        }

        suspend fun populateDatabase(noteDao: NotesDAO){
            noteDao.insert(Notes("Sample 1", "This is a sample note"))
            noteDao.insert(Notes("Sample 2", "This is a sample note"))
            noteDao.insert(Notes("Sample 3", "This is a sample note"))
        }
    }
}