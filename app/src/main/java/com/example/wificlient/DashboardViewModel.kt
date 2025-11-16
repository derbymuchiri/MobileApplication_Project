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

    private val _todayIncome = MutableLiveData("Ksh 0.00")
    val todayIncome: LiveData<String> = _todayIncome

    private val _monthIncome = MutableLiveData("Ksh 0.00")
    val monthIncome: LiveData<String> = _monthIncome

    private val _activeUsers = MutableLiveData("0")
    val activeUsers: LiveData<String> = _activeUsers

    private val _totalUsers = MutableLiveData("0")
    val totalUsers: LiveData<String> = _totalUsers

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = WifiRepository(appDao)

        loadStats()
    }

    fun loadStats() {
        viewModelScope.launch {
            // 1. Calculate start of today
            val today = Calendar.getInstance()
            today.set(Calendar.HOUR_OF_DAY, 0)
            today.set(Calendar.MINUTE, 0)
            today.set(Calendar.SECOND, 0)
            val todayStart = today.timeInMillis

            // 2. Calculate start of this month
            val month = Calendar.getInstance()
            month.set(Calendar.DAY_OF_MONTH, 1)
            month.set(Calendar.HOUR_OF_DAY, 0)
            month.set(Calendar.MINUTE, 0)
            month.set(Calendar.SECOND, 0)
            val monthStart = month.timeInMillis

            // 3. Call the repository for each stat
            val todayIncomeValue = repository.getTodayIncome(todayStart)
            val monthIncomeValue = repository.getMonthIncome(monthStart)
            val activeUsersValue = repository.getActiveUsersCount()
            val totalUsersValue = repository.getTotalUsersCount()

            // 4. Update the LiveData (this will update the UI)
            _todayIncome.postValue("Ksh ${"%.2f".format(todayIncomeValue)}")
            _monthIncome.postValue("Ksh ${"%.2f".format(monthIncomeValue)}")
            _activeUsers.postValue(activeUsersValue.toString())
            _totalUsers.postValue(totalUsersValue.toString())
        }
    }
}