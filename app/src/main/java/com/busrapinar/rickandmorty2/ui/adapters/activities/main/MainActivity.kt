package com.busrapinar.rickandmorty2.ui.adapters.activities.main

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.busrapinar.rickandmorty2.api.models.ResultCharacter
import com.busrapinar.rickandmorty2.databinding.ActivityMainBinding
import com.busrapinar.rickandmorty2.ui.adapters.CharactersAdapter
import com.busrapinar.rickandmorty2.ui.adapters.activities.detail.DetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var characterAdapter : CharactersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        initObservers()
        setListeners()
        viewModel.getCharacter()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    private fun initBinding() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    private fun setListeners() {

    }
    private fun initObservers() {
        viewModel.mutableCharacterResponse.observe(this@MainActivity) {
            initAdapter(it.results)
        }
    }
    private fun initAdapter (data: List<ResultCharacter>) {
        characterAdapter = CharactersAdapter(data, ::adapterClicked)
        viewBinding.rvCharacters.adapter = characterAdapter
    }

    private fun adapterClicked(data: Int) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("KEY_DATA", data)
        startActivity(intent)
    }
}