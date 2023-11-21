package com.mnj.news.model

sealed class Status<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T?) : Status<T>(data)
    class Error<T>(data: T? = null, message: String?) : Status<T>(data, message)
    class Loading<T>() : Status<T>()

}