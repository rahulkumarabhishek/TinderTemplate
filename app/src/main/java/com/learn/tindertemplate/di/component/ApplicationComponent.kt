package com.learn.tindertemplate.di.component

import com.learn.tindertemplate.di.module.ApiRepositoryModule
import com.learn.tindertemplate.presentation.MainActivity
import com.learn.tindertemplate.di.module.AppModule
import com.learn.tindertemplate.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class,ApiRepositoryModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}