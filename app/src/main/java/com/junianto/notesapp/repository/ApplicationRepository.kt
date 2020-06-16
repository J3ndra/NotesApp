package com.junianto.notesapp.repository

import android.content.Context
import com.junianto.notesapp.db.notes.Notes
import com.junianto.notesapp.db.notes.NotesDAO
import com.junianto.notesapp.db.reminder.Reminder
import com.junianto.notesapp.db.reminder.ReminderDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApplicationRepository ( private val notesDAO: NotesDAO, private val reminderDAO: ReminderDAO, private val context: Context) {
    fun getAllNotes() = notesDAO.getNotes()

    suspend fun delete(notes: Notes) {
        withContext(Dispatchers.IO) {
            notesDAO.deleteNotes(notes)
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            notesDAO.clearAllNotes()
        }
    }

    suspend fun update(notes: Notes) {
        withContext(Dispatchers.IO) {
            notesDAO.updateNotes(notes)
        }
    }

    suspend fun insert(notes: Notes) {
        withContext(Dispatchers.IO) {
            notesDAO.insertNotes(notes)
        }
    }

    suspend fun getNoteById(notesId: String): Notes? {
        return withContext(Dispatchers.IO) {
            notesDAO.getNoteById(notesId)
        }
    }

    fun getAllReminder() = reminderDAO.getReminder()

    suspend fun getReminderList():List<Reminder>? {
        return withContext(Dispatchers.IO) {
            reminderDAO.getReminderList()
        }
    }

    suspend fun getReminderById(reminderId: String): Reminder? {
        return withContext(Dispatchers.IO) {
            reminderDAO.getLatestReminder()
        }
    }

    suspend fun insert(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDAO.insertReminder(reminder)
        }
    }

    suspend fun update(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDAO.updateReminder(reminder)
        }
    }

    suspend fun delete(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDAO.deleteReminder(reminder)
        }
    }
}