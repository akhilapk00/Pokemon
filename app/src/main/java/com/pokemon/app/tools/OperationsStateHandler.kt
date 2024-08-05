package com.pokemon.app.tools

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pokemon.app.state.ResponseState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OperationsStateHandler<T>(
    private val scope: CoroutineScope,
    private var stateUpdateCallback:suspend (state: ResponseState<T>)->Unit
) {
//    private val _state = MutableLiveData<ResponseState<T>>()
//    val state: LiveData<ResponseState<T>> = _state

    private var action: (suspend()-> ResponseState<T>)? = null
    private var apiCallJob : Job? = null
    fun load(action: suspend () -> ResponseState<T>) {
        this.action = action
        apiCallJob = scope.launch(Dispatchers.IO) {
            stateUpdateCallback(ResponseState.Loading)
            try {
                val response = action()
                updateState(response)
            }   catch (e: CancellationException) {
                updateState(ResponseState.Cancelled)
            } catch (e: Exception) {
                updateState(ResponseState.Failed(e.message ?: MSG_SOMETHING_WENT_WRONG, 100))
            }
        }
    }



    private suspend fun updateState(state: ResponseState<T>){
        withContext(Dispatchers.Main) {
            stateUpdateCallback(state)
        }
    }

    fun retry() {
        if (action != null) {
            load(action!!)
        }
    }

    fun cancel() {
        apiCallJob?.cancel(CancellationException())
        apiCallJob = null
    }


    companion object {
        const val MSG_SOMETHING_WENT_WRONG = "Something went wrong"
        const val MSG_VALIDATION_ERROR = "Validation error"
        const val MSG_NO_NETWORK = "No internet connection"
    }
}