package com.pokemon.app.network.model

import android.net.Uri
import android.util.Log

data class PokmonList (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    val results: List<Pokemon>
)

data class Pokemon (
    val name: String,
    val url: String
){
    fun getPath() :String?{
   return  Uri.parse(this.url).path
    }
}