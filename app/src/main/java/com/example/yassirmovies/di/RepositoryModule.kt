package com.example.yassirmovies.di

import com.example.yassirmovies.network.MovieService
import com.example.yassirmovies.repositories.MovieRepository
import com.example.yassirmovies.repositories.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun getMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }
}