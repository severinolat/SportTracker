package com.example.bnav.model


data class ScoreList(val event: ArrayList<Score?>? = null)
data class Score(val idEvent: String, val strEvent: String, val strSport:String,val strLeague:String, val strHomeTeam:String, val strAwayTeam: String, val intHomeScore:String, val intRound: String, val intAwayScore:String, val dateEvent: String)
