package com.example.storehouseclient.endpoints

import Users
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("api/users/getAll")
    fun getUsers(): Call<List<Users>>

    @POST("api/users/save")
    fun addUser(@Body user: Users): Call<Users>

    @DELETE("api/users/delete/{id}")
    fun deleteUser(@Path("id") id: Long): Call<Void>
}