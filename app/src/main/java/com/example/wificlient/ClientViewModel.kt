package com.example.wificlient

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ClientViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WifiRepository
    val allUsers: LiveData<List<User>>

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = WifiRepository(appDao)
        allUsers = repository.allUsers
    }

    fun addUser(userId: String, name: String) {
        viewModelScope.launch {
            val newUser = User(
                userId = userId,
                name = name,
                status = "Active", // Default new users to "Active"
                dataUsage = 0L
            )
            repository.insertUser(newUser)
        }
    }
}