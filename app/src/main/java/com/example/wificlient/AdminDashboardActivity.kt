package com.example.wificlient

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val btnDashboard = findViewById<Button>(R.id.btnDashboard)
        val btnVouchers = findViewById<Button>(R.id.btnVouchers)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Load Dashboard fragment first
        replaceFragment(DashboardFragment())

        btnDashboard.setOnClickListener {
            replaceFragment(DashboardFragment())
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