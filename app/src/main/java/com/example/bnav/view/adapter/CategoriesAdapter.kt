package com.example.bnav.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bnav.R
import com.example.bnav.databinding.ItemSportBinding
import com.example.bnav.model.Sport


class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    var items = ArrayList<Sport?>()

    fun setListData(data: ArrayList<Sport?>) {
        this.items = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_sport, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items[position]?.let { holder.bind(it) }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemSportBinding.bind(view)

        val spName = binding.title
        val strFormat = binding.subTitle
        val imageThumb = binding.imageSport

        fun bind(data: Sport) {
            spName.text = data.strSport
            if(!TextUtils.isEmpty(data.strFormat)) {
                strFormat.text = data.strFormat
            } else {
                strFormat.text = "No Format available."
            }

            val url = data.strSportThumb
            Glide.with(imageThumb)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.default_thumb)
                .error(R.drawable.default_thumb)
                .fallback(R.drawable.default_thumb)
                .into(imageThumb)
        }

    }

}