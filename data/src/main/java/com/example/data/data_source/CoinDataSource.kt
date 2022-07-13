package com.example.data.data_source

import com.example.data.entity.CoinEntity
import retrofit2.http.GET

interface CoinDataSource {
    @GET("/v1/coins")
    suspend fun getCoins():List<CoinEntity>
}