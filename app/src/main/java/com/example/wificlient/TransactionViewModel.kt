package com.example.wificlient

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WifiRepository
    val allTransactions: LiveData<List<Transaction>>

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = WifiRepository(appDao)
        allTransactions = repository.allTransactions
    }
}

