package com.learn.tindertemplate.repositories

import com.learn.tindertemplate.data.model.Profile
import retrofit2.Response

interface ApiRepository {
    suspend fun getProfileData(url:String): Response<Profile>
}