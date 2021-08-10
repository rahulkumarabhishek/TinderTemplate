package com.learn.tindertemplate.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Application) {
    @Provides
    @Singleton
    fun getAppContext(): Application {
        return context
    }
}