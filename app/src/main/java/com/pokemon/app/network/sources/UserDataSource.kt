package com.pokemon.app.network.sources

import com.pokemon.app.network.model.PokmonDetail
import com.pokemon.app.network.model.PokmonList
import com.pokemon.app.state.ResponseState

interface UserDataSource {
    suspend fun getPokeMonList():ResponseState<PokmonList>
    suspend fun getPokeMon(url: String): ResponseState<PokmonDetail>


}