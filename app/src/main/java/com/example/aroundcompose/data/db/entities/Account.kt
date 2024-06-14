package com.example.aroundcompose.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class Account(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val level: Int,
    val teamId: Int,
    val geolocation: String,
)
