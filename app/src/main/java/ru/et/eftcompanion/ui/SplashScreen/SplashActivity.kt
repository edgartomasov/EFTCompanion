package ru.et.eftcompanion.ui.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import ru.et.eftcompanion.ui.MainActivity.MainActivity
import ru.et.eftcompanion.R
import ru.et.eftcompanion.di.ApplicationComponent
import ru.et.eftcompanion.mvp.MvpActivity

class SplashActivity : MvpActivity<SplashView, SplashPresenter>(), SplashView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startMainActivity()
    }

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
        component.inject(presenter)
    }

    override fun startMainActivity() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }, 2000)
    }

}