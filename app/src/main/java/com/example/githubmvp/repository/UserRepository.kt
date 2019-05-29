package com.example.githubmvp.repository

import com.example.githubmvp.endpoint.UserEndpoint
import com.example.githubmvp.model.HttpStatus
import com.example.githubmvp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun getAllUsers(onComplete: (List<User>, HttpStatus) -> Unit) {
        UserEndpoint.getEndpoint().getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                call.cancel()
                onComplete(emptyList(), HttpStatus.httpError(errorMessage = t.message.orEmpty()))
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    onComplete(response.body().orEmpty(), HttpStatus.httpSuccess())
                } else {
                    onComplete(emptyList(), HttpStatus.httpError(response.code(), response.message()))
                }
            }
        })
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository {
            if (instance == null) instance = UserRepository()
            return instance!!
        }
    }
}