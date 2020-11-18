package com.example.restapi.api

import com.example.restapi.models.UserRepositary
import retrofit2.Call
import retrofit2.http.GET


interface ServiceRepositary {





    @GET("posts")
    fun listRepos(): Call<List<UserRepositary?>?>?
}