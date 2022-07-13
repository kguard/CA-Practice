package com.example.domain.repository

import com.example.domain.model.CoinDetailModel

interface CoinDetailRepository {
    suspend fun getCoinDetail(id:String):List<CoinDetailModel>
}