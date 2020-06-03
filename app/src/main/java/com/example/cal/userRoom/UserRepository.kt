package com.example.cal.userRoom

import androidx.lifecycle.LiveData

class UserRepository(private val usersDao: UsersDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<User>> = usersDao.getAllUsers()

    suspend fun insert(user: User) {
        usersDao.insert(user)
    }
}