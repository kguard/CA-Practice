package com.example.data.di

import com.example.data.data_source.CoinDataSource
import com.example.data.data_source.CoinDataSourceImpl
import com.example.data.data_source.CoinDetailDataSouceImpl
import com.example.data.data_source.CoinDetailDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideCoinDataSource(retrofit: Retrofit):CoinDataSource{
        return CoinDataSourceImpl(retrofit)
    }
    @Provides
    @Singleton
    fun provideCoinDetailDataSource(retrofit: Retrofit):CoinDetailDataSource{
        return CoinDetailDataSouceImpl(retrofit)
    }
}