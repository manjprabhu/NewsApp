package com.mnj.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnj.news.model.NewsData
import com.mnj.news.model.NewsModel
import com.mnj.news.model.Status
import com.mnj.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _homeNews: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val homeNews: StateFlow<Status<List<NewsModel>>> = _homeNews.asStateFlow()

    private val _generalNews: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val generalNews: StateFlow<Status<List<NewsModel>>> = _generalNews.asStateFlow()

    private val _scienceNews: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val scienceNews: StateFlow<Status<List<NewsModel>>> = _scienceNews.asStateFlow()

    private val _healthNews: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val healthNews: StateFlow<Status<List<NewsModel>>> = _healthNews.asStateFlow()

    private val _entertainmentNews: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val entertainmentNews: StateFlow<Status<List<NewsModel>>> = _entertainmentNews.asStateFlow()

    private val _businessNews: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val businessNews: StateFlow<Status<List<NewsModel>>> = _businessNews.asStateFlow()

    private val _technologyNews: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val technologyNews: StateFlow<Status<List<NewsModel>>> = _technologyNews.asStateFlow()

    private val _sportsNews: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val sportsNews: StateFlow<Status<List<NewsModel>>> = _sportsNews.asStateFlow()


    init {
        getHeadLines("general")
        getSportsNews("sports")
      /*  getHeadLines("general")
        getHeadLines("science")
        getHeadLines("health")
        getHeadLines("entertainment")
        getHeadLines("business")
        getHeadLines("technology")
        getHeadLines("sports")*/

    }

    private fun getHeadLines(category: String) {
        viewModelScope.launch {
            _generalNews
                .value = Status.Loading()
            val result = repository.getHeadLines(category = category)
            _generalNews.value = handleNetworkResponse(result)
        }
    }

    private fun getSportsNews(category: String) {
        viewModelScope.launch {
            _sportsNews
                .value = Status.Loading()
            val result = repository.getHeadLines(category = category)
            _sportsNews.value = handleNetworkResponse(result)
        }
    }

    private fun handleNetworkResponse(response: Response<NewsData>): Status<List<NewsModel>> {
        val tempList = mutableListOf<NewsModel>()
        if (response.isSuccessful) {
            val newsData = response.body()
            if (newsData != null) {
                response.body()?.articles?.forEach {

                    tempList.add(
                        NewsModel(
                            it.title, it.urlToImage, it.description, it.url,
                            it.source.name, it.publishedAt, it.content, it.author
                        )
                    )
                }
                return Status.Success(tempList)
            }
        }
        return Status.Error(tempList, "Error")
    }
}