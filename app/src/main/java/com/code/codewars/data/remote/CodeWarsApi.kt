package com.code.codewars.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CodeWarsApi {

    @Headers("Content-Type: application/json")
    @GET("users/{user}/code-challenges/authored")
    fun getUserAuthoredChallenges(
        @Path("user") user: String,
    ): Single<ApiResponse>
}
