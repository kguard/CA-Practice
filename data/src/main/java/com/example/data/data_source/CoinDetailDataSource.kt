package com.example.data.data_source

import com.example.data.entity.detail.CoinDetailEntity
import com.example.domain.model.CoinDetailModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinDetailDataSource {
    @GET("/v1/coins/{coin_id}")
    suspend fun getCoinDetail(
        @Path("coin_id")
        id: String
    ):List<CoinDetailEntity>
}