package com.example.cal

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class HistoryRepository(private val historyDao: HistoryDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<History>> = historyDao.getAll()

    suspend fun insert(history: History) {
        historyDao.insertAll(history)
    }
}