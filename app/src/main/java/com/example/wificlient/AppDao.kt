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

    @Query("SELECT SUM(amount) FROM transactions WHERE timestamp >= :todayStart")
    suspend fun getTodayIncome(todayStart: Long): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE timestamp >= :monthStart")
    suspend fun getMonthIncome(monthStart: Long): Double?

    @Query("SELECT COUNT(*) FROM invoices WHERE status = 'Overdue'")
    suspend fun getOverdueInvoiceCount(): Int

    @Query("SELECT name FROM users ORDER BY dataUsage DESC LIMIT 1")
    suspend fun getMostActiveClientName(): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User) // <-- This was missing

    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getAllUsers(): LiveData<List<User>> // <-- This was missing

    // For TransactionsFragment
    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): LiveData<List<Transaction>> // <-- This was missing
}