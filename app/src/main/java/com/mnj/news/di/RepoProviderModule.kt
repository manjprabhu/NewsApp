package com.mnj.news.di

import com.mnj.news.network.NewsApiService
import com.mnj.news.repository.NewsRepository
import com.mnj.news.repository.NewsRepositoryImpl
import com.mnj.news.viewmodel.NewsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoProviderModule {

    @Provides
    @Singleton
    fun providesRepository(newsApiService: NewsApiService): NewsRepository {
        return NewsRepositoryImpl(newsApiService)
    }

    @Provides
    @Singleton
    fun providesViewModel(repo: NewsRepository): NewsViewModel {
        return NewsViewModel(repo)
    }


}