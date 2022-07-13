package com.example.data

import com.example.data.data_source.CoinDataSource
import com.example.domain.model.CoinModel
import com.example.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api:CoinDataSource): CoinRepository {
    override suspend fun getCoins(): List<CoinModel> {
        return api.getCoins().filter { it.type=="coin"}.map{Mapper.MapperToModel(it)}
    }

}