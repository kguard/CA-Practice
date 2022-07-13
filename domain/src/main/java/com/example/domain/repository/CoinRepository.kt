package com.example.domain.repository

import com.example.domain.model.CoinModel

interface CoinRepository {
    suspend fun getCoins(): List<CoinModel>
}