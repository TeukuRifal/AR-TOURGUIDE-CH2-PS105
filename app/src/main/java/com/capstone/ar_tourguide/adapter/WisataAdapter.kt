package com.capstone.ar_tourguide.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.model.Place

class WisataAdapter(private val listWisata: List<Place>): RecyclerView.Adapter<WisataAdapter.ListViewHolder>() {
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
        return listWisata.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val place: Place = listWisata[position]

        // Load image from URL using Glide
        Glide.with(holder.itemView.context)
            .load(place.photo)  // Use the correct property for the image URL
            .placeholder(R.drawable.monas)
            .into(holder.imgPhoto)


        holder.name.text = place.name
        holder.description.text = place.description

        holder.itemView.setOnClickListener {
            // Panggil pendengar klik jika ada
            onItemClickListener?.invoke(position)
        }
    }




}