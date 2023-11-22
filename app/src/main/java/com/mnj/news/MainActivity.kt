package com.mnj.news

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnj.news.model.NewsModel
import com.mnj.news.ui.theme.NewsTheme
import com.mnj.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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
                    LauncherScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LauncherScreen() {
    androidx.compose.material.Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.primary,
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "News",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            )
        },
        backgroundColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            CreateNewsCategoryTab()
            Spacer(Modifier.height(5.dp))
            GetSportsNews()
//          ReadNews(url = "https://www.siasat.com/world-cup-mohammed-shamis-estranged-wife-hasin-jahan-stuns-fans-with-cryptic-video-2915839/\\n\")\n")
        }
    }
}


@OptIn(
    ExperimentalFoundationApi::class
)
@Composable
fun CreateNewsCategoryTab() {
    val newsCategories = listOf(
        NewsCategory(Constants.HOME.uppercase(Locale.ROOT)),
        NewsCategory(Constants.GENERAL.uppercase(Locale.ROOT)),
        NewsCategory(Constants.BUSINESS.uppercase(Locale.ROOT)),
        NewsCategory(Constants.ENTERTAINMENT.uppercase(Locale.ROOT)),
        NewsCategory(Constants.HEALTH.uppercase(Locale.ROOT)),
        NewsCategory(Constants.TECHNOLOGY.uppercase(Locale.ROOT)),
        NewsCategory(Constants.SCIENCE.uppercase(Locale.ROOT)),
        NewsCategory(Constants.SPORTS.uppercase(Locale.ROOT))
    )

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val pagerState = androidx.compose.foundation.pager.rememberPagerState()

    LaunchedEffect(selectedTabIndex) {

       pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }


    Column(modifier = Modifier.height(50.dp)) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = MaterialTheme.colorScheme.background
        ) {
            newsCategories.forEachIndexed { index, newsCategory ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = newsCategory.title,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    })
            }
        }

        HorizontalPager(
            pageCount = newsCategories.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight()
        ) { index ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = newsCategories[index].title)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsList(newsList: List<NewsModel>) {
    LazyColumn {
     /*   stickyHeader {
            CreateNewsCategoryTab()
        }*/
        itemsIndexed(items = newsList) { _, item ->
            NewsItem(news = item) {
                println("==>> Clicked item  :${item.url}")
            }
        }
    }
}


@Composable
fun GetHeadLines(viewModel: NewsViewModel = viewModel()) {
    viewModel.generalNews.collectAsState().let {
        it.value.data?.let { it1 ->
            NewsList(newsList = it1)
        }
    }
}

@Composable
fun GetSportsNews(viewModel: NewsViewModel = viewModel()) {
    viewModel.sportsNews.collectAsState().let {
        it.value.data?.let { it1 ->
            NewsList(newsList = it1)
        }
    }
}


