package com.pokemon.app.network.sources

import com.pokemon.app.network.model.PokmonDetail
import com.pokemon.app.network.model.PokmonList
import com.pokemon.app.network.service.PockmonApiServices
import com.pokemon.app.state.ResponseState
import javax.inject.Inject


class UserDataSourcesImpl @Inject constructor(
    private val apiServices: PockmonApiServices
):UserDataSource {
    override suspend fun getPokeMonList(): ResponseState<PokmonList> {
      return  try {
            val response = apiServices.getPokmon().await()
            ResponseState.Success(response)

        }catch (e:Exception) {
            ResponseState.Failed(e.message?:"",2024)
        }
    }

    override suspend fun getPokeMon(url: String): ResponseState<PokmonDetail> {
        return  try {
            val response = apiServices.getPokMonDetail(url).await()
            ResponseState.Success(response)

        }catch (e:Exception) {
            ResponseState.Failed(e.message?:"",2024)
        }
    }


}