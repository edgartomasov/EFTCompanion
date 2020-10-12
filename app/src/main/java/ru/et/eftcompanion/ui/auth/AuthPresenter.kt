package ru.et.eftcompanion.ui.auth

import ru.et.eftcompanion.model.User
import ru.et.eftcompanion.mvp.MvpPresenter

open class AuthPresenter : MvpPresenter<AuthView>() {

    fun authUser(): User{
        val user = User()
        return user
    }
}