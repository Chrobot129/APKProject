package com.example.cal.historyRoom

import androidx.lifecycle.LiveData

class HistoryRepository(private val recordDao: RecordDao) {

    val allRecords: LiveData<List<Record>> = recordDao.getHistory()

    fun insert(record: Record) {
        recordDao.insert(record)
    }

    fun deleteAll() {
        recordDao.deleteAll()
    }

}