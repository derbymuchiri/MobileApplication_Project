package com.example.wificlient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TransactionsFragment : Fragment() {

    private val transactionViewModel: TransactionViewModel by viewModels()
    private lateinit var adapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transactions, container, false)

        // Setup RecyclerView
        adapter = TransactionAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerTransactions)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe LiveData
        transactionViewModel.allTransactions.observe(viewLifecycleOwner, Observer { transactions ->
            transactions?.let { adapter.setData(it) }
        })

        return view
    }
}