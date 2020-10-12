package ru.et.eftcompanion.ui.MainActivity

import ru.et.eftcompanion.mvp.MvpView

interface MainView : MvpView {

    fun checkLoginSuccess()
    fun checkLoginError()
}