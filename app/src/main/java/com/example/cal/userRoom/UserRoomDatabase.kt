package com.example.cal.userRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(User::class), version = 2, exportSchema = false)
public abstract class UserRoomDatabase: RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {

        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UserRoomDatabase {
        return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "users_table"
                )
                    .addCallback(UserDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class UserDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.usersDao())
                    }
                }
            }
        }

        fun populateDatabase(usersDao: UsersDao) {
            //usersDao.deleteAll()

           // var user = User("Adrian", "Mężczyzna")
           // usersDao.insert(user)
           // user = User("Krystian", "Mężczyzna")
           // usersDao.insert(user)
        }
     }
}
