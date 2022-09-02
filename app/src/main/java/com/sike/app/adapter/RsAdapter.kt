package com.sike.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sike.app.R
import com.sike.app.RumahSakit

class RsAdapter(private val context: Context, private val rumahsakit: List<RumahSakit>, val listener: (RumahSakit) -> Unit)
    : RecyclerView.Adapter<RsAdapter.RsViewHolder>(){


    class RsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imgRs = view.findViewById<ImageView>(R.id.img_item_photo)
        val nameRs = view.findViewById<TextView>(R.id.tv_rs_name)
        val locRs = view.findViewById<TextView>(R.id.tv_loc_rs)
        val descRs = view.findViewById<TextView>(R.id.tv_rs_description)

        fun bindView(rumahSakit: RumahSakit, listener: (RumahSakit) -> Unit){
            imgRs.setImageResource(rumahSakit.imgRs)
            nameRs.text = rumahSakit.nameRs
            locRs.text = rumahSakit.locRs
            descRs.text = rumahSakit.descRs
            itemView.setOnClickListener {
                listener(rumahSakit)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RsViewHolder {
        return RsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_rs, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RsViewHolder, position: Int) {
        holder.bindView(rumahsakit[position], listener)
    }

    override fun getItemCount(): Int = rumahsakit.size
}
