package com.example.ca

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CoinDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val detailUseCase: CoinDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
):ViewModel(){
    data class CoinDetailState(
        val isLoading: Boolean = true,
        val detail: CoinDetailModel? = null,
        val error: String = ""
    )

    var t=savedStateHandle.get<String>("coinId")
    suspend fun getCoinDetail()
    {
        viewModelScope.launch { detailUseCase.invoke(t) }
    }
}