package com.busrapinar.rickandmorty2.api.network


import com.busrapinar.rickandmorty2.api.models.CharacterResponse
import com.busrapinar.rickandmorty2.api.models.ResultCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    fun getCharacter() : Call<CharacterResponse>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int) : Call<ResultCharacter>
}