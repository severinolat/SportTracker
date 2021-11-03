package com.example.bnav.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bnav.R
import com.example.bnav.databinding.ItemSportBinding
import com.example.bnav.databinding.ItemTeamBinding
import com.example.bnav.model.Team

class FavsAdapter : RecyclerView.Adapter<FavsAdapter.MyViewHolder>() {

    var items = ArrayList<Team?>()

    fun setListData(data: ArrayList<Team?>) {
        this.items = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavsAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items[position]?.let { holder.bind(it) }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemTeamBinding.bind(view)

        val strTeam = binding.txtName
        val strSport = binding.txtSport
        val strTeamLogo = binding.imageTeam
        val strAlternate = binding.txtFullName

        fun bind(data: Team) {
            strTeam.text = data.strTeam
            strSport.text = data.strSport
            strAlternate.text = data.strAlternate

            val url = data.strTeamLogo
            Glide.with(strTeamLogo)
                .load(url)
                .centerInside()
                .placeholder(R.drawable.default_thumb)
                .error(R.drawable.default_thumb)
                .fallback(R.drawable.default_thumb)
                .into(strTeamLogo)
        }

    }

}