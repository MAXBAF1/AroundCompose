package com.example.aroundcompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aroundcompose.data.db.entities.AccountEntity
import com.example.aroundcompose.data.db.entities.SettingsEntity
import com.example.aroundcompose.data.db.entities.SkillsEntity

@Database(
    entities = [
        AccountEntity::class,
        SkillsEntity::class,
        SettingsEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AroundDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}