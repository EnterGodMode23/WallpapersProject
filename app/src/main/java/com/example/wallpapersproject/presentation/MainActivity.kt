package com.example.wallpapersproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wallpapersproject.R
import com.example.wallpapersproject.data.retrofit.PhotoApi
import com.example.wallpapersproject.data.retrofit.RetrofitClient
import com.example.wallpapersproject.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val API_KEY = "33786499-5584879290479131b764ec16b&q=bird&image_type=photo"

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_WallpapersProject)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = RetrofitClient.getClient()
            .create(PhotoApi::class.java)
            .getPhotos(API_KEY)

        client
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it ->
                val photos = it.total
                binding.tv.text = photos.toString()
            }, {
                error ->
                Log.e("AAA", error.toString())
            })
    }
}