package com.example.wirebarley.core.dataSourceRemote.apilayer.di

import com.example.wirebarley.core.dataSourceRemote.apilayer.dataSource.ApilayerDataSource
import com.example.wirebarley.core.dataSourceRemote.apilayer.dataSource.ApilayerDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ApilayerDataSourceModule {

    @Binds
    fun bindsApilaterDataSource(
        apilayerDataSourceImpl: ApilayerDataSourceImpl
    ): ApilayerDataSource
}