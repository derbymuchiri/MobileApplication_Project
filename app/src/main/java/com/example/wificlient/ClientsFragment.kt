package com.example.wificlient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wificlient.ui.clients.ClientAdapter

class ClientsFragment : Fragment() {

    private val clientViewModel: ClientViewModel by viewModels()
    private lateinit var adapter: ClientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_clients, container, false)

        // Setup RecyclerView
        adapter = ClientAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerClients)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe LiveData
        clientViewModel.allUsers.observe(viewLifecycleOwner, Observer { users ->
            users?.let { adapter.setData(it) }
        })

        // Setup Add Client Form
        val etClientName = view.findViewById<EditText>(R.id.etClientName)
        val etClientId = view.findViewById<EditText>(R.id.etClientId)
        val btnAddClient = view.findViewById<Button>(R.id.btnAddClient)

        btnAddClient.setOnClickListener {
            val name = etClientName.text.toString()
            val id = etClientId.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                clientViewModel.addUser(id, name)
                Toast.makeText(context, "Client Added!", Toast.LENGTH_SHORT).show()
                etClientName.text.clear()
                etClientId.text.clear()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}