package com.example.flickrbyretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class RVadapter (val activity: MainActivity, private val names: ArrayList<Photo>) : RecyclerView.Adapter<RVadapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = names[position]


        holder.itemView.apply {
            title.text = photo.title
            val link="https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
            Glide.with(activity)
                .load(link)
                .into(imageView2)
            clItr.setOnClickListener{activity.openImg(link)}

        }
    }
    override fun getItemCount() = names.size
}