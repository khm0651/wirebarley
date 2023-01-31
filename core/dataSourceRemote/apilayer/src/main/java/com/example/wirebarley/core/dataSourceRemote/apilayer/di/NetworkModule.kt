package com.example.wirebarley.core.dataSourceRemote.apilayer.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import com.example.wirebarley.core.dataSourceRemote.apilayer.api.ApilayerApi
import com.example.wirebarley.core.remoteDataSource.apilayer.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = "https://www.apilayer.net"
private const val API_KEY = "yFZiAh1tch5CVS9BEL70IqhfHYUJb996"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideApiInterceptor(): Interceptor = Interceptor {
        val url = it.request().url.newBuilder()
            .addQueryParameter("access_key", API_KEY)
            .build()

        val request = it.request().newBuilder()
            .url(url)
            .build()

        it.proceed(request)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = when(BuildConfig.BUILD_TYPE){
            "debug" -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun providesApilayerApi(
        networkJson: Json,
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): ApilayerApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .build()
        )
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(ApilayerApi::class.java)
}