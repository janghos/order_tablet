package com.example.order_tablet.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.order_tablet.databinding.RvListItemBinding
import com.example.order_tablet.model.RvListContent
import java.net.URL
import java.util.concurrent.Executors

class RvListAdapter(val items : MutableList<RvListContent>, private val context : Context) :  RecyclerView.Adapter<RvListAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: RvListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: RvListContent) {
            binding.title.text = item.text
            Glide.with(itemView.context)
                .load(item.imageUrl) // item.imageUrl은 이미지의 URL입니다.
                .override(800, 800)
                .into(binding.iv)
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