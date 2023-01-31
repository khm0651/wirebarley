package com.example.wirebarley.core.dataSourceLocal.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesWirebarleyDatabase(
        @ApplicationContext context: Context,
    ): WirebarleyDatabase = Room.databaseBuilder(
        context,
        WirebarleyDatabase::class.java,
        "wirebarley"
    ).build()
}