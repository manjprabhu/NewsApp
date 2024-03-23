package com.mnj.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnj.news.Constants
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

    private val _homeNews: MutableStateFlow<Status<MutableList<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val homeNews: StateFlow<Status<MutableList<NewsModel>>> = _homeNews.asStateFlow()

    private val _scienceNews: MutableStateFlow<Status<MutableList<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val scienceNews: StateFlow<Status<MutableList<NewsModel>>> = _scienceNews.asStateFlow()

    private val _healthNews: MutableStateFlow<Status<MutableList<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val healthNews: StateFlow<Status<MutableList<NewsModel>>> = _healthNews.asStateFlow()

    private val _entertainmentNews: MutableStateFlow<Status<MutableList<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val entertainmentNews: StateFlow<Status<MutableList<NewsModel>>> =
        _entertainmentNews.asStateFlow()

    private val _businessNews: MutableStateFlow<Status<MutableList<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val businessNews: StateFlow<Status<MutableList<NewsModel>>> = _businessNews.asStateFlow()

    private val _technologyNews: MutableStateFlow<Status<MutableList<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val technologyNews: StateFlow<Status<MutableList<NewsModel>>> = _technologyNews.asStateFlow()

    private val _sportsNews: MutableStateFlow<Status<MutableList<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val sportsNews: StateFlow<Status<MutableList<NewsModel>>> = _sportsNews.asStateFlow()


    init {

    }

    public fun getHeadLines() {
        getHeadLines(Constants.GENERAL)
        getHeadLines(Constants.SPORTS)
        getHeadLines(Constants.SCIENCE)
        getHeadLines(Constants.TECHNOLOGY)
        getHeadLines(Constants.ENTERTAINMENT)
        getHeadLines(Constants.BUSINESS)
        getHeadLines(Constants.HEALTH)
    }

    private fun getHeadLines(category: String) {
        when (category) {
            Constants.GENERAL -> {
                viewModelScope.launch {
                    _homeNews.value = Status.Loading()
                    val result = repository.getHeadLines(category = category)
                    _homeNews.value = handleNetworkResponse(result)
                }
            }
            Constants.BUSINESS -> {
                viewModelScope.launch {
                    _businessNews.value = Status.Loading()
                    val result = repository.getHeadLines(category = category)
                    _businessNews.value = handleNetworkResponse(result)
                }
            }
            Constants.ENTERTAINMENT -> {
                viewModelScope.launch {
                    _entertainmentNews.value = Status.Loading()
                    val result = repository.getHeadLines(category = category)
                    _entertainmentNews.value = handleNetworkResponse(result)
                }
            }
            Constants.HEALTH -> {
                viewModelScope.launch {
                    _healthNews.value = Status.Loading()
                    val result = repository.getHeadLines(category = category)
                    _healthNews.value = handleNetworkResponse(result)
                }
            }
            Constants.TECHNOLOGY -> {
                viewModelScope.launch {
                    _technologyNews.value = Status.Loading()
                    val result = repository.getHeadLines(category = category)
                    _technologyNews.value = handleNetworkResponse(result)
                }
            }
            Constants.SCIENCE -> {
                viewModelScope.launch {
                    _scienceNews.value = Status.Loading()
                    val result = repository.getHeadLines(category = category)
                    _scienceNews.value = handleNetworkResponse(result)
                }
            }
            Constants.SPORTS -> {
                viewModelScope.launch {
                    _sportsNews.value = Status.Loading()
                    val result = repository.getHeadLines(category = category)
                    _sportsNews.value = handleNetworkResponse(result)
                }
            }
        }
    }

    private fun handleNetworkResponse(response: Response<NewsData>): Status<MutableList<NewsModel>> {
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