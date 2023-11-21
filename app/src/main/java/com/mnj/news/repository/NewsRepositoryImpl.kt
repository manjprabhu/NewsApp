package com.mnj.news.repository

import com.mnj.news.network.NewsApiService
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(newsApi:NewsApiService) : NewsRepository {
}