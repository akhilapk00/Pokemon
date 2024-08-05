package com.pokemon.app.ui.screens.pokemon.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.app.network.model.Pokemon
import com.pokemon.app.network.model.PokmonList
import com.pokemon.app.network.repositories.UserRepository
import com.pokemon.app.state.ResponseState
import com.pokemon.app.tools.OperationsStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val userRepository: UserRepository
) :ViewModel(){


    var pokemonList = ArrayList<Pokemon>()
    val _pokemanListResponseState = MutableStateFlow<ResponseState<PokmonList>>(ResponseState.Idle)
    val pokemanListResponseState = _pokemanListResponseState.asStateFlow()
    var pokeMonListResponse = OperationsStateHandler<PokmonList>(viewModelScope){
        _pokemanListResponseState .value= it
    }

    fun loadPokemon(){
        pokeMonListResponse.load {
            userRepository.getPokeMonList().apply {
                if (this is ResponseState.Success<*>) {
                    val response = this.response as PokmonList
                    pokemonList.clear()
                    pokemonList.addAll(response.results)
                }
            }
        }
    }

}