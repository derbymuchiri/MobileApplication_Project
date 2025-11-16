package com.example.wificlient

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val userId: String, // A unique ID for the user
    val name: String,
    val status: String // "Active" or "Inactive"
)