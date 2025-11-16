package com.example.wificlient

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vouchers")
data class Voucher(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String,
    val type: String, // "Hotspot" or "PPPOE"
    val router: String,
    val planName: String,
    val status: String // "Used" or "Not Used"
)