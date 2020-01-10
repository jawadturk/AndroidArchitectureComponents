package com.example.androidarchitecturecomponents

import android.app.Application
import android.content.Context
import com.example.androidarchitecturecomponents.data.DataService
import com.example.androidarchitecturecomponents.di.AppComponent
import com.example.androidarchitecturecomponents.di.ContextModule
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import timber.log.Timber.DebugTree

class AndroidArchitectureComponentsApp: Application() {

    private val TAG: String = AndroidArchitectureComponentsApp::class.java.getSimpleName()
    private var dataService: DataService? = null
    private var scheduler: Scheduler? = null
    private var component: AppComponent? = null


    private  fun get(context: Context): AndroidArchitectureComponentsApp? {
        return context.applicationContext as AndroidArchitectureComponentsApp
    }


    fun create(context: Context): AndroidArchitectureComponentsApp? {
        return get(context);
    }

    fun getDataService(): DataService? {
        return dataService
    }

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }
        return scheduler
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        component = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
        dataService = component!!.getDataService()
    }
}