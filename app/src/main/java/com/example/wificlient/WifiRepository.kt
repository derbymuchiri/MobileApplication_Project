package com.example.wificlient.data.repository

import androidx.lifecycle.LiveData
import com.example.wificlient.data.db.AppDao
import com.example.wificlient.data.model.Voucher

class WifiRepository(private val appDao: AppDao) {

    // --- Voucher ---
    val allVouchers: LiveData<List<Voucher>> = appDao.getAllVouchers()

    suspend fun insertVoucher(voucher: Voucher) {
        appDao.insertVoucher(voucher)
    }

    suspend fun deleteUsedVouchers() {
        appDao.deleteUsedVouchers()
    }

    // --- Dashboard Stats ---

    suspend fun getActiveUsersCount(): Int {
        return appDao.getActiveUsersCount()
    }

    suspend fun getTotalUsersCount(): Int {
        return appDao.getTotalUsersCount()
    }

    suspend fun getTodayIncome(todayStart: Long): Double {
        return appDao.getTodayIncome(todayStart) ?: 0.0 // Return 0.0 if null
    }

    suspend fun getMonthIncome(monthStart: Long): Double {
        return appDao.getMonthIncome(monthStart) ?: 0.0 // Return 0.0 if null
    }
}