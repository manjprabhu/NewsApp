package com.mnj.news.model

data class NewsModel(
    val headLine: String,
    val image: String?,
    val description: String?,
    val url: String?,
    val source: String?,
    val time: String?,
    val content: String?
) {
}