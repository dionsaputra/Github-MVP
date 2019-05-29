package com.example.githubmvp.contract

import com.example.githubmvp.model.User

interface UserListView {
    fun showUserList(users: List<User>)
    fun showErrorMessage(errorMessage: String)
    fun showLoadingState(loadingState: Boolean)
}