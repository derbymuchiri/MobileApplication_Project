package com.example.wificlient

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// 1. Add User::class and Transaction::class
@Database(entities = [Voucher::class, User::class, Transaction::class, Invoice::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "wifi_client_database"
                )
                    .fallbackToDestructiveMigration() // This will clear the old database
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}