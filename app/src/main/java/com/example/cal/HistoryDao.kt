package com.example.cal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_table")
    fun getAll(): LiveData<List<History>>
//
//    @Query("SELECT * FROM history_table WHERE uid IN (:historyIds)")
//    fun loadAllByIds(historyIds: IntArray): LiveData<List<History>>

    @Insert
    fun insertAll(vararg entries: History)

    @Delete
    fun delete(history: History)
}