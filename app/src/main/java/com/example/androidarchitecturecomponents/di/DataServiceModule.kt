package com.example.androidarchitecturecomponents.di

import com.example.androidarchitecturecomponents.data.DataService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataServiceModule {
    private val BASE_URL = "https://api.donorschoose.org"

    @Provides
    @Singleton
    fun dataService(retrofit: Retrofit): DataService? {
        return retrofit.create<DataService>(DataService::class.java)
    }


    @Provides
    @Singleton
    fun gson(): Gson? {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(DateTime::class.java, DateTimeConverter())
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient?, gson: Gson?): Retrofit? {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }
}

