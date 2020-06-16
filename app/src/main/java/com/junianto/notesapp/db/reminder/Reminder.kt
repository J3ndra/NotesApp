package com.junianto.notesapp.db.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reminderId")
    val id: Int,

    @ColumnInfo(name = "reminder_identifier")
    val reminderIdentifier: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "reminderTitle")
    var title: String,

    @ColumnInfo(name = "reminderDesc")
    var description: String,

    @ColumnInfo(name = "reminderTime")
    var reminderTime: String,

    @ColumnInfo(name = "reminderDate")
    var reminderDate: String,

    @ColumnInfo(name = "active")
    var isActive: Boolean,

    @ColumnInfo(name = "repeatValue")
    var repeatValue: Int,

    @ColumnInfo(name = "repeatUnit")
    var repeatUnit: String
)