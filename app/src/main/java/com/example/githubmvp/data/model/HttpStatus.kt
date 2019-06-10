package com.example.githubmvp.data.model

import java.net.HttpURLConnection

class HttpStatus(val statusCode: Int, val errorMessage: String) {

    fun isSuccessful(): Boolean {
        return statusCode == HttpURLConnection.HTTP_OK
    }

    companion object {
        fun httpError(statusCode: Int = HttpURLConnection.HTTP_NOT_FOUND, errorMessage: String): HttpStatus {
            return HttpStatus(statusCode, errorMessage)
        }

        fun httpSuccess(statusCode: Int = HttpURLConnection.HTTP_OK, errorMessage: String = ""): HttpStatus {
            return HttpStatus(statusCode, errorMessage)
        }
    }
}