package com.example.domain.model

data class CoinModel(
    val id:String,
    val name:String,
    val symbol:String,
    val rank:Int,
    val is_new:Boolean,
    val is_active:Boolean
)
