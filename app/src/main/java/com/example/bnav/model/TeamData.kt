package com.example.bnav.model

data class TeamList(val teams: ArrayList<Team?>? = null)

data class Team(val idTeam: String, val strTeam: String, val strAlternate:String,val intFormedYear:String, val strSport:String, val strLeague: String, val strDescriptionEN:String, val strCountry:String, val strTeamLogo: String )

