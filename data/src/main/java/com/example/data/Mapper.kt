package com.example.data

import com.example.data.entity.CoinEntity
import com.example.data.entity.detail.CoinDetailEntity
import com.example.data.entity.detail.Team
import com.example.domain.model.CoinDetailModel
import com.example.domain.model.CoinModel
import com.example.domain.model.TeamModel

object Mapper {
    //추상화를 통해서 만듦
    fun MapperToModel(entity: CoinEntity): CoinModel =
        CoinModel(entity.id,entity.name,entity.symbol,entity.rank,entity.isNew,entity.isActive)

}