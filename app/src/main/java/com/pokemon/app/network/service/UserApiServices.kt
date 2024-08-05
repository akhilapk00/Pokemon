package com.pokemon.app.network.service

import android.content.Context
import android.provider.SyncStateContract
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pokemon.app.data.Constants
import com.pokemon.app.network.RequestUrl
import com.pokemon.app.network.model.PokmonDetail
import com.pokemon.app.network.model.PokmonList
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

interface PockmonApiServices {
    @GET(RequestUrl.POK_MON_LIST)
    fun getPokmon(
    ): Deferred<PokmonList>

    @GET
    fun getPokMonDetail(@Url url:String):Deferred<PokmonDetail>




    companion object {

        operator fun invoke(
            context: Context,


            ): PockmonApiServices {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PockmonApiServices::class.java)
        }

    }
}