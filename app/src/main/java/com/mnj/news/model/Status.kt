package com.mnj.news.model

sealed class Status<T>(val data:T?, val message: String?=null) {

    class Success<T>(data: T) : Status<T>(data)
    class Error<T>(data: T?, message: String?) : Status<T>(data,message)
}