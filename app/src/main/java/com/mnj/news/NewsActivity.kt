package com.mnj.news

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mnj.news.newsitems.LocationComposable
import com.mnj.news.newsitems.ShowProgressIndicator
import com.mnj.news.ui.theme.NewsTheme
import com.mnj.news.viewmodel.NewsViewModel
import com.mnj.news.viewmodel.SealedUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.URL
import java.util.Collections
import java.util.Locale


@AndroidEntryPoint
class NewsActivity : ComponentActivity() {

    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        if (!isNetworkAvailable(applicationContext)) {
            return
        }

        println("==>> iPAddress: ${myFunction()}")

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

                    //val progress by newsViewModel.progress.collectAsStateWithLifecycle()

//                    val uiState = newsViewModel.state.collectAsStateWithLifecycle().value

                    val uiState = newsViewModel.state.value

                    println("==>> progress : ${uiState is SealedUiState.Loading}")

                    /*   when (uiState) {
                           is UiState.Loading -> ShowProgressIndicator(isShow = true)
                           is UiState.Success -> Unit
                           is UiState.Error -> Unit//ShowError(uiState.data)
                       }*/


                    when (uiState) {
                        is SealedUiState.Loading -> ShowProgressIndicator(isShow = true)
                        is SealedUiState.Error -> ShowError(text = uiState.error)
                        is SealedUiState.Success -> uiState.data?.let { LocationComposable(data = it) }
                    }


                    /*    if(uiState is SealedUiState.Loading) {
                            ShowProgressIndicator(isShow = true)
                        }
                        if(uiState is SealedUiState.Error) {
                           ShowError(text = uiState.error)
                        }*/

                    //  ShowProgressIndicator(isShow = uiState.isLoading)


                    //Collects the value from flow in lifecycle-aware manner. This is recommended  way to collect flows on Android app.
                    /*          val generalNews by newsViewModel.homeNews.collectAsStateWithLifecycle()
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
                              }*/
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

private fun getIpAddress(context: Context): String? {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val linkAddresses =
        connectivityManager.getLinkProperties(connectivityManager.activeNetwork)?.linkAddresses

    val ipV4Address = linkAddresses?.firstOrNull { linkAddress ->
        linkAddress.address.hostAddress?.contains('.') ?: false
    }?.address?.hostAddress
    println("==>>> IP address: $ipV4Address")
    return ipV4Address
}

private suspend fun getMyPublicIpAsync(): Deferred<String> =
    coroutineScope {
        async(Dispatchers.IO) {
            var result = ""
            result = try {
                val url = URL("https://api.ipify.org")
                val httpsURLConnection = url.openConnection()
                val iStream = httpsURLConnection.getInputStream()
                val buff = ByteArray(1024)
                val read = iStream.read(buff)
                String(buff, 0, read)
            } catch (e: Exception) {
                "error : $e"
            }
            return@async result
        }
    }

private fun myFunction(): String {
    var myPublicIp = ""
    runBlocking {
        myPublicIp = getMyPublicIpAsync().await()
    }
        /*CoroutineScope(Dispatchers.Main).launch {
            myPublicIp = getMyPublicIpAsync().await()
            println("==>> Public IP address : $myPublicIp")
        }*/
    return myPublicIp
}

@Composable
fun ShowError(text: String) {
    println("==>>> ShowError : $text")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Green,
                fontStyle = FontStyle.Italic
            )
        )
    }
}


