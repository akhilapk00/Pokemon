package com.pokemon.app.di

import android.content.Context
import com.pokemon.app.network.service.PockmonApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Singleton
    @Provides
    fun provideApiService(
        @ApplicationContext appContext: Context

    ): PockmonApiServices {
        return PockmonApiServices(appContext)
    }

}
