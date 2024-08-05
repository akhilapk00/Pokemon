package com.pokemon.app.network.repositories

import com.pokemon.app.network.model.PokmonDetail
import com.pokemon.app.network.model.PokmonList
import com.pokemon.app.state.ResponseState

interface UserRepository {
    suspend fun getPokeMonList(): ResponseState<PokmonList>
    suspend fun getPokeMon(url:String): ResponseState<PokmonDetail>

}