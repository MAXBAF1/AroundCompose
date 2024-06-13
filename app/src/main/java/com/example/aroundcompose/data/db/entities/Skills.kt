package com.example.aroundcompose.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills")
data class Skills(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val skillName: String,
    val skillDesc: String,
    val imageId: Int
)
