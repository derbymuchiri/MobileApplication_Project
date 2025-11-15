package com.example.wificlient.ui.vouchers

import android.app.Application
import com.example.wificlient.data.model.AppDatabase
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wificlient.data.model.Voucher
import com.example.wificlient.data.repository.WifiRepository
import kotlinx.coroutines.launch

class VoucherViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WifiRepository
    val allVouchers: LiveData<List<Voucher>>

    init {
        val appDao = AppDatabase.getDatabase(application).appDao()
        repository = WifiRepository(appDao)
        allVouchers = repository.allVouchers
    }

    fun addVoucher(code: String, type: String, router: String, plan: String) {
        viewModelScope.launch {
            val newVoucher = Voucher(
                code = code,
                type = type,
                router = router,
                planName = plan,
                status = "Not Used"
            )
            repository.insertVoucher(newVoucher)
        }
    }

    fun deleteUsed() {
        viewModelScope.launch {
            repository.deleteUsedVouchers()
        }
    }
}