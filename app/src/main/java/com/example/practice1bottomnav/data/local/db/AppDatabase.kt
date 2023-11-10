package com.example.practice1bottomnav.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practice1bottomnav.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}