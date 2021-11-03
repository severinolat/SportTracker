package com.example.bnav.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bnav.R
import com.example.bnav.databinding.ItemTeamBinding
import com.example.bnav.model.Team
import com.example.bnav.util.safeLet

class SearchTeamAdapter(private var lstRes: ArrayList<Team?>) :
    RecyclerView.Adapter<SearchTeamAdapter.MyViewHolder>(), Filterable {

    var lstResFiltered = ArrayList<Team?>()

    init {
        lstResFiltered = lstRes
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTeamAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return lstResFiltered.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        lstResFiltered[position]?.let { holder.bind(it) }
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                lstResFiltered = if (charSearch.isEmpty()) {
                    lstRes
                } else {
                    val resultList = ArrayList<Team?>()
                    for (row in lstRes) {
                        if (row != null) {
                            safeLet(row.strTeam, row.strCountry) { title, content ->
                                if (title.contains(charSearch, ignoreCase = true)
                                    || content.contains(charSearch, ignoreCase = true)
                                ) {
                                    resultList.add(row)
                                }
                            }
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = lstResFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                lstResFiltered = results?.values as ArrayList<Team?>
                notifyDataSetChanged()
            }
        }
    }
}