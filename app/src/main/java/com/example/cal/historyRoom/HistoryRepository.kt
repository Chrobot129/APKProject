package com.example.cal.historyRoom

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle

class HistoryRepository(private val recordDao: RecordDao, userName: String) {

    val allHistory: LiveData<List<Record>> = recordDao.getHistory(userName)

    suspend fun insert(record: Record) {
        recordDao.insert(record)
    }

 //   fun getHistory(userName: String): List<Record> {
 //       return recordDao.getHistoryByPerson(userName)
 //   }
}