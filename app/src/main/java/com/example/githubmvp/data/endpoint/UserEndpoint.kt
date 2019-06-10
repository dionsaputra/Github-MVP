package com.example.githubmvp.data.endpoint

import com.example.githubmvp.data.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserEndpoint {

    @GET("/users")
    fun getAllUsers(): Call<List<User>>

    companion object {
        private val BASE_URL = "http://api.github.com"
        private var instance: Retrofit? = null

        private fun getInstance(): Retrofit {
            if (instance == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
                instance = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!!
        }

        fun getEndpoint(): UserEndpoint {
            return getInstance().create(UserEndpoint::class.java)
        }
    }
}