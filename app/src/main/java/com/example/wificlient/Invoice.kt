package com.example.wificlient

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoices")
data class Invoice(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String,
    val amount: Double,
    val status: String // "Paid" or "Overdue"
)