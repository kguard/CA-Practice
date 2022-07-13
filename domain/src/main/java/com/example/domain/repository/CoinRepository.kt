package com.example.domain

interface CoinRepository {
    suspend fun getCoins(): List<CoinModel>
}