package com.example.cal.historyRoom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecordDao {
    @Insert
    fun insert(record: Record)

    @Update
    fun update(record: Record)

    @Delete
    fun delete(record: Record)

    @Query("SELECT * FROM history_table")
    fun getHistory(): LiveData<List<Record>>

    @Query("DELETE FROM history_table")
    fun deleteAll()
}