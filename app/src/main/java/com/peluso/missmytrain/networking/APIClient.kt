package com.peluso.missmytrain.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit {
        if(retrofit==null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}
