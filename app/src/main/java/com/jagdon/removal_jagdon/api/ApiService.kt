package com.jagdon.removal_jagdon.api

import com.jagdon.removal_jagdon.models.MenuItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("/api/menu-items")
    fun getMenuItems(): Call<List<MenuItem>>

    @POST("/api/menu-items")
    fun addItem(@Body item: MenuItem): Call<Void>

    @PUT("/api/menu-items/{id}")
    fun editItem(@Path("id") id: String, @Body item: MenuItem): Call<Void>

    @DELETE("/api/menu-items/{id}")
    fun deleteItem(@Path("id") id: String): Call<Void>
}
