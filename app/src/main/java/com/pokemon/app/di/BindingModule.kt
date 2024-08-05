package com.pokemon.app.di

import com.pokemon.app.network.repositories.UserRepository
import com.pokemon.app.network.sources.UserDataSource
import com.pokemon.app.network.sources.UserDataSourcesImpl
import com.pokemon.app.network.repositories.UserRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    @Singleton
    abstract fun bindRepository(impl: UserRepositoriesImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindUserDataSource(impl: UserDataSourcesImpl): UserDataSource
}


@Qualifier
annotation class UsersRepository