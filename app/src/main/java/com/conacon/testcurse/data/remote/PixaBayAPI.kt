package com.conacon.testcurse.data.remote


import com.conacon.testcurse.BuildConfig
import com.conacon.testcurse.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayAPI {
    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQueri: String,
        @Query("key") apikey : String = BuildConfig.API_KEY
    ): Response<ImageResponse>

}