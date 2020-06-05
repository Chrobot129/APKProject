package com.example.cal.historyRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryViewModel(application: Application, userName: String): AndroidViewModel(application) {

    class MyViewModelFactory(private val mApplication: Application, var params: String) :
        NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass == ViewModel1::class.java) {
                ViewModel1(mApplication, mParams[0] as String) as T
            } else if (modelClass == ViewModel2::class.java) {
                ViewModel2(mApplication, mParams[0] as Int) as T
            } else if (modelClass == ViewModel3::class.java) {
                ViewModel3(
                    mApplication,
                    mParams[0] as Int,
                    mParams[1] as String
                ) as T
            } else {
                super.create(modelClass)
            }
        }

        init {
            mParams = params
        }
    }

  //  protected inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
  //      object : ViewModelProvider.Factory {
   //         override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
   //     }


    private val repository: HistoryRepository
    //private val repository: HistoryRepository by lazy { HistoryRepository(recordDao, userName) }

    val userName: String = ""
    val history: LiveData<List<Record>>

    init{
        val recordDao = HistoryRoomDatabase.getDatabase(application, viewModelScope).recordDao()
        repository = HistoryRepository(recordDao, userName)
        history = repository.allHistory
    }

  //  fun getHistory(userName: String): List<Record>? {
  //      return repository.getHistory(userName)
     //   return history.value?.filterNot { record -> userName.equals(record.time) }
  //  }

 //   fun setUser(userName: String){
  //      this.userName = userName
   // }

    fun insert(record: Record) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(record)
    }


}