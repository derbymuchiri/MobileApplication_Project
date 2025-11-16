package com.example.wificlient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var transactionList = emptyList<Transaction>()

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.tvTransactionId)
        val date: TextView = itemView.findViewById(R.id.tvTransactionDate)
        val amount: TextView = itemView.findViewById(R.id.tvTransactionAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val currentTransaction = transactionList[position]

        holder.id.text = "Transaction #${currentTransaction.id}"
        holder.amount.text = "Ksh ${"%.2f".format(currentTransaction.amount)}"
        holder.date.text = formatTimestamp(currentTransaction.timestamp)
    }

    override fun getItemCount() = transactionList.size

    fun setData(transactions: List<Transaction>) {
        this.transactionList = transactions
        notifyDataSetChanged()
    }

    private fun formatTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}