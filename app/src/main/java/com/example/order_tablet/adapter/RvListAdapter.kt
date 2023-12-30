package com.example.order_tablet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.order_tablet.databinding.RvListItemBinding
import com.example.order_tablet.model.RvListContent

class RvListAdapter(val items : MutableList<RvListContent>, private val context : Context) :  RecyclerView.Adapter<RvListAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: RvListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: RvListContent) {
            binding.title.text = item.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvListAdapter.ViewHolder {
        val view = RvListItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvListAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}