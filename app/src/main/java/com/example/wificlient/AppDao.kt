package com.example.wificlient

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AppDao {

    // --- Voucher Queries ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVoucher(voucher: Voucher)

    @Query("SELECT * FROM vouchers ORDER BY id DESC")
    fun getAllVouchers(): LiveData<List<Voucher>>

    @Query("DELETE FROM vouchers WHERE status = 'Used'")
    suspend fun deleteUsedVouchers()

    // --- Dashboard Stats Queries ---

    @Query("SELECT COUNT(*) FROM users WHERE status = 'Active'")
    suspend fun getActiveUsersCount(): Int

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getTotalUsersCount(): Int

    // We pass in the start-of-day timestamp
    @Query("SELECT SUM(amount) FROM transactions WHERE timestamp >= :todayStart")
    suspend fun getTodayIncome(todayStart: Long): Double? // Nullable in case there are no sales

    // We pass in the start-of-month timestamp
    @Query("SELECT SUM(amount) FROM transactions WHERE timestamp >= :monthStart")
    suspend fun getMonthIncome(monthStart: Long): Double? // Nullable
}