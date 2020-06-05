package com.example.cal.userRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository

    val allUsers: LiveData<List<User>>

    init{
        val usersDao = UserRoomDatabase.getDatabase(application, viewModelScope).usersDao()
        repository = UserRepository(usersDao)
        allUsers = repository.allUsers
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    fun getUser(name: String): User? {
        return  repository.getUser(name)
        // return allUsers.value?.find { user -> name.equals(user.userName) }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

}