package com.example.bnav.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bnav.R
import com.example.bnav.databinding.ItemScorerBinding
import com.example.bnav.databinding.ItemTeamBinding
import com.example.bnav.model.Score

class ScoresAdapter : RecyclerView.Adapter<ScoresAdapter.MyViewHolder>() {

    var items = ArrayList<Score?>()

    fun setListData(data: ArrayList<Score?>) {
        this.items = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_scorer, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items[position]?.let { holder.bind(it) }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemScorerBinding.bind(view)

        val strHomeTeam = binding.txtHomeTeam
        val strAwayTeam = binding.txtAwayTeam
        val intHomeScore = binding.txtHomeScore
        val intAwayScore = binding.txtAwayScore
        val dateEvent = binding.txtDate


        fun bind(data: Score) {
            strHomeTeam.text = data.strHomeTeam
            strAwayTeam.text = data.strAwayTeam
            intHomeScore.text = data.intHomeScore
            intHomeScore.text = data.intHomeScore
            intAwayScore.text = data.intAwayScore
            dateEvent.text = data.dateEvent


        }

    }

}