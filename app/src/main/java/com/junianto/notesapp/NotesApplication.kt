package com.junianto.notesapp

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}