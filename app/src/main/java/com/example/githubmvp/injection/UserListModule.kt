package com.example.githubmvp.injection

import android.app.Activity
import com.example.githubmvp.presenter.UserListPresenter
import dagger.Module
import dagger.Provides

@Module
class UserListModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): UserListPresenter {
        return UserListPresenter()
    }
}