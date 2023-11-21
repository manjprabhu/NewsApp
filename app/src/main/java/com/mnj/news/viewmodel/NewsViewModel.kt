package com.mnj.news.viewmodel

import androidx.lifecycle.ViewModel
import com.mnj.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) :ViewModel(){
}