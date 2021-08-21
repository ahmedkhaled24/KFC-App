package com.example.aaaaa

import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("comments/")
    fun getData() : Call<ArrayList<data>>
}