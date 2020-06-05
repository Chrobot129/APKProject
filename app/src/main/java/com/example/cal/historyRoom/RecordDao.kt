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

    @Query("SELECT * FROM history_table WHERE time LIKE :userName")
    fun getHistoryByPerson(userName: String): List<Record>

  //  @Query("SELECT * FROM history_table")
    @Query("SELECT * FROM history_table WHERE time LIKE :userName")
    fun getHistory(userName: String): LiveData<List<Record>>

    @Query("DELETE FROM history_table")
    fun deleteAll()
}