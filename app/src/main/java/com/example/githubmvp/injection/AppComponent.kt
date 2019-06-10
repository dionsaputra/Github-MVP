package com.example.githubmvp.injection

import android.app.Application
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: Application)

}