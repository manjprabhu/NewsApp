package com.mnj.news.model

data class NewsModel(
    val title: String,
    val urlToImage: String?,
    val description: String?,
    val url: String?,
    val sourceName: String?,
    val publishedAt: String?,
    val content: String?,
    val author:String?
) {
}