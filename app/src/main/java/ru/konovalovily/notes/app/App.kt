package ru.konovalovily.notes.app

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.konovalovily.notes.di.appModule
import ru.konovalovily.notes.di.dataModule
import ru.konovalovily.notes.di.domainModule
import ru.konovalovily.notes.di.networkModule
import ru.konovalovily.notes.model.BackupWorker
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule, networkModule))

        }
        startWorkManager()
    }

    private fun startWorkManager() {
        WorkManager.getInstance(this).enqueue(
            PeriodicWorkRequest.Builder(
                BackupWorker::class.java, 15, TimeUnit.MINUTES
            ).build()
        )
    }
}