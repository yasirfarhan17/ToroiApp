package com.example.toroi.ui.details_view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.network_domain.network.model.DataItem
import com.example.toroi.R
import com.example.toroi.databinding.IndiviewItemsBinding

class DetailsAdapter(private val callBack: DetailsAdapterCallBack) :
    RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private val items = ArrayList<DataItem>()

    fun submitList(homeList: ArrayList<DataItem>) {
        items.clear()
        notifyItemRangeRemoved(0, items.size)
        items.addAll(homeList)
        notifyItemRangeInserted(0, items.size)
    }


    inner class DetailsViewHolder(private val binding: IndiviewItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: DataItem) {
            with(binding) {
                tvEmail.text = item.email
                tvName.text = item.firstName + "  " + item.lastName
                tvPhone.text = item?.phoneNumber ?: "----------"
                profileImage.load(item.avatar) {
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.add)
                }
                cvRoot.setOnClickListener {
                    callBack.onItemClick(adapterPosition, item)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding =
            IndiviewItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

interface DetailsAdapterCallBack {
    fun onItemClick(position: Int, item: DataItem)
}