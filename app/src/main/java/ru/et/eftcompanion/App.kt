package ru.et.eftcompanion

import android.app.Application
import ru.et.eftcompanion.di.ApplicationComponent
import ru.et.eftcompanion.di.ApplicationModule
import ru.et.eftcompanion.di.DaggerApplicationComponent

class App : Application() {

    var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {

        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}