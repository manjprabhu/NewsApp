package com.mnj.news

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnj.news.newsitems.ContentScreen
import com.mnj.news.ui.theme.NewsTheme
import com.mnj.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        if (!isNetworkAvailable(applicationContext)) {
            return
        }

        val newsViewModel: NewsViewModel by viewModels()

        setContent {
            NewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LaunchedEffect(key1 = Unit) {
                        newsViewModel.getHeadLines()
                    }

                    //Collects the value from flow in lifecycle-aware manner. This is recommended  way to collect flows on Android app.
                    val generalNews by newsViewModel.homeNews.collectAsStateWithLifecycle()
                    val sportsNews by newsViewModel.sportsNews.collectAsStateWithLifecycle()

                    val entertainmentNews by newsViewModel.entertainmentNews.collectAsStateWithLifecycle()
                     val scienceNews by newsViewModel.scienceNews.collectAsStateWithLifecycle()

                    val businessNews by newsViewModel.businessNews.collectAsStateWithLifecycle()
                    val healthNews by newsViewModel.healthNews.collectAsStateWithLifecycle()

                    val technologyNews by newsViewModel.technologyNews.collectAsStateWithLifecycle()

                    generalNews.data?.let {
                        entertainmentNews.data?.let { it1 ->
                            scienceNews.data?.let { it2 ->
                                businessNews.data?.let { it3 ->
                                    healthNews.data?.let { it4 ->
                                        technologyNews.data?.let { it5 ->
                                            sportsNews.data?.let { it6 ->
                                                ContentScreen(
                                                    generalList = it,
                                                    entertainmentList = it1,
                                                    scienceList = it2,
                                                    businessList = it3,
                                                    healthList = it4,
                                                    technologyList = it5,
                                                    sportsList = it6
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    // Check internet connection
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}


