package com.example.wificlient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class VoucherFragment : Fragment() {

    private val voucherViewModel: VoucherViewModel by viewModels()
    private lateinit var adapter: VoucherAdapter

    private lateinit var btnAddVoucher: Button
    private lateinit var btnPrint: Button
    private lateinit var recyclerVoucher: RecyclerView
    private lateinit var addVoucherLayout: ScrollView
    private lateinit var typeGroup: RadioGroup
    private lateinit var spinnerRouters: Spinner
    private lateinit var spinnerPlans: Spinner
    private lateinit var etNumber: EditText
    private lateinit var etLength: EditText
    private lateinit var btnGenerate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_voucher, container, false)

        adapter = VoucherAdapter()
        recyclerVoucher = view.findViewById(R.id.recyclerVoucher)
        recyclerVoucher.adapter = adapter
        recyclerVoucher.layoutManager = LinearLayoutManager(requireContext())

        voucherViewModel.allVouchers.observe(viewLifecycleOwner, Observer { vouchers ->
            vouchers?.let { adapter.setData(it) }
        })

        // Find all other views
        btnAddVoucher = view.findViewById(R.id.btnAddVoucher)
        btnPrint = view.findViewById(R.id.btnPrint)
        addVoucherLayout = view.findViewById(R.id.addVoucherLayout)
        typeGroup = view.findViewById(R.id.typeGroup)
        spinnerPlans = view.findViewById(R.id.plansSpinner)
        etNumber = view.findViewById(R.id.etNumber)
        etLength = view.findViewById(R.id.etLength)
        btnGenerate = view.findViewById(R.id.btnGenerate)

        val dummyRouters = arrayOf("Freetown", "Central", "West End")
        val routerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dummyRouters)
        spinnerRouters.adapter = routerAdapter

        val dummyPlans = arrayOf("24Hrs Unlimited", "1Week Unlimited", "1Month 10GB")
        val planAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dummyPlans)
        spinnerPlans.adapter = planAdapter

        btnAddVoucher.setOnClickListener {
            if (addVoucherLayout.visibility == View.GONE) {
                addVoucherLayout.visibility = View.VISIBLE
                btnAddVoucher.text = "Cancel"
            } else {
                addVoucherLayout.visibility = View.GONE
                btnAddVoucher.text = "Add Vouchers"
            }
        }

        btnGenerate.setOnClickListener {
            generateVouchers()
        }

        btnPrint.setOnClickListener {
            Toast.makeText(context, "Print function not implemented.", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun generateVouchers() {
        val selectedType = view?.findViewById<RadioButton>(typeGroup.checkedRadioButtonId)?.text.toString()
        val selectedRouter = spinnerRouters.selectedItem.toString()
        val selectedPlan = spinnerPlans.selectedItem.toString()
        val numberText = etNumber.text.toString()
        val lengthText = etLength.text.toString()

        if (numberText.isEmpty() || lengthText.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val number = numberText.toInt()
        val length = lengthText.toInt()

        for (i in 1..number) {
            val code = (1000..9999).random().toString() + ('A'..'Z').random()
            voucherViewModel.addVoucher(code, selectedType, selectedRouter, selectedPlan)
        }

        Toast.makeText(context, "Generated $number vouchers!", Toast.LENGTH_SHORT).show()
        addVoucherLayout.visibility = View.GONE
        btnAddVoucher.text = "Add Vouchers"
    }
}
