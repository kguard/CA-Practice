package com.example.data.data_source

import com.example.data.entity.CoinEntity
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CoinDataSourceImpl @Inject constructor(private val retrofit: Retrofit):CoinDataSource
{
    override suspend fun getCoins(): List<CoinEntity> {
       return retrofit.create(CoinDataSource::class.java).getCoins()
    }

}