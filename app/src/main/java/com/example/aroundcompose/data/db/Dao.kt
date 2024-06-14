package com.example.aroundcompose.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aroundcompose.data.db.entities.Account
import com.example.aroundcompose.data.db.entities.Settings
import com.example.aroundcompose.data.db.entities.Skills
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(entity = Account::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewAccountData(account: Account)

    @Insert(entity = Skills::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewSkillData(skill: Skills)

    @Insert(entity = Settings::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewSettingsData(settings: Settings)

    @Query("SELECT * FROM account")
    fun getAllAccountData(): Flow<Account>

    @Query("SELECT * FROM skills")
    fun getAllSkillsData(): Flow<Skills>

    @Query("SELECT * FROM settings")
    fun getAllSettingsData(): Flow<Settings>
}