package com.junianto.notesapp.db.reminderDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "reminder")
data class Reminder(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "date")
    var date:Long,

    @ColumnInfo(name = "time")
    var time:Long,

    @ColumnInfo(name = "isFinishied")
    var isFinishied:Int = 0,

    @ColumnInfo(name = "isShow")
    var isShow:Int = 0
): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}