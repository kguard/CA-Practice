package com.example.ca

import com.example.domain.model.CoinDetailModel
import com.example.domain.repository.CoinDetailRepository
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(val repository: CoinDetailRepository) {
    suspend operator fun invoke(id: String?): List<CoinDetailModel>? {
        return id?.let { repository.getCoinDetail(it) }
    }
}