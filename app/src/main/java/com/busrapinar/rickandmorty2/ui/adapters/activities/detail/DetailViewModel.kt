package com.busrapinar.rickandmorty2.ui.adapters.activities.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.busrapinar.rickandmorty2.api.models.ResultCharacter
import com.busrapinar.rickandmorty2.api.network.ApiClient
import com.busrapinar.rickandmorty2.api.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val apiService = ApiClient.createClient().create(ApiService::class.java)

    private val mutableCharacterData = MutableLiveData<ResultCharacter>()
    val characterLiveData : LiveData<ResultCharacter> get() = mutableCharacterData

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            apiService.getCharacterById(id).enqueue(object : Callback<ResultCharacter>{
                override fun onResponse(
                    call: Call<ResultCharacter>,
                    response: Response<ResultCharacter>
                ) {
                    mutableCharacterData.postValue(response.body())
                }

                override fun onFailure(call: Call<ResultCharacter>, t: Throwable) {
                    Log.d("Error", "onFailure: ${t.localizedMessage}")
                }
            })
        }
    }
}