package com.example.wificlient

import androidx.lifecycle.LiveData

class WifiRepository(private val appDao: AppDao) {

    // --- For Vouchers ---
    val allVouchers: LiveData<List<Voucher>> = appDao.getAllVouchers()
    suspend fun insertVoucher(voucher: Voucher) {
        appDao.insertVoucher(voucher)
    }
    suspend fun deleteUsedVouchers() {
        appDao.deleteUsedVouchers()
    }

    // --- For Dashboard ---
    suspend fun getActiveUsersCount(): Int {
        return appDao.getActiveUsersCount()
    }
    suspend fun getTotalUsersCount(): Int {
        return appDao.getTotalUsersCount()
    }
    suspend fun getTodayIncome(todayStart: Long): Double {
        return appDao.getTodayIncome(todayStart) ?: 0.0
    }
    suspend fun getMonthIncome(monthStart: Long): Double {
        return appDao.getMonthIncome(monthStart) ?: 0.0
    }
    suspend fun getOverdueInvoiceCount(): Int {
        return appDao.getOverdueInvoiceCount()
    }
    suspend fun getMostActiveClientName(): String? {
        return appDao.getMostActiveClientName()
    }

    val allUsers: LiveData<List<User>> = appDao.getAllUsers()
    suspend fun insertUser(user: User) {
        appDao.insertUser(user)
    }

    // For TransactionsFragment
    val allTransactions: LiveData<List<Transaction>> = appDao.getAllTransactions()
}