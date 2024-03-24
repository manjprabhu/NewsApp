package com.mnj.news.repository

import com.mnj.news.BuildConfig
import com.mnj.news.model.Location
import com.mnj.news.model.NewsData
import com.mnj.news.model.Status
import com.mnj.news.network.NewsApiService
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApiService) : NewsRepository {

    override suspend fun getHeadLines(category: String): Status<Location> {
        /*return newsApi.getHeadLines(
            country = "in",
            category = category,
            apiKey = BuildConfig.API_KEY
        )*/

        delay(10000)
        return try {
            Status.Success(
                Location(
                    "115.99.109.240",
                    "115.99.108.0/23",
                    "IPv4",
                    "Bengaluru",
                    "Karnataka",
                    "KA",
                    "IN",
                    "India",
                    "IN",
                    "IND",
                    "New Delhi",
                    ".in",
                    "AS",
                    false,
                    "560002",
                    12.9634,
                    77.5855,
                    "Asia/Kolkata",
                    "+0530",
                    "+91",
                    "INR",
                    "Rupee",
                    "en-IN,hi,bn,te,mr,ta,ur,gu,kn,ml,or,pa,as,bh,sat,ks,ne,sd,kok,doi,mni,sit,sa,fr,lus,inc",
                    3287590.0,
                    1352617328,
                    "AS17488",
                    "Hathway IP Over Cable Internet"
                )
            )

        } catch (e: Exception) {
            Status.Error(null, "Error in fetching data!!!")
        }
    }
}