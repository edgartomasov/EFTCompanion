package ru.et.eftcompanion.mvp

import ru.et.eftcompanion.di.ApplicationComponent

interface MvpView {

    fun onError(error: Throwable)

    fun inject(component: ApplicationComponent)
}