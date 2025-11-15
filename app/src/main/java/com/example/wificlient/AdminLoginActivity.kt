package com.example.wificlientmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wificlient.R

class AdminLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        val etAdminID = findViewById<EditText>(R.id.etAdminID)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val adminID = etAdminID.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Temporary hardcoded check for now (backend will replace this later)
            if (adminID == "admin" && password == "1234") {
                val intent = Intent(this, AdminDashboardActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid Admin ID or Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
