package com.example.androidarchitecturecomponents.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor? {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i(message)
            }

        })
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return interceptor
    }

    @Provides
    @Singleton
    fun cache(cacheFile: File?): Cache? {
        return Cache(cacheFile!!, 10 * 1000 * 1000) //10MB Cahe
    }

    @Provides
    @Singleton
    fun cacheFile(@ApplicationContext context: Context): File? {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @Singleton
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor?,
        cache: Cache?
    ): OkHttpClient? {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor!!)
            .cache(cache)
            .build()
    }
}