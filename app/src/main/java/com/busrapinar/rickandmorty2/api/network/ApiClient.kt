package com.busrapinar.rickandmorty2.api.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //base url
    private const val baseUrl = "https://rickandmortyapi.com/api/"

    //api client'i create ettiğimiz yer ezber
    fun createClient() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}