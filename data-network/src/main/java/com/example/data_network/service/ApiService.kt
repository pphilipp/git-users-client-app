package com.example.data_network.service

import com.example.data_network.model.responce.UserDetailsRM
import com.example.data_network.model.responce.UserRM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun fetchUsersList(
        @Query("since") pageNumber: Int = 0,
        @Query("per_page") perPageAmount: Int = 30,
    ): Response<List<UserRM>>

    @GET("users/{userLogin}")
    suspend fun fetchUserDetails(
        @Path("userLogin") userLogin: String
    ): Response<UserDetailsRM>
}