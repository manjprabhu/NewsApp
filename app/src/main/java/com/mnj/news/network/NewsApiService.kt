package com.mnj.news.network

import com.mnj.news.model.NewsData
import retrofit2.http.GET

interface NewsApiService {

    @GET("/v2/top-headlines")
    suspend fun getHeadLines(): List<NewsData>
}