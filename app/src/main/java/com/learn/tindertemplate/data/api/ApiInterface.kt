package com.learn.tindertemplate.data.api

import com.learn.tindertemplate.data.model.Profile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    suspend fun getProfileData(@Url url:String): Response<Profile>
}