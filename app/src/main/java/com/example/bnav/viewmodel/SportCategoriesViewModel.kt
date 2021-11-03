package com.example.bnav.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.myfirstapp.network.RetroInstance
import com.demo.myfirstapp.network.RetroService
import com.example.bnav.model.SportList
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import org.json.JSONArray

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SportCategoriesViewModel() : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<SportList>


    init {
        recyclerListData = MutableLiveData()
    }


    fun getRecyclerListDataObserver(): MutableLiveData<SportList> {
        return recyclerListData
    }



    fun makeApiCall() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getSportList()

        call.enqueue(object : retrofit2.Callback<SportList>{
            override fun onResponse(call: Call<SportList>, response: Response<SportList>) {

                if(response.isSuccessful) {

                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<SportList>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()

                recyclerListData.postValue(null)
            }
        })
    }


}



