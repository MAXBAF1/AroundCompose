package com.example.aroundcompose.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "general",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["account_id"]
        ), ForeignKey(
            entity = Skills::class,
            parentColumns = ["id"],
            childColumns = ["skill_id"]
        ), ForeignKey(
            entity = Settings::class,
            parentColumns = ["id"],
            childColumns = ["settings_id"]
        )
    ]
)
data class General(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val geolocation: String,
    @ColumnInfo(name = "account_id") val accountId: Int,
    @ColumnInfo(name = "skill_id") val skillId: Int,
    @ColumnInfo(name = "settings_id") val settingsId: Int,
)
