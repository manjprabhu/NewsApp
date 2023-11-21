package com.mnj.news.repository

import com.mnj.news.model.NewsData
import com.mnj.news.network.NewsApiService
import retrofit2.Response
import javax.inject.Inject

interface NewsRepository {

    suspend fun getHeadLines(category:String): Response<NewsData>
}