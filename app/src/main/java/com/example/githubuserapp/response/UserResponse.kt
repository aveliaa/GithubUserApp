package com.example.githubuserapp.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("users")
    val items : ArrayList<User>
)
