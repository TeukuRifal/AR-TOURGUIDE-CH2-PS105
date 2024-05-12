package com.capstone.ar_tourguide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.model.cafe

class CafeAdapter(private val listCafe: List<cafe>) : RecyclerView.Adapter<CafeAdapter.ListViewHolder>() {
    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_wisata)
        val name: TextView = itemView.findViewById(R.id.tv_item_name)
        val description: TextView = itemView.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCafe.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val cafe: cafe = listCafe[position]

        // Load image from URL using Glide
        Glide.with(holder.itemView.context)
            .load(cafe.image)  // Use the correct property for the image URL
            .placeholder(R.drawable.cafe_placeholder) // Change to your default placeholder
            .into(holder.imgPhoto)

        holder.name.text = cafe.name
        holder.description.text = cafe.address

        holder.itemView.setOnClickListener {
            // Panggil pendengar klik jika ada
            onItemClickListener?.invoke(position)
        }
    }
}
