package com.example.cal.historyRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Record::class), version = 5, exportSchema = false)
public abstract class HistoryRoomDatabase: RoomDatabase() {

    abstract fun recordDao(): RecordDao

    companion object {

        @Volatile
        private var INSTANCE: HistoryRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): HistoryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryRoomDatabase::class.java,
                    "history_table"
                )
                    .addCallback(HistoryDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class HistoryDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.recordDao())
                    }
                }
            }
        }

        fun populateDatabase(recordDao: RecordDao) {
           //  recordDao.deleteAll()
          //   var user = Record("Adrian", "20", "wczoraj")
          //   recordDao.insert(user)
           //  user = Record("Adrian", "21", "dzisiaj")
           //  recordDao.insert(user)
        }
    }
}

