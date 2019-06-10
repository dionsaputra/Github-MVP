package com.example.githubmvp.presenter

import com.example.githubmvp.ui.contract.UserListView
import com.example.githubmvp.data.repository.UserRepository

class UserListPresenter() {

    private lateinit var userListView: UserListView
    private val userRepo = UserRepository.getInstance()

    fun attachView(userListView: UserListView) {
        this.userListView = userListView
    }

    fun getAllUsers() {
        userListView.showLoadingState(true)
        userRepo.getAllUsers() { users, httpStatus ->
            if (httpStatus.isSuccessful()) {
                userListView.showUserList(users)
            } else {
                userListView.showErrorMessage(httpStatus.errorMessage)
            }
            userListView.showLoadingState(false)
        }
    }
}