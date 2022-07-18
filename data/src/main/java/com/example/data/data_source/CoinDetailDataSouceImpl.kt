package com.example.data.data_source

import com.example.data.entity.detail.CoinDetailEntity
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CoinDetailDataSouceImpl @Inject constructor(private val retrofit: Retrofit):CoinDetailDataSource {
    override suspend fun getCoinDetail(id: String): CoinDetailEntity {
        return retrofit.create(CoinDetailDataSource::class.java).getCoinDetail(id)
    }

}