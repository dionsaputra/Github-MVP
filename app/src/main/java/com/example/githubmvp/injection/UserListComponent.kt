package com.example.githubmvp.injection

import com.example.githubmvp.ui.view.UserListActivity
import dagger.Component

@Component(modules = [UserListModule::class])
interface UserListComponent {

    fun inject(userListActivity: UserListActivity)

}