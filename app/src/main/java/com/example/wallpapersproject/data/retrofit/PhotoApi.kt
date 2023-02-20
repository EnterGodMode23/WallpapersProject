package com.example.wallpapersproject.data.retrofit

import com.example.wallpapersproject.data.models.Photos
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("api/")
    fun getPhotos(@Query("key") apiKey: String): Single<Photos>
}