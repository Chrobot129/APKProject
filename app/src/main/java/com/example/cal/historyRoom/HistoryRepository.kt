package com.example.cal.historyRoom

import androidx.lifecycle.LiveData

class HistoryRepository(private val recordDao: RecordDao) {

    val allUsers: LiveData<List<Record>> = recordDao.getHistory()

    fun insert(record: Record) {
        recordDao.insert(record)
    }

}