package ru.et.eftcompanion.ui.MainActivity

import android.util.Log
import ru.et.eftcompanion.data.Prefs
import ru.et.eftcompanion.mvp.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var prefs: Prefs

    open fun checkLogin(){
        if (prefs.getBoolean("isLogin")){
            initUser()
        } else {
            view?.checkLoginError()
        }
    }

    fun initUser(){

    }
}