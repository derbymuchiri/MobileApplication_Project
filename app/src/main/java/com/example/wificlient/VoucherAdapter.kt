package com.example.wificlient

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wificlient.data.model.Voucher

class VoucherAdapter : RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>() {

    private var voucherList = emptyList<Voucher>()

    class VoucherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tvVoucherId)
        val tvType: TextView = itemView.findViewById(R.id.tvVoucherType)
        val tvPlan: TextView = itemView.findViewById(R.id.tvVoucherPlan)
        val tvCode: TextView = itemView.findViewById(R.id.tvVoucherCode)
        val tvStatus: TextView = itemView.findViewById(R.id.tvVoucherStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_voucher, parent, false)
        return VoucherViewHolder(view)
    }

    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        val currentVoucher = voucherList[position]

        holder.tvId.text = currentVoucher.id.toString()
        holder.tvType.text = currentVoucher.type
        holder.tvPlan.text = currentVoucher.planName
        holder.tvCode.text = currentVoucher.code
        holder.tvStatus.text = currentVoucher.status

        if (currentVoucher.status == "Used") {
            holder.tvStatus.setTextColor(Color.parseColor("#D32F2F")) // Red
        } else {
            holder.tvStatus.setTextColor(Color.parseColor("#388E3C")) // Green
        }
    }

    override fun getItemCount() = voucherList.size

    fun setData(vouchers: List<Voucher>) {
        this.voucherList = vouchers
        notifyDataSetChanged()
    }
}