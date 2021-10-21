package com.example.flickrbyretrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("rest/?method=flickr.photos.search&per_page=10&api_key=1a8e792c9028b8a8af023ae2289360ed&format=json&nojsoncallback=1")
    fun doGetListResources(@Query("text")text:String): Call<photoDetails?>?
}