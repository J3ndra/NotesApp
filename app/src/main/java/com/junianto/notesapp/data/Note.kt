package com.junianto.notesapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "notes")
data class Note @JvmOverloads constructor(
    @ColumnInfo(name = "noteTitle")
    var title: String = "",

    @ColumnInfo(name = "noteDesc")
    var description: String = "",

    @ColumnInfo(name = "noteTime")
    var timeStamp: String = "",

    @PrimaryKey
    @ColumnInfo(name = "noteId")
    var id: String = UUID.randomUUID().toString()
) {

    val titleForList: String
        get() = if (title.isNotEmpty()) title else description

    val isEmpty
        get() = title.isEmpty() || description.isEmpty()
}