package com.example.aroundcompose.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills")
data class SkillsEntity(
    @PrimaryKey
    val id: Int,
    val maxLevel: Int,
    val skillName: String,
    val skillDesc: String,
    val imageUrl: String
)
