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

    // 1. Get the ViewModel (where your fake data lives)
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // 2. Find all the TextViews from your XML layout
        val tvTodayIncome: TextView = view.findViewById(R.id.tvTodayIncome)
        val tvMonthIncome: TextView = view.findViewById(R.id.tvMonthIncome)
        val tvActiveUsers: TextView = view.findViewById(R.id.tvActiveUsers)
        val tvTotalUsers: TextView = view.findViewById(R.id.tvTotalUsers)
        val tvMostActive: TextView = view.findViewById(R.id.tvMostActive)
        val tvOverdueInvoices: TextView = view.findViewById(R.id.tvOverdueInvoices)


        // 3. Set up the "Observers" to get the fake data
        // This is the code that was missing

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

        dashboardViewModel.mostActiveClient.observe(viewLifecycleOwner, Observer { clientName ->
            tvMostActive.text = clientName
        })

        dashboardViewModel.overdueInvoices.observe(viewLifecycleOwner, Observer { count ->
            tvOverdueInvoices.text = count
        })

        return view
    }
}