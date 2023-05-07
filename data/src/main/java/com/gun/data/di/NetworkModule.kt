package com.gun.data.di

import com.gun.data.network.PrettyLogger
import com.gun.domain.common.Constants
import com.gun.domain.common.toMd5
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor(PrettyLogger()).setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val ts = System.currentTimeMillis().toString()
                val hash = (ts + Constants.MARVEL_PRIVATE_API_KEY + Constants.MARVEL_PUBLIC_API_KEY).toMd5()

                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("apikey", Constants.MARVEL_PUBLIC_API_KEY)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("hash", hash)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    }
}