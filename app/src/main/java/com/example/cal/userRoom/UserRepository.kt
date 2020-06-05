package com.example.cal.userRoom

import androidx.lifecycle.LiveData

class UserRepository(private val usersDao: UsersDao) {

    val allUsers: LiveData<List<User>> = usersDao.getAllUsers()

    fun insert(user: User) {
        usersDao.insert(user)
    }

    fun deleteAll(){
        usersDao.deleteAll()
    }
}