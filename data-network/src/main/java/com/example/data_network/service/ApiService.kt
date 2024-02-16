package com.example.data_network.service

import com.example.data_network.model.responce.UserDetailsRemoteModel
import com.example.data_network.model.responce.UserRemoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun fetchUsersList(
        @Query("since") pageNumber: Int = 1,
        @Query("per_page") perPageAmount: Int = 10,
    ): Response<List<UserRemoteModel>>

    @GET("users/{userLogin}")
    suspend fun fetchUserDetails(
        @Path("userLogin") userLogin: String
    ): Response<UserDetailsRemoteModel>
}