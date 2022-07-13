package com.example.data

import retrofit2.http.GET

interface CoinDataSource {
    @GET("/v1/coins")
    suspend fun getCoins():List<CoinEntity>
}