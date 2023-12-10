package com.busrapinar.rickandmorty2.ui.adapters.activities.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.busrapinar.rickandmorty2.databinding.ActivityDetailBinding
import com.busrapinar.rickandmorty2.util.loadUrl

class DetailActivity : AppCompatActivity() {
    /*
   intent ve data gönderme
   dinamik(@Path ile) End-Point oluştutma
   app seviyesinde Extensions fonksiyonlar oluşturma.(loadUrl fonk.)
   liveData kapsülleme


   yapılacaklar

   ui düzenlenecek card viewlarla- kenarlar oval olsun biraz.
   arka plana renk tasarım (serbest)
   SharedPreferences ile MainAct. de atılan istek kaydedilir.
   sonraki uygulama açılışında eğer kaydettiğimiz data boş değilse kaydettiğimiz datayı kullan ve hiç End-Pointe istek atma
   eğer shared'a kaydettiğimiz data boş ise yani app silinmiş tekrar yüklenmiş vs. End-pointe tekrar istek atarak shared'a tekrar kaydet.

   ---VAKIT OLURSA---
   Dependency Injection nedir ?
   repository nedir ne işe yarar neden kullanırız ?
    */
    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var idValue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgs()
        initBinding()
        initViewModel()
        initObserver()
        getCharacterById()
    }

    private fun initObserver() {
        viewModel.characterLiveData.observe(this@DetailActivity) {
            binding.apply {
                ivIcon.loadUrl(it.image)
                tvName.text = it.name
                tvGender.text = it.gender
                tvStatus.text = it.status
                tvGalaxy.text = it.origin.name
                tvEpSize.text = it.episode.size.toString()
                tvLocName.text = it.location.name
            }
        }
    }

    private fun getArgs() {
        idValue = intent.getIntExtra("KEY_DATA", 0)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
    }

    private fun initBinding() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getCharacterById() {
        viewModel.getCharacter(idValue)
    }
}