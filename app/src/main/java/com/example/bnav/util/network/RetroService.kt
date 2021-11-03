package com.demo.myfirstapp.network

import com.example.bnav.model.ScoreList
import com.example.bnav.model.SportList
import com.example.bnav.model.TeamList
import com.google.gson.JsonElement
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("all_sports.php")
    fun getSportList(): Call<SportList>

    @GET("lookup_all_teams.php?id=4328")
    fun getFavsList(): Call<TeamList>

    @GET("searchevents.php?e=Arsenal_vs_Chelsea")
    fun getScoreList(): Call<ScoreList>

    @GET("searchteams.php")
    fun getTeamBy(@Query("t") query: String): Call<TeamList>


}