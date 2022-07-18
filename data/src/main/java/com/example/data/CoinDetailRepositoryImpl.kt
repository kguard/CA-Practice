package com.example.data

import com.example.data.data_source.CoinDetailDataSource
import com.example.data.entity.detail.toCoinDetail
import com.example.domain.model.CoinDetailModel
import com.example.domain.repository.CoinDetailRepository
import javax.inject.Inject

class CoinDetailRepositoryImpl @Inject constructor(private val api: CoinDetailDataSource):CoinDetailRepository {
    override suspend fun getCoinDetail(id: String):CoinDetailModel {
        return api.getCoinDetail(id).toCoinDetail()
    }
}