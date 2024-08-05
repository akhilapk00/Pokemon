package com.pokemon.app.ui.screens.pokemon.detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.app.network.model.Pokemon
import com.pokemon.app.network.model.PokmonDetail
import com.pokemon.app.network.model.PokmonList
import com.pokemon.app.network.repositories.UserRepository
import com.pokemon.app.state.ResponseState
import com.pokemon.app.tools.OperationsStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewmodel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    var themeColor: Color = Color.Black
    var pokemonList = ArrayList<Pokemon>()
    val _pokemanDetailResponseState = MutableStateFlow<ResponseState<PokmonDetail>>(ResponseState.Idle)
    val pokemanDetailResponseState = _pokemanDetailResponseState.asStateFlow()
    var pokeMonDetailResponse = OperationsStateHandler<PokmonDetail>(viewModelScope){
        _pokemanDetailResponseState .value= it
    }

    fun loadPokMonDetail(url:String) {
        pokeMonDetailResponse.load {
            userRepository.getPokeMon(url)
        }
    }
}