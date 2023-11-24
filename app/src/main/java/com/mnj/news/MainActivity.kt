package com.mnj.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnj.news.newsitems.ContentScreen
import com.mnj.news.ui.theme.NewsTheme
import com.mnj.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel: NewsViewModel = viewModel()

                    val generalNews by viewModel.homeNews.collectAsState()
                    val sportsNews by viewModel.sportsNews.collectAsState()

                    val entertainmentNews by viewModel.entertainmentNews.collectAsState()
                    val scienceNews by viewModel.scienceNews.collectAsState()

                    val businessNews by viewModel.businessNews.collectAsState()
                    val healthNews by viewModel.healthNews.collectAsState()

                    val technologyNews by viewModel.technologyNews.collectAsState()

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
}


