package com.example.bnav.model


data class SportList(val sports: ArrayList<Sport?>? = null)
data class Sport(val idSport: String, val strSport: String, val strFormat:String,val strSportThumb:String, val strSportIconGreen:String, val strSportDescription: String)
