package com.mnj.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
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
                    CreateNewsCategoryTab()
                }
            }
        }
    }
}

@Composable
fun GetHeadLines(viewModel: NewsViewModel = viewModel()) {
    viewModel.newsFlow.collectAsState().let {
        it.value.data?.forEach {
            println("==>>> news : ${it.headLine}")
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CreateNewsCategoryTab() {
    val newsCategories = listOf(
        NewsCategory(Constants.HOME),
        NewsCategory(Constants.GENERAL),
        NewsCategory(Constants.BUSINESS),
        NewsCategory(Constants.ENTERTAINMENT),
        NewsCategory(Constants.HEALTH),
        NewsCategory(Constants.TECHNOLOGY),
        NewsCategory(Constants.SCIENCE),
        NewsCategory(Constants.SPORTS)
    )

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    var pagerState = rememberPagerState(pageCount = newsCategories.size)

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
            newsCategories.forEachIndexed { index, newsCategory ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = newsCategory.title) })
            }
        }

        com.google.accompanist.pager.HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = newsCategories[index].title)
            }

        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsTheme {
        Greeting("Android")
    }
}