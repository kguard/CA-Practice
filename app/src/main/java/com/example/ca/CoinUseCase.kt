package com.example.domain

import javax.inject.Inject

class CoinUseCase @Inject constructor(private val repository: CoinRepository) {
    suspend fun execute()=repository.getCoins()
}