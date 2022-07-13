package com.example.ca

import com.example.domain.model.CoinModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CoinUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): Flow<Resource<List<CoinModel>>> = flow{
        try {
            emit(Resource.Loading<List<CoinModel>>())
            val coins=repository.getCoins()
            emit(Resource.Success<List<CoinModel>>(coins))
        }
        catch (e:HttpException){
            emit(Resource.Error<List<CoinModel>>(e.localizedMessage?:"모르는 오류 에휴"))
        }
        catch (e: IOException){
            emit(Resource.Error<List<CoinModel>>("연결실패 에휴"))
        }
    }
}