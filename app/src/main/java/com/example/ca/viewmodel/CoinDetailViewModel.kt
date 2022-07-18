package com.example.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca.Resource
import com.example.ca.State
import com.example.ca.usecase.CoinDetailUseCase
import com.example.domain.model.CoinDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val detailUseCase: CoinDetailUseCase,
    //private val savedStateHandle: SavedStateHandle,
    //private val id: String
) :ViewModel(){
    data class CoinDetailState(
        val state: State = State.LOADING,
        val detail: CoinDetailModel? = null,
        val error: String = ""
    )

    private val _coinDetailState= MutableStateFlow(CoinDetailState())
    val coinDetailState: StateFlow<CoinDetailState> = _coinDetailState.asStateFlow()
    //var t=savedStateHandle.get<String>("coinId")

    fun getCoinDetail(id: String)
    {
        //viewModelScope.launch { detailUseCase.invoke(t) }
        detailUseCase(id).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _coinDetailState.value= CoinDetailState()
                }
                is Resource.Success -> {
                    _coinDetailState.value= CoinDetailState(
                        state= State.SUCCESS,
                        error ="",
                        detail = result.data
                    )
                }
                is Resource.Error -> {
                    _coinDetailState.value= CoinDetailState(
                        state = State.ERROR,
                        error=result.message,
                        detail = null
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}