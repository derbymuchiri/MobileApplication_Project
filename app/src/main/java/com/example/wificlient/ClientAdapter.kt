package com.example.wificlient.ui.clients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wificlient.R
import com.example.wificlient.User

class ClientAdapter : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    private var userList = emptyList<User>()

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvClientName)
        val id: TextView = itemView.findViewById(R.id.tvClientId)
        val status: TextView = itemView.findViewById(R.id.tvClientStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_client, parent, false)
        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.name.text = currentUser.name
        holder.id.text = "ID: ${currentUser.userId}"
        holder.status.text = "Status: ${currentUser.status}"
    }

    override fun getItemCount() = userList.size

    fun setData(users: List<User>) {
        this.userList = users
        notifyDataSetChanged()
    }
}