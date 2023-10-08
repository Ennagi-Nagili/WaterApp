package com.annaginagili.waterapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiClient {
    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val API_KEY = "13a99525892d46629666afd797f6af8b"

    val newsApiService: NewsApiService by lazy {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        retrofit.create(NewsApiService::class.java)
    }
}