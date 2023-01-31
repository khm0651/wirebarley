package com.example.wirebarley.core.data.di

import com.example.wirebarley.core.data.repository.ExchangeRateRepository
import com.example.wirebarley.core.data.repository.ExchangeRateRepositoryImpl
import com.example.wirebarley.core.data.repository.UserRepository
import com.example.wirebarley.core.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsUserRepository(
        userRepository: UserRepositoryImpl
    ): UserRepository

    @Binds
    fun bindsExchangeRateRepository(
        exchangeRateRepository: ExchangeRateRepositoryImpl
    ): ExchangeRateRepository
}