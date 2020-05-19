package com.example.cal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history_table")
class History(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "energia") val energia: Int,
    @ColumnInfo(name = "data") val data: String?
) {
    val history: CharSequence?
}