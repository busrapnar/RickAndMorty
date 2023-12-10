package com.busrapinar.rickandmorty2.ui.adapters.activities.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.busrapinar.rickandmorty2.api.models.CharacterResponse
import com.busrapinar.rickandmorty2.api.network.ApiClient
import com.busrapinar.rickandmorty2.api.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val apiClient = ApiClient.createClient().create(ApiService::class.java)

    val mutableCharacterResponse = MutableLiveData<CharacterResponse>()

    fun getCharacter() {
        viewModelScope.launch {
            apiClient.getCharacter().enqueue(object : Callback<CharacterResponse> {
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    mutableCharacterResponse.value = response.body()
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    Log.d("Busra Pinar", "onFailure: ${t.message}")
                }
            })
        }
    }

}