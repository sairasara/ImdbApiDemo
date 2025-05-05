package com.saira.imdb.di

import com.saira.imdb.data.api.ApiService
import com.saira.imdb.data.repository.MovieRepositoryImpl
import com.saira.imdb.domain.repository.MovieRepository
import com.saira.imdb.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    // Provide the MovieRepository instance
    @Provides
    fun provideMovieRepository(apiService: ApiService,
                               networkUtils: NetworkUtils): MovieRepository {
        return MovieRepositoryImpl(apiService, networkUtils)
    }
}
