package com.junianto.notesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Notes(
    @ColumnInfo(name = "noteTitle")
    var title: String,

    @ColumnInfo(name = "noteDesc")
    var description: String,

    @ColumnInfo(name = "noteTime")
    var timeStamp: String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}