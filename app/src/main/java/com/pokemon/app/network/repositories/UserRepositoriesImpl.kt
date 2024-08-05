package com.pokemon.app.network.repositories

import com.pokemon.app.network.model.PokmonDetail
import com.pokemon.app.network.model.PokmonList
import com.pokemon.app.network.sources.UserDataSource
import com.pokemon.app.state.ResponseState
import javax.inject.Inject

class UserRepositoriesImpl @Inject constructor(private val userDataSource: UserDataSource):
    UserRepository {
    override suspend fun getPokeMonList(): ResponseState<PokmonList> {
      return  userDataSource.getPokeMonList()
    }

    override suspend fun getPokeMon(url: String): ResponseState<PokmonDetail> {
        return  userDataSource.getPokeMon(url)
    }


}