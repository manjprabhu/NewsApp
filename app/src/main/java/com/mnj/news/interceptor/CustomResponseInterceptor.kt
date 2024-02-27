package com.mnj.news.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject

class CustomResponseInterceptor: Interceptor {

    //Re writing the response using retrofit.
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        try {
            val jObject = JSONObject()
            if(response.code == 200) {
                jObject.put("RESPONSE_CODE","SUCCESS")
            }
        } catch (e:JSONException) {
            e.printStackTrace()
        }
        return response
    }
}