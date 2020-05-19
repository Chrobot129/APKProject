package com.example.cal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HistoryRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<History>>

    init {
        val wordsDao = AppDatabase.getDatabase(application).historyDao()
        repository = HistoryRepository(wordsDao)
        allWords = repository.allWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(history: History) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(history)
    }
}