package com.example.githubuserapp.api

import com.example.githubuserapp.response.UserResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    // https://api.github.com/search/users?q={username}
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") query: String
    ): Call<UserResponse>

}