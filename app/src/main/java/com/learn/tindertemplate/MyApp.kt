package com.learn.tindertemplate

import android.app.Application
import com.learn.tindertemplate.di.component.ApplicationComponent
import com.learn.tindertemplate.di.component.DaggerApplicationComponent
import com.learn.tindertemplate.di.module.ApiRepositoryModule
import com.learn.tindertemplate.di.module.AppModule
import com.learn.tindertemplate.di.module.NetworkModule

class MyApp:Application() {
    val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }
    open fun initializeComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder().
        appModule(AppModule(this)).
        networkModule(NetworkModule()).
        apiRepositoryModule(ApiRepositoryModule()).build()
    }

}