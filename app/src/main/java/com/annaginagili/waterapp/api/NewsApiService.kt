package com.annaginagili.waterapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    fun getWaterRelatedArticles(
        @Query("q") query: String = "water",
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}