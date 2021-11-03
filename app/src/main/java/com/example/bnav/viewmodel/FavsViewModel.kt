package com.example.bnav.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.demo.myfirstapp.network.RetroInstance
import com.demo.myfirstapp.network.RetroService
import com.example.bnav.model.Team
import com.example.bnav.model.TeamList
import com.example.bnav.view.adapter.FavsAdapter
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

class FavsViewModel() : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<TeamList>


    init {
        recyclerListData = MutableLiveData()
    }


    fun getRecyclerListDataObserver(): MutableLiveData<TeamList> {
        return recyclerListData
    }



    fun makeApiCall() {
        Log.d("calling", "api call")
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getFavsList()

        call.enqueue(object : retrofit2.Callback<TeamList>{
            override fun onResponse(call: Call<TeamList>, response: Response<TeamList>) {

                if(response.isSuccessful) {

                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<TeamList>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()

                recyclerListData.postValue(null)
            }
        })
    }


    fun makeApiCall(input: String) {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getTeamBy(input)

        call.enqueue(object : retrofit2.Callback<TeamList>{
            override fun onResponse(call: Call<TeamList>, response: Response<TeamList>) {
                Log.d("sdfr", response.body().toString())
                if(response.isSuccessful) {

                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<TeamList>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()

                recyclerListData.postValue(null)
            }
        })
    }




}



