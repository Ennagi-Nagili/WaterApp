package com.annaginagili.waterapp.fragment.myChallenges

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaginagili.waterapp.api.Article
import com.annaginagili.waterapp.api.NewsApiClient
import com.annaginagili.waterapp.api.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val newsData = MutableLiveData<List<Article>>()

    fun getNewsData(context: Context) {
        val newsApiService = NewsApiClient.newsApiService

        val call = newsApiService.getWaterRelatedArticles(apiKey = "13a99525892d46629666afd797f6af8b")
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    newsData.postValue(articles!!)
                } else {
                    Toast.makeText(context, "Failed to reach API endpoint", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(context, "Failed to get data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}