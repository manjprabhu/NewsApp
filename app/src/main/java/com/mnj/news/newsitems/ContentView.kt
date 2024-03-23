package com.mnj.news.newsitems

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mnj.news.Constants
import com.mnj.news.NewsCategory
import com.mnj.news.model.NewsModel
import kotlinx.coroutines.launch
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentScreen(
    generalList: MutableList<NewsModel>,
    entertainmentList: MutableList<NewsModel>,
    scienceList: MutableList<NewsModel>,
    businessList: MutableList<NewsModel>,
    healthList: MutableList<NewsModel>,
    technologyList: MutableList<NewsModel>,
    sportsList: MutableList<NewsModel>
) {
    Scaffold(
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

        ContentView(
            generalList = generalList,
            entertainmentList = entertainmentList,
            scienceList = scienceList,
            businessList = businessList,
            healthList = healthList,
            technologyList = technologyList,
            sportsList = sportsList
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentView(
    generalList: MutableList<NewsModel>,
    entertainmentList: MutableList<NewsModel>,
    scienceList: MutableList<NewsModel>,
    businessList: MutableList<NewsModel>,
    healthList: MutableList<NewsModel>,
    technologyList: MutableList<NewsModel>,
    sportsList: MutableList<NewsModel>
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TabRowView(pagerState)

        Spacer(Modifier.height(5.dp))

        PagerView(
            pagerState = pagerState,
            generalList = generalList,
            entertainmentList = entertainmentList,
            scienceList = scienceList,
            businessList = businessList,
            healthList = healthList,
            technologyList = technologyList,
            sportsList = sportsList
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowView(
    pagerState: PagerState
) {

    val newsCategories = listOf(
        NewsCategory(Constants.HOME.uppercase(Locale.ROOT)),
        NewsCategory(Constants.BUSINESS.uppercase(Locale.ROOT)),
        NewsCategory(Constants.ENTERTAINMENT.uppercase(Locale.ROOT)),
        NewsCategory(Constants.HEALTH.uppercase(Locale.ROOT)),
        NewsCategory(Constants.TECHNOLOGY.uppercase(Locale.ROOT)),
        NewsCategory(Constants.SCIENCE.uppercase(Locale.ROOT)),
        NewsCategory(Constants.SPORTS.uppercase(Locale.ROOT))
    )

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.height(50.dp)) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colorScheme.background,
        ) {
            newsCategories.forEachIndexed { index, newsCategory ->
                val isSelected = pagerState.currentPage == index
                Tab(
                    selected = isSelected,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = newsCategory.title,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerView(
    pagerState: PagerState,
    generalList: MutableList<NewsModel>,
    entertainmentList: MutableList<NewsModel>,
    scienceList: MutableList<NewsModel>,
    businessList: MutableList<NewsModel>,
    healthList: MutableList<NewsModel>,
    technologyList: MutableList<NewsModel>,
    sportsList: MutableList<NewsModel>
) {

    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                pageCount = 7,
                state = pagerState
            ) { page ->
                when (page) {
                    0 -> {
                        NewsList(newsList = generalList)
                    }

                    1 -> {
                        NewsList(newsList = businessList)
                    }

                    2 -> {
                        NewsList(newsList = entertainmentList)
                    }

                    3 -> {
                        NewsList(newsList = healthList)
                    }

                    4 -> {
                        NewsList(newsList = technologyList)
                    }

                    5 -> {
                        NewsList(newsList = scienceList)
                    }

                    6 -> {
                        NewsList(newsList = sportsList)
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun NewsList(newsList: MutableList<NewsModel>) {
    var showWebView by remember { mutableStateOf(false) }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 2.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(items = newsList) { _, item ->
            NewsItem(news = item) {
                showWebView = true
            }
            if (showWebView)
                item.url?.let {
                    ReadNews(url = it)
                }
        }
    }
}

@Composable
fun ShowProgressIndicator(isShow: Boolean) {

    println("==>> IsShow: $isShow")

    if(!isShow)
        return

     Column(
         modifier = Modifier.fillMaxSize(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
     ) {
         CircularProgressIndicator(
             modifier = Modifier.size(75.dp),
             color = MaterialTheme.colorScheme.primary,
             strokeWidth = 5.dp
         )
     }
/*
    CircularProgressIndicator(
        modifier = Modifier
            .progressSemantics()
            .size(12.dp),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 10.dp
    )*/
}

