package com.example.wificlient

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val userId: String,
    val name: String,
    val status: String, // "Active" or "Inactive"
    val dataUsage: Long = 0L // <-- ADD THIS NEW FIELD
)