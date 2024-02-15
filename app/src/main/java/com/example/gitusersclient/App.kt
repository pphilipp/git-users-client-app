package com.example.gitusersclient

import android.app.Application
import com.example.gitusersclient.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule
            )
        }
    }
}