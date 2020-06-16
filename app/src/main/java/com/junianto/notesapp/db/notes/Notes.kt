package com.junianto.notesapp.db.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    val id: Int,

    @ColumnInfo(name = "note_identifier")
    val noteIdentifier: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "noteTitle")
    var title: String,

    @ColumnInfo(name = "noteDesc")
    var description: String,

    @ColumnInfo(name = "noteTime")
    var timeStamp: String
)