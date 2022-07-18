package com.example.ca.usecase

import com.example.ca.Resource
import com.example.domain.model.CoinDetailModel
import com.example.domain.repository.CoinDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(val repository: CoinDetailRepository) {
    operator fun invoke(id: String): Flow<Resource<CoinDetailModel>> = flow {
        try {
            emit(Resource.Loading<CoinDetailModel>())
            val coinDetail = repository.getCoinDetail(id)
            emit(Resource.Success<CoinDetailModel>(coinDetail))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetailModel>(e.localizedMessage ?: "모르는 오류 에휴"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetailModel>("연결실패 에휴"))
        }
    }
}