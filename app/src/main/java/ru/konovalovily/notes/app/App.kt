package ru.konovalovily.notes.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.konovalovily.notes.di.appModule
import ru.konovalovily.notes.di.dataModule
import ru.konovalovily.notes.di.domainModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))

        }
    }

}