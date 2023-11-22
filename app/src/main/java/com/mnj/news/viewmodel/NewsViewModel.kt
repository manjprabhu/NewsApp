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

    private val _newsFlow: MutableStateFlow<Status<List<NewsModel>>> =
        MutableStateFlow(Status.Loading())
    val newsFlow: StateFlow<Status<List<NewsModel>>> = _newsFlow.asStateFlow()

    init {
        getHeadLines("technology")
    }

    private fun getHeadLines(category: String) {
        viewModelScope.launch {
            _newsFlow.value = Status.Loading()
            val result = repository.getHeadLines(category = category)
            _newsFlow.value = handleNetworkResponse(result)
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
                            it.source.name, it.publishedAt, it.content,it.author
                        )
                    )
                }
                _newsFlow.value = Status.Success(tempList)
            }
        }
        return Status.Error(tempList, "Error")
    }
}