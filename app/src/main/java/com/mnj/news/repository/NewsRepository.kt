package com.mnj.news.repository

import com.mnj.news.model.Location
import com.mnj.news.model.NewsData
import com.mnj.news.model.Status
import retrofit2.Response

interface NewsRepository {

    suspend fun getHeadLines(category: String): Status<Location>
}