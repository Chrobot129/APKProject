package com.example.cal.historyRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): AndroidViewModel(application) {

    private val repository: HistoryRepository

    val history: LiveData<List<Record>>

    init{
        val recordDao = HistoryRoomDatabase.getDatabase(application, viewModelScope).recordDao()
        repository = HistoryRepository(recordDao)
        history = repository.allUsers
    }

    fun insert(record: Record) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(record)
    }

}