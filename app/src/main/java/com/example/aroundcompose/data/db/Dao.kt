package com.example.aroundcompose.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aroundcompose.data.db.entities.AccountEntity
import com.example.aroundcompose.data.db.entities.SettingsEntity
import com.example.aroundcompose.data.db.entities.SkillsEntity

@Dao
interface Dao {
    @Insert(entity = AccountEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewAccountData(account: AccountEntity)

    @Insert(entity = SkillsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewSkillData(skill: SkillsEntity)

    @Insert(entity = SettingsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewSettingsData(settings: SettingsEntity)

    @Query("SELECT * FROM account")
    suspend fun getAllAccountData(): AccountEntity

    @Query("SELECT * FROM skills")
    suspend fun getAllSkillsData(): List<SkillsEntity>

    @Query("SELECT * FROM settings")
    suspend fun getAllSettingsData(): SettingsEntity
}