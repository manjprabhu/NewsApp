package com.mnj.news.model

data class NewsData(var status: String,
                    var totalResults: Int,
                    var articles: List<Article>) {
}