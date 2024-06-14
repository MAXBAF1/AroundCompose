package com.example.aroundcompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aroundcompose.data.db.entities.Account
import com.example.aroundcompose.data.db.entities.Settings
import com.example.aroundcompose.data.db.entities.Skills

@Database(
    entities = [
        Account::class,
        Skills::class,
        Settings::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AroundDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}