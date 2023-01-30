package com.example.wirebarley.di

import com.example.wirebarley.data.repository.UserRepository
import com.example.wirebarley.data.repository.UserRepositoryImpl
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
}