package com.mnj.news.repository

import com.mnj.news.BuildConfig
import com.mnj.news.model.NewsData
import com.mnj.news.network.NewsApiService
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApiService) : NewsRepository {

    override suspend fun getHeadLines(category: String): Response<NewsData> {
        return newsApi.getHeadLines(
            country = "in",
            category = category,
            apiKey = BuildConfig.API_KEY
        )
    }
}