package com.example.githubmvp.ui

import android.app.Application
import com.example.githubmvp.injection.AppComponent
import com.example.githubmvp.injection.AppModule
import com.example.githubmvp.injection.DaggerAppComponent

class BaseApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.inject(this)
    }

    fun getAppComponent(): AppComponent = component

    companion object {
        lateinit var instance: BaseApp private set
    }
}