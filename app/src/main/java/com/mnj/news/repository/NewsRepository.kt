package com.mnj.news.repository

import com.mnj.news.model.NewsData
import retrofit2.Response

interface NewsRepository {

    suspend fun getHeadLines(category: String): Response<NewsData>
}