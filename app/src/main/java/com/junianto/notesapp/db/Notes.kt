package com.junianto.notesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "noteList")
data class Notes(
    @ColumnInfo(name = "noteTitle")
    var title: String = "",

    @ColumnInfo(name = "noteDesc")
    var description: String = ""
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}