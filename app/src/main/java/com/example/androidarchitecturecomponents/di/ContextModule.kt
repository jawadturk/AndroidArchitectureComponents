package com.example.androidarchitecturecomponents.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(var context: Context){

    @Provides
    @Singleton
    @ApplicationContext
    fun context(): Context? {
        return context
    }
}



