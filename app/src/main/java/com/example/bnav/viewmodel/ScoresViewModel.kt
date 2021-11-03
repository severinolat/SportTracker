package com.example.bnav.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.myfirstapp.network.RetroInstance
import com.demo.myfirstapp.network.RetroService
import com.example.bnav.model.ScoreList
import retrofit2.Call
import retrofit2.Response

class ScoresViewModel() : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<ScoreList>


    init {
        recyclerListData = MutableLiveData()
    }


    fun getRecyclerListDataObserver(): MutableLiveData<ScoreList> {
        return recyclerListData
    }



    fun makeApiCall() {
        Log.d("calling", "api call")
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getScoreList()

        call.enqueue(object : retrofit2.Callback<ScoreList>{
            override fun onResponse(call: Call<ScoreList>, response: Response<ScoreList>) {
                Log.d("calling", response.body().toString())
                if(response.isSuccessful) {

                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ScoreList>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()

                recyclerListData.postValue(null)
            }
        })
    }


}



