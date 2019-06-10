package com.example.githubmvp.ui.contract

import com.example.githubmvp.data.model.User

interface UserListView {
    fun showUserList(users: List<User>)
    fun showErrorMessage(errorMessage: String)
    fun showLoadingState(loadingState: Boolean)
}