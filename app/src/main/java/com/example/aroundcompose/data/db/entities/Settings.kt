package com.example.aroundcompose.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val toggleNotification: Boolean,
    val theme: String,
    val language: String
)
