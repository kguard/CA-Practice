package com.example.data.di

import com.example.data.CoinDetailRepositoryImpl
import com.example.data.CoinRepositoryImpl
import com.example.data.data_source.CoinDataSource
import com.example.data.data_source.CoinDetailDataSource
import com.example.domain.repository.CoinDetailRepository
import com.example.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCoinRepository(coinDataSource: CoinDataSource): CoinRepository {
        return CoinRepositoryImpl(coinDataSource)
    }
    @Provides
    @Singleton
    fun provideCoinDetailRepository(coinDetailDataSource: CoinDetailDataSource):CoinDetailRepository{
        return CoinDetailRepositoryImpl(coinDetailDataSource)
    }
}