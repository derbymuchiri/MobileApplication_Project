package com.example.wificlient

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Calendar

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WifiRepository

    private val _todayIncome = MutableLiveData("Ksh 207,784")
    val todayIncome: LiveData<String> = _todayIncome
    private val _monthIncome = MutableLiveData("Ksh 1,956,890")
    val monthIncome: LiveData<String> = _monthIncome
    private val _activeUsers = MutableLiveData("126,000")
    val activeUsers: LiveData<String> = _activeUsers
    private val _totalUsers = MutableLiveData("798")
    val totalUsers: LiveData<String> = _totalUsers

    private val _mostActiveClient = MutableLiveData("Jeniffer Odhiambo")
    val mostActiveClient: LiveData<String> = _mostActiveClient

    private val _overdueInvoices = MutableLiveData("75")
    val overdueInvoices: LiveData<String> = _overdueInvoices


    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = WifiRepository(appDao)
        loadStats()
    }

    fun loadStats() {
        viewModelScope.launch {
            val today = Calendar.getInstance()
            today.set(Calendar.HOUR_OF_DAY, 0)
            today.set(Calendar.MINUTE, 0)
            today.set(Calendar.SECOND, 0)
            val todayStart = today.timeInMillis

            val month = Calendar.getInstance()
            month.set(Calendar.DAY_OF_MONTH, 1)
            month.set(Calendar.HOUR_OF_DAY, 0)
            month.set(Calendar.MINUTE, 0)
            month.set(Calendar.SECOND, 0)
            val monthStart = month.timeInMillis

            val todayIncomeValue = repository.getTodayIncome(todayStart)
            val monthIncomeValue = repository.getMonthIncome(monthStart)
            val activeUsersValue = repository.getActiveUsersCount()
            val totalUsersValue = repository.getTotalUsersCount()

            // --- ADD THESE NEW REPOSITORY CALLS ---
            val overdueCount = repository.getOverdueInvoiceCount()
            val topClient = repository.getMostActiveClientName()

            _todayIncome.postValue("Ksh ${"%.2f".format(todayIncomeValue)}")
            _monthIncome.postValue("Ksh ${"%.2f".format(monthIncomeValue)}")
            _activeUsers.postValue(activeUsersValue.toString())
            _totalUsers.postValue(totalUsersValue.toString())
            _overdueInvoices.postValue(overdueCount.toString())
            _mostActiveClient.postValue(topClient ?: "N/A")
        }
    }
}