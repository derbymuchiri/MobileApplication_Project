package com.example.wificlient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer


class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val tvTodayIncome: TextView = view.findViewById(R.id.tvTodayIncome)
        val tvMonthIncome: TextView = view.findViewById(R.id.tvMonthIncome)
        val tvActiveUsers: TextView = view.findViewById(R.id.tvActiveUsers)
        val tvTotalUsers: TextView = view.findViewById(R.id.tvTotalUsers)

        dashboardViewModel.todayIncome.observe(viewLifecycleOwner, Observer { income ->
            tvTodayIncome.text = income
        })

        dashboardViewModel.monthIncome.observe(viewLifecycleOwner, Observer { income ->
            tvMonthIncome.text = income
        })

        dashboardViewModel.activeUsers.observe(viewLifecycleOwner, Observer { count ->
            tvActiveUsers.text = count
        })

        dashboardViewModel.totalUsers.observe(viewLifecycleOwner, Observer { count ->
            tvTotalUsers.text = count
        })

        return view
    }
}