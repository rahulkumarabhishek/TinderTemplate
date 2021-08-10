package com.learn.tindertemplate.di.module

import com.learn.tindertemplate.data.api.ApiInterface
import com.learn.tindertemplate.repositories.ApiRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiRepositoryModule {
    @Singleton
    @Provides
    fun provideApiRepository(apiInterface: ApiInterface):ApiRepositoryImpl {
        return ApiRepositoryImpl(apiInterface)
    }
}