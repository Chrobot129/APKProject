package com.example.cal.historyRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class Record(var bmi: String, var time: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}