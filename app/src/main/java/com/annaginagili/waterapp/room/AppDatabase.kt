package com.annaginagili.waterapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.annaginagili.waterapp.model.Challenge

@Database(entities = [Challenge::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): ChallengeDao
}