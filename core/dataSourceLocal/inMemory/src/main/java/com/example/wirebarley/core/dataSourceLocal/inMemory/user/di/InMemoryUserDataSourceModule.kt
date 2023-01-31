package com.example.wirebarley.core.dataSourceLocal.inMemory.user.di

import com.example.wirebarley.core.dataSourceLocal.inMemory.user.InMemoryUserDataSource
import com.example.wirebarley.core.dataSourceLocal.inMemory.user.InMemoryUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InMemoryUserDataSourceModule {

    @Binds
    fun bindsInMemoryUserDataSource(
        inMemoryUserDataSource: InMemoryUserDataSourceImpl
    ): InMemoryUserDataSource
}