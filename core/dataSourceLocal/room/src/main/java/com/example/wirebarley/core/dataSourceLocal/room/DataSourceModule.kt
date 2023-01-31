package com.example.wirebarley.core.dataSourceLocal.room

import com.example.wirebarley.core.dataSourceLocal.room.dataSource.RoomDataSource
import com.example.wirebarley.core.dataSourceLocal.room.dataSource.RoomDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsRoomDataSource(
        roomDataSourceImpl: RoomDataSourceImpl
    ): RoomDataSource
}