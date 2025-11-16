package com.example.wificlient

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wificlient.R

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        // Find all the buttons
        val btnDashboard = findViewById<Button>(R.id.btnDashboard)
        val btnVouchers = findViewById<Button>(R.id.btnVouchers)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // --- 1. FIND THE NEW BUTTONS (This was missing) ---
        val btnClients = findViewById<Button>(R.id.btnClients)
        val btnTransactions = findViewById<Button>(R.id.btnTransactions)


        // Load Dashboard fragment first
        replaceFragment(DashboardFragment())

        // --- 2. SET UP ALL LISTENERS ---
        btnDashboard.setOnClickListener {
            replaceFragment(DashboardFragment())
        }

        // --- 3. ADD LISTENERS FOR YOUR NEW BUTTONS (This was missing) ---
        btnClients.setOnClickListener {
            replaceFragment(ClientsFragment()) // <-- Load new fragment
        }

        btnTransactions.setOnClickListener {
            replaceFragment(TransactionsFragment()) // <-- Load new fragment
        }

        btnVouchers.setOnClickListener {
            replaceFragment(VoucherFragment())
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}