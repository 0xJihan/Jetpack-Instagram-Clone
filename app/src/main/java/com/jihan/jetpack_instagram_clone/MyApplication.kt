package com.jihan.jetpack_instagram_clone

import android.app.Application
import com.jihan.jetpack_instagram_clone.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)

        }
    }
}