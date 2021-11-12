package com.example.syscostarwars.services.network.api

import com.example.syscostarwars.data.Planet
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("planets")
    suspend fun getPlanets(@Query("page") page: Int): Planet
}