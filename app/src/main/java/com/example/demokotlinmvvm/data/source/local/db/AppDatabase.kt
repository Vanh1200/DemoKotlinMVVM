package com.example.demokotlinmvvm.data.source.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.demokotlinmvvm.data.model.User
import com.example.demokotlinmvvm.data.source.local.dao.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        fun getInstance(
            context: Context
        ) =
            INSTANCE ?: synchronized(AppDatabase::class.java) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "user-database"
                )
                    .allowMainThreadQueries()
                    .build()
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
