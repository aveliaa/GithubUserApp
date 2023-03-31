package com.example.githubuserapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.api.ApiConfig
import com.example.githubuserapp.response.User
import com.example.githubuserapp.response.UserResponse
import retrofit2.Call
import retrofit2.Response

class MainViewModel: ViewModel() {
    val userList = MutableLiveData<ArrayList<User>>()

    fun setSearchResult(query: String){
        val retrofit = ApiConfig.getRetrofit()
            .getUsers(query)
            .enqueue(object : retrofit2.Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){
                        userList.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    var msg: String = "Error not detected"
                    if(t.message != null){
                        msg = t.message!!
                    }
                    Log.d("Error",msg)
                }

            })
    }

    fun getSearchResult(): LiveData<ArrayList<User>>{
        return userList
    }
}