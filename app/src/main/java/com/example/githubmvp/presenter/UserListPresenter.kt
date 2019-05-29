package com.example.githubmvp.presenter

import com.example.githubmvp.contract.UserListView
import com.example.githubmvp.model.HttpStatus
import com.example.githubmvp.repository.UserRepository

class UserListPresenter(val userListView: UserListView) {

    private val userRepo = UserRepository.getInstance()

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