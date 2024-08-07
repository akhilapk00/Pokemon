package com.pokemon.app.state


sealed class ResponseState<out T> {
    data object Idle: ResponseState<Nothing>()
    data object Loading: ResponseState<Nothing>()
    class Success<R>(val response: R?) : ResponseState<R>()
    class ValidationError(val errorCode: Int,val error: String) : ResponseState<Nothing>()
    class Failed(val error: String, val errorCode: Int) : ResponseState<Nothing>()
    data object Cancelled : ResponseState<Nothing>()
}