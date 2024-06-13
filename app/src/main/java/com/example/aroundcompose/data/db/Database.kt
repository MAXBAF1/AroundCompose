package com.example.aroundcompose.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.aroundcompose.data.db.entities.Account
import com.example.aroundcompose.data.db.entities.General
import com.example.aroundcompose.data.db.entities.Settings
import com.example.aroundcompose.data.db.entities.Skills

@Database(
    entities = [
        General::class,
        Account::class,
        Skills::class,
        Settings::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AroundDatabase : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var Instance: AroundDatabase? = null
        fun getDatabase(context: Context): AroundDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                databaseBuilder(context, AroundDatabase::class.java, "around_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}