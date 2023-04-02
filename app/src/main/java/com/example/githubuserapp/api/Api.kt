package com.example.githubuserapp.api

import com.example.githubuserapp.response.UserResponse
import retrofit2.Call
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