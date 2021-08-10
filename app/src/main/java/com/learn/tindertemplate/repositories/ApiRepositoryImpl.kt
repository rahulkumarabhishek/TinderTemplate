package com.learn.tindertemplate.repositories

import com.learn.tindertemplate.data.api.ApiInterface
import com.learn.tindertemplate.data.model.Profile
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class ApiRepositoryImpl(private val apiInterface: ApiInterface) : ApiRepository {
    override suspend fun getProfileData(url:String): Response<Profile> {
        return apiInterface.getProfileData(url)
    }
}