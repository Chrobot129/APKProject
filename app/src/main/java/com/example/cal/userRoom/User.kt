package com.example.cal.userRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(var userName: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}