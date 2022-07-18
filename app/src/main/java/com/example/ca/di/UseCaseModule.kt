package com.example.ca.di

import com.example.ca.usecase.CoinDetailUseCase
import com.example.domain.repository.CoinRepository
import com.example.ca.usecase.CoinUseCase
import com.example.domain.repository.CoinDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideCoinUseCase(repository: CoinRepository): CoinUseCase {
        return CoinUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideCoinDetailUseCase(repository: CoinDetailRepository): CoinDetailUseCase {
        return CoinDetailUseCase(repository)
    }

}