package com.example.cal.userRoom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsersDao {
    @Insert
    fun insert(user: com.example.cal.userRoom.User)

    @Update
    fun update(user: com.example.cal.userRoom.User)

    @Delete
    fun delete(user: com.example.cal.userRoom.User)

    @Query("SELECT * FROM users_table")
    fun getAllUsers(): LiveData<List<com.example.cal.userRoom.User>>

    @Query("DELETE FROM users_table")
    fun deleteAll()
}