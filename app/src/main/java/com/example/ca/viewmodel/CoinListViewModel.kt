package com.example.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca.Resource
import com.example.ca.State
import com.example.ca.usecase.CoinUseCase
import com.example.domain.model.CoinModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val useCase: CoinUseCase):ViewModel() {

    data class CoinListState(
        val state: State = State.LOADING,
        val data: List<CoinModel> = emptyList(),
        val error: String = ""
    )
    val _CoinState= MutableStateFlow(CoinListState())
    val CoinState: StateFlow<CoinListState> = _CoinState.asStateFlow()
    fun getCoin()
    {
        useCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _CoinState.value = CoinListState()
                }
                is Resource.Success -> {
                    _CoinState.value = CoinListState(
                        state = State.SUCCESS,
                        error = "",
                        data = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _CoinState.value = CoinListState(
                        state = State.ERROR,
                        error = result.message,
                        data = emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
    init {
        getCoin()
    }
}